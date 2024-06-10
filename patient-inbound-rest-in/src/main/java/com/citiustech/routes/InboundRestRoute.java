package com.citiustech.routes;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class InboundRestRoute extends RouteBuilder {
	public String patientReportDetailsQueue;
	public String allPatientsSqlQuery;
	
	public String getPatientReportDetailsQueue() {
		return patientReportDetailsQueue;
	}
	public void setPatientReportDetailsQueue(String patientReportDetailsQueue) {
		this.patientReportDetailsQueue = patientReportDetailsQueue;
	}
	public String getAllPatientsSqlQuery() {
		return allPatientsSqlQuery;
	}
	public void setAllPatientsSqlQuery(String allPatientsSqlQuery) {
		this.allPatientsSqlQuery = allPatientsSqlQuery;
	}

	@Override
	public void configure() throws Exception {
		// starts server and configure the server
		restConfiguration() 
				.component("spark-rest")
				.port(8081)
				.bindingMode(RestBindingMode.json);
		
		//Rest endpoint
		rest("/patient").get("/{id}").to(getPatientReportDetailsQueue());
		
		//Rest route for retrieving patient report details
		from(getPatientReportDetailsQueue())
		.to(getAllPatientsSqlQuery() + "?outputType=SelectOne");
	}
}
