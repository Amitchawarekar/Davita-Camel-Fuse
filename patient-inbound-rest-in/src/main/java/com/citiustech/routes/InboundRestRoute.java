package com.citiustech.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class InboundRestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		restConfiguration() // starts server and configure the server
				.component("spark-rest")
				.port(8081)
				.bindingMode(RestBindingMode.json);

		rest("/patient").get("/{id}").to("direct:getPatientReportDetails");
		
		from("direct:getPatientReportDetails")
		.to("sql:SELECT p.patient_id, p.first_name, p.last_name, p.date_of_birth, p.gender, p.address, p.phone_number, p.email,d.diagnosis_date, d.diagnosis_details,e.equipment_name, e.usage_details,n.first_name AS nurse_first_name, n.last_name AS nurse_last_name, n.contact_number AS nurse_contact_number, n.email AS nurse_email, pn.assignment_date, pn.end_date FROM Patients p LEFT JOIN Diagnoses d ON p.patient_id = d.patient_id LEFT JOIN Equipment e ON p.patient_id = e.patient_id LEFT JOIN Patient_Nurses pn ON p.patient_id = pn.patient_id LEFT JOIN Nurses n ON pn.nurse_id = n.nurse_id WHERE p.patient_id = :#id?outputType=SelectOne")
		.log("${body}");
	}

}
