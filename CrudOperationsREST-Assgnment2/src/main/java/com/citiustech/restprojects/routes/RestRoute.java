package com.citiustech.restprojects.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;


public class RestRoute extends RouteBuilder {
	
	
	//Direct Variables
	public String AllPatientsDirect;
	public String patientByIdDirect;
	public String addPatientDirect;
	public String deletePatientDirect;
	public String putPatientDirect;
	public String updatePatientAddressDirect;
	
	//Direct Getters and setters
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
		// TODO Auto-generated method stub
		restConfiguration() // starts server and configure the server
		.component("spark-rest").port(8080).bindingMode(RestBindingMode.json);

		rest("/PatientData")
		.get("/all").to(getAllPatientsDirect())
		.get("/{id}").to(getPatientByIdDirect())
		.post().to(getAddPatientDirect())
		.patch("/{id}").to(getUpdatePatientAddressDirect())
		.delete("/{id}").to(getDeletePatientDirect())
		.put("/{id}").to(getPutPatientDirect());
	}

	

}
