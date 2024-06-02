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
	
	
	public String AllPatientsDirect;
	public String patientByIdDirect;
	public String addPatientDirect;
	public String deletePatientDirect;
	public String putPatientDirect;
	public String updatePatientAddressDirect;
	
	

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
	
	
	public String getAllPatientsDirect() {
		return AllPatientsDirect;
	}
	public void setAllPatientsDirect(String allPatientsDirect) {
		AllPatientsDirect = allPatientsDirect;
	}
	public String getPatientByIdDirect() {
		return patientByIdDirect;
	}
	public void setPatientByIdDirect(String patientByIdDirect) {
		this.patientByIdDirect = patientByIdDirect;
	}
	public String getAddPatientDirect() {
		return addPatientDirect;
	}
	public void setAddPatientDirect(String addPatientDirect) {
		this.addPatientDirect = addPatientDirect;
	}
	public String getDeletePatientDirect() {
		return deletePatientDirect;
	}
	public void setDeletePatientDirect(String deletePatientDirect) {
		this.deletePatientDirect = deletePatientDirect;
	}
	public String getPutPatientDirect() {
		return putPatientDirect;
	}
	public void setPutPatientDirect(String putPatientDirect) {
		this.putPatientDirect = putPatientDirect;
	}
	public String getUpdatePatientAddressDirect() {
		return updatePatientAddressDirect;
	}
	public void setUpdatePatientAddressDirect(String updatePatientAddressDirect) {
		this.updatePatientAddressDirect = updatePatientAddressDirect;
	}
	@Override
	public void configure() throws Exception {
		
		
		//get All patients using JDBC Component
		from(getAllPatientsDirect()).log("${header.Authorization}")
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
		from(getPatientByIdDirect())
		.process(new BasicAuthProcessor())
		.choice()
		.when(header("SuccessfulAuthorization").isEqualTo(true))
		.setHeader("id", simple("${header.id}")).
		to(getPatientByIdSQlQuery())
		.otherwise()
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(401))
		.setBody(simple("Invalid Login"));
		
		//add patient 
		from(getAddPatientDirect())
		.process(new BasicAuthProcessor())
		.choice()
		.when(header("SuccessfulAuthorization").isEqualTo(true))
		.to(getAddPatientSqlQuery())
		.otherwise()
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(401))
		.setBody(simple("Invalid Login"));
		
		//delete patient
		from(getDeletePatientDirect())
		.process(new BasicAuthProcessor())
		.choice()
		.when(header("SuccessfulAuthorization").isEqualTo(true))
		.setHeader("id", simple("${header.id}"))
		.to(getDeletePatientSqlQuery())
		.otherwise()
		.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(401))
		.setBody(simple("Invalid Login"));
		
		//put patient
		from(getPutPatientDirect())
		.process(new BasicAuthProcessor())
		.choice()
		.when(header("SuccessfulAuthorization").isEqualTo(true))
		 .log("Update patient data : ${body}")
		   .to(getUpdatePatientSqlQuery())
		   .otherwise()
			.setHeader(Exchange.HTTP_RESPONSE_CODE, constant(401))
			.setBody(simple("Invalid Login"));
		
		// patch patient
		from(getUpdatePatientAddressDirect())
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
