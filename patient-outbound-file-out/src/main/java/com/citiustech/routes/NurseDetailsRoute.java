package com.citiustech.routes;

import java.util.LinkedHashMap;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class NurseDetailsRoute extends RouteBuilder {
	
	public String patientXlateTopic;
	public String nurseStatusDetailsDirect;
	public String activeNurseFilePath;
	public String inactiveNurseFilePath;
	
	public String getPatientXlateTopic() {
		return patientXlateTopic;
	}
	public void setPatientXlateTopic(String patientXlateTopic) {
		this.patientXlateTopic = patientXlateTopic;
	}


	public String getNurseStatusDetailsDirect() {
		return nurseStatusDetailsDirect;
	}

	public void setNurseStatusDetailsDirect(String nurseStatusDetailsDirect) {
		this.nurseStatusDetailsDirect = nurseStatusDetailsDirect;
	}


	public String getActiveNurseFilePath() {
		return activeNurseFilePath;
	}

	public void setActiveNurseFilePath(String activeNurseFilePath) {
		this.activeNurseFilePath = activeNurseFilePath;
	}

	public String getInactiveNurseFilePath() {
		return inactiveNurseFilePath;
	}

	public void setInactiveNurseFilePath(String inactiveNurseFilePath) {
		this.inactiveNurseFilePath = inactiveNurseFilePath;
	}


	@Override
	public void configure() throws Exception {
		from(getPatientXlateTopic())
		.unmarshal().json(JsonLibrary.Jackson,LinkedHashMap.class)
		.log("Data from Xlate: ${body} ")
        .to(getNurseStatusDetailsDirect());
 
		//Nurse Detail To File Route
		from(getNurseStatusDetailsDirect())
		.log("Sending Active and Inactive Nurses to appropriate file route")
		.setHeader("PatientStatus",simple("${body[PatientTreatmentDetails][DiagnosisDetails][PatientStatus]}"))
		.setHeader("PatientId",simple("${body[PatientDemographicDetails][PatientId]}"))
		.setHeader("CamelFileName",simple("NurseId-" +"${body[PatientTreatmentDetails][nursedetails][NurseId]}"))
		.setBody(simple("${body[PatientTreatmentDetails][nursedetails]}"))
		.marshal().json(JsonLibrary.Jackson)
		.choice()
	 	.when(header("PatientStatus").isEqualTo("Active"))
	 			.to(getActiveNurseFilePath())
	 		.otherwise()
	 			.to(getInactiveNurseFilePath());
	}

}
