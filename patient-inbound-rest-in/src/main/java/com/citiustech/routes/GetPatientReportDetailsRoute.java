package com.citiustech.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

import com.citiustech.exceptions.ActiveMQException;
import com.citiustech.exceptions.PatientNotFoundException;


public class GetPatientReportDetailsRoute extends RouteBuilder {
	
	public String patientIdsSourceUri;
	public String httpUri;
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
	@Override
	public void configure() throws Exception {
		
		//Exception Handling
		onException(PatientNotFoundException.class)
		.handled(true)
		.log("Patient Not Found : ${exception.message}")
		.to("log:patientNotFound");
		
		onException(ActiveMQException.class)
		.handled(true)
		.log("ActiveMQ error : ${exception.message}")
		.to("log:ActiveMQError");
		
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
		//setting http uri
		.setHeader(Exchange.HTTP_URI, simple("http://localhost:8081/patient/"+"${header.patientId}"))
		//Requesting the REST API for the data
		.log("Requesting : http://localhost:8081/patient/"+"${header.patientId}")
		.to("http://localhost:8081/patient/"+"${header.patientId}")
//		.log("${body}");
		//Sending it ActivemQ Server
		.to("activemq:queue:patientDetailsQueue");
	
	
//	    from("timer://test-rest-api?period=1000")
//		    .log("Rest API IS CALLING")
//		    .setHeader(Exchange.HTTP_METHOD, simple("GET"))
//		    .to("http://localhost:8081/patient/1001")
//		    .log("${body}");
	}
	

}
