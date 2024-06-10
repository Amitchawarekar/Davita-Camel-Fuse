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
	public String amqQueue;
	public String restApiDataReceivedWireTapDirect;
	
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
	public String getAmqQueue() {
		return amqQueue;
	}
	public void setAmqQueue(String amqQueue) {
		this.amqQueue = amqQueue;
	}
	public String getRestApiDataReceivedWireTapDirect() {
		return restApiDataReceivedWireTapDirect;
	}
	public void setRestApiDataReceivedWireTapDirect(String restApiDataReceivedWireTapDirect) {
		this.restApiDataReceivedWireTapDirect = restApiDataReceivedWireTapDirect;
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
		from(getPatientIdsSourceUri()).routeId("PatientReportRoute")
		.split(body().tokenize("\n"))
		.setBody(simple("${body.trim()}"))
		.choice()
		.when(body().isEqualTo(""))
		.log("Empty Line in file")
		.otherwise()
		.process(new Processor() {
			@Override
			public void process(Exchange exchange) throws Exception {
				String input_body = exchange.getIn().getBody(String.class);
				exchange.getIn().setHeader("patientId", input_body);
			}
		})
		.log(LoggingLevel.INFO,"Received patientId from the file - ${body}")
		.setHeader(Exchange.HTTP_METHOD,constant("GET"))
		.setHeader(Exchange.HTTP_PATH, simple("${header.patientId}"))
		//Requesting the REST API for the data
		.log(LoggingLevel.INFO,"Requesting :" + getHttpUri() + "${header.patientId}")
		.to(getHttpUri())
		.wireTap(getRestApiDataReceivedWireTapDirect())
		//Sending it ActivemQ Server
		.to(getAmqQueue())
		.log(LoggingLevel.INFO,"Patient Data sent to ActiveMQ queue");
		
		
		from(getRestApiDataReceivedWireTapDirect()) // Wiretap route
		.log(LoggingLevel.INFO,"Patient Data received from rest api");
	}
}
