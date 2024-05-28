package com.citiustech.restprojects.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class RestRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		// TODO Auto-generated method stub
		restConfiguration() // starts server and configure the server
				.component("spark-rest").port(8080).bindingMode(RestBindingMode.json);

		rest("/PatientData").get("/all").to("direct:getAllPatients").get("/{id}").to("direct:getPatientById").post()
				.to("direct:addPatient").patch("/{id}").to("direct:updatePatientAddress").delete("/{id}")
				.to("direct:deletePatient").put("/{id}").to("direct:putPatient");
	}

}
