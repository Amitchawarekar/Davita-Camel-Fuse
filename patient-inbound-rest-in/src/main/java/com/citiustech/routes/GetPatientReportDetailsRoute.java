package com.citiustech.routes;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;


public class GetPatientReportDetailsRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
//		from("file:src/main/resources/data/inbound?noop=true&fileName=patientIds.txt")
//		.split(body().tokenize("\n"))
//		.setHeader("patientId", body())
//		.log("Read Patient ID: ${header.patientId}")
//		.to("direct:getPatient");
//		System.out.println("Reading");
		
//		
//		//get PatientByID using SQL component
//		from("direct:getPatientReportDetails")
//		.setHeader("id", simple("${header.id}"))
//		.to("sql:SELECT p.patient_id, p.first_name, p.last_name, p.date_of_birth, p.gender, p.address, p.phone_number, p.email,d.diagnosis_date, d.diagnosis_details,e.equipment_name, e.usage_details,n.first_name AS nurse_first_name, n.last_name AS nurse_last_name, n.contact_number AS nurse_contact_number, n.email AS nurse_email, pn.assignment_date, pn.end_date FROM Patients p LEFT JOIN Diagnoses d ON p.patient_id = d.patient_id LEFT JOIN Equipment e ON p.patient_id = e.patient_id LEFT JOIN Patient_Nurses pn ON p.patient_id = pn.patient_id LEFT JOIN Nurses n ON pn.nurse_id = n.nurse_id WHERE p.patient_id = :#id?outputType=SelectOne");
//	
		
//		from("direct:getPatient")
		from("file:src/main/resources/data/inbound?noop=true&fileName=patientIds.txt")
		.split(body().tokenize("\n"))
		.process(new Processor() {
			
			@Override
			public void process(Exchange exchange) throws Exception {
				String body = exchange.getIn().getBody(String.class);
				System.out.println(body);
				exchange.getIn().setHeader("patientId", body);
				
			}
		})
		.log("be : http://localhost:8081/patient/"+"${header.patientId}")
		.setHeader(Exchange.HTTP_METHOD, constant("GET"))
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
