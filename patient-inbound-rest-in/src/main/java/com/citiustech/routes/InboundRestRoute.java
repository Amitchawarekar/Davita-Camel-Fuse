package com.citiustech.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class InboundRestRoute extends RouteBuilder {
	
	public String patientreportDetailsSqlQuery;
	public String getPatientreportDetailsSqlQuery() {
		return patientreportDetailsSqlQuery;
	}
	public void setPatientreportDetailsSqlQuery(String patientreportDetailsSqlQuery) {
		this.patientreportDetailsSqlQuery = patientreportDetailsSqlQuery;
	}
	@Override
	public void configure() throws Exception {
		
		// starts server and configure the server
		restConfiguration() 
				.component("spark-rest")
				.port(8081)
				.bindingMode(RestBindingMode.json);
		
		//Rest endpoint
		rest("/patient").get("/{id}").to("direct:getPatientReportDetails");
		
		
		//Rest route for retrieving patient report details
		from("direct:getPatientReportDetails")
		.to("sql:select * from Patients where patientId = :#id?outputType=SelectOne");
	}

}
