package com.citiustech.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import com.citiustech.exceptions.PatientNotFoundException;


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
		onException(PatientNotFoundException.class)
		.handled(true)
		.log("Patient Not Found : ${exception.message}")
		.to("log:patientNotFound");
		
		
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
