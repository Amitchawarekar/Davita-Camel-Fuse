package com.citiustech.routes;

import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.sql.SQLException;

import org.apache.activemq.ConnectionFailedException;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.http.common.HttpOperationFailedException;

public class GetPatientReportDetailsRoute extends RouteBuilder {
	
	public String patientIdsSourceUri;
	public String httpUri;
	public String AMQQueue;
	
	public String getPatientIdsSourceUri() {
		return patientIdsSourceUri;
	}
	public void setPatientIdsSourceUri(String patientIdsSourceUri) {
		this.patientIdsSourceUri = patientIdsSourceUri;
	}
	
	public String getHttpUri() {
		return httpUri;
	}
	public void setHttpUri(String httpUri) {
		this.httpUri = httpUri;
	}
	
	public String getAMQQueue() {
		return AMQQueue;
	}
	public void setAMQQueue(String aMQQueue) {
		AMQQueue = aMQQueue;
	}
	
	@Override
	public void configure() throws Exception {
		
		//Exception Handling
		
		//Http Invocation Exception
		onException(HttpOperationFailedException.class)
		.handled(true)
		.log("Http Request Failed : ${exception.message}")
		.maximumRedeliveries(3)
		.retryAttemptedLogLevel(LoggingLevel.WARN);
		
		
		//ActiveMQ connection Exception
		onException(ConnectionFailedException.class)
		.handled(true)
		.log("ActiveMQ Connection Failed : ${exception.message}")
		.maximumRedeliveries(3)
		.maximumRedeliveryDelay("1000")
		.retryAttemptedLogLevel(LoggingLevel.WARN);
		
		
		//File Not Found Exception
		onException(FileNotFoundException.class)
		.handled(true)
		.log("File Not Exist : ${exception.message}")
		.retryAttemptedLogLevel(LoggingLevel.WARN);
		
		
		//Rest Api Exception
		onException(ConnectException.class)
		.handled(true)
		.log("Rest Api Call Failed : ${exception.message}")
		.maximumRedeliveries(3)
		.maximumRedeliveryDelay("1000")
		.retryAttemptedLogLevel(LoggingLevel.WARN);
		
		
		//SQL Exception 
		onException(SQLException.class)
		.handled(true)
		.log("SQL Exception occurred: ${exception.message}");		
				
		
		
		//Default Error Handler
		onException(Exception.class)
		.handled(true)
		.log("Exception occurred: ${exception.message}");		
		
		//tokenizing and triming the incoming patientIds from the file 
		from(getPatientIdsSourceUri())
		.split(body().tokenize("\n"))
		.setBody(simple("${body.trim()}"))
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				String body = exchange.getIn().getBody(String.class);
				System.out.println(body);
				exchange.getIn().setHeader("patientId", body);
			}
		})
		
		//setting http method
		.setHeader(Exchange.HTTP_METHOD,constant("GET"))
		//setting Http path 
		.setHeader(Exchange.HTTP_PATH, simple("${header.patientId}"))
		//Requesting the REST API for the data
		.log("Requesting :" + getHttpUri())
		.to(getHttpUri())
//		.log("${body}");
		//Sending it ActivemQ Server
		.to(getAMQQueue());
	}
	

}
