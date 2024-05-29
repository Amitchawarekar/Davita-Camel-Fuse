package com.citiustech.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;


public class GetPatientReportDetailsRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		
		from("file:src/main/resources/data/inbound?noop=true&fileName=patientIds.txt")
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
		
		.setHeader(Exchange.HTTP_METHOD,constant("GET"))
		.setHeader(Exchange.HTTP_URI, simple("http://localhost:8081/patient/"+"${header.patientId}"))
		.log("af : http://localhost:8081/patient/"+"${header.patientId}")
		.to("http://localhost:8081/patient/"+"${header.patientId}")
		.log("to : http://localhost:8081/patient/"+"${header.patientId}")
		.log("PatientDetails : ${body}");
	
	
//	    from("timer://test-rest-api?period=1000")
//		    .log("Rest API IS CALLING")
//		    .setHeader(Exchange.HTTP_METHOD, simple("GET"))
//		    .to("http://localhost:8081/patient/1001")
//		    .log("${body}");
	}
	

}
