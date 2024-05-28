package com.citiustech.restprojects.routes;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;

import com.citiustech.restprojects.processors.BasicAuthProcessor;

public class PatientCrudRoute extends RouteBuilder {
	
	public String allPatientSqlQuery;
	public String patientByIdSQlQuery;
	public String addPatientSqlQuery;
	public String deletePatientSqlQuery;
	public String updatePatientSqlQuery;
	public String updatePatientAddressSqlQuery;
	

	public String getAllPatientSqlQuery() {
		return allPatientSqlQuery;
	}
	public void setAllPatientSqlQuery(String allPatientSqlQuery) {
		this.allPatientSqlQuery = allPatientSqlQuery;
	}
	public String getPatientByIdSQlQuery() {
		return patientByIdSQlQuery;
	}
	public void setPatientByIdSQlQuery(String patientByIdSQlQuery) {
		this.patientByIdSQlQuery = patientByIdSQlQuery;
	}
	public String getAddPatientSqlQuery() {
		return addPatientSqlQuery;
	}
	public void setAddPatientSqlQuery(String addPatientSqlQuery) {
		this.addPatientSqlQuery = addPatientSqlQuery;
	}
	public String getDeletePatientSqlQuery() {
		return deletePatientSqlQuery;
	}
	public void setDeletePatientSqlQuery(String deletePatientSqlQuery) {
		this.deletePatientSqlQuery = deletePatientSqlQuery;
	}
	public String getUpdatePatientSqlQuery() {
		return updatePatientSqlQuery;
	}
	public void setUpdatePatientSqlQuery(String updatePatientSqlQuery) {
		this.updatePatientSqlQuery = updatePatientSqlQuery;
	}
	public String getUpdatePatientAddressSqlQuery() {
		return updatePatientAddressSqlQuery;
	}
	public void setUpdatePatientAddressSqlQuery(String updatePatientAddressSqlQuery) {
		this.updatePatientAddressSqlQuery = updatePatientAddressSqlQuery;
	}

	@Override
	public void configure() throws Exception {
		
		
		//get All patients using JDBC Component
		from("direct:getAllPatients").log("${header.Authorization}")
		.process(new BasicAuthProcessor())
		.choice()
		.when(header("SuccessfulAuthorization").isEqualTo(true))
		.setBody(simple(getAllPatientSqlQuery()))
		.to("jdbc:mysqlDataSource")
//		.when(header("SuccessfulAuthorization").isEqualTo(false))
		.otherwise()
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(401))
		.setBody(simple("Invalid Login"));
			
		//get PatientByID using SQL component
		from("direct:getPatientById")
		.process(new BasicAuthProcessor())
		.choice()
		.when(header("SuccessfulAuthorization").isEqualTo(true))
		.setHeader("id", simple("${header.id}")).
		to(getPatientByIdSQlQuery())
		.otherwise()
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(401))
		.setBody(simple("Invalid Login"));
		
		//add patient 
		from("direct:addPatient")
		.process(new BasicAuthProcessor())
		.choice()
		.when(header("SuccessfulAuthorization").isEqualTo(true))
		.to(getAddPatientSqlQuery())
		.otherwise()
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(401))
		.setBody(simple("Invalid Login"));
		
		//delete patient
		from("direct:deletePatient")
		.process(new BasicAuthProcessor())
		.choice()
		.when(header("SuccessfulAuthorization").isEqualTo(true))
		.setHeader("id", simple("${header.id}"))
		.to(getDeletePatientSqlQuery())
		.otherwise()
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(401))
		.setBody(simple("Invalid Login"));
		
		//put patient
		from("direct:putPatient")
		.process(new BasicAuthProcessor())
		.choice()
		.when(header("SuccessfulAuthorization").isEqualTo(true))
		 .log("Update patient data : ${body}")
		   .to(getUpdatePatientSqlQuery())
		   .otherwise()
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(401))
			.setBody(simple("Invalid Login"));
		
		// patch patient
		from("direct:updatePatientAddress")
		.process(new BasicAuthProcessor())
		.choice()
		.when(header("SuccessfulAuthorization").isEqualTo(true))
		   .log("Update patient data : ${body}")
		   .to(getUpdatePatientAddressSqlQuery())
		   .otherwise()
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(401))
			.setBody(simple("Invalid Login"));
	}
}
