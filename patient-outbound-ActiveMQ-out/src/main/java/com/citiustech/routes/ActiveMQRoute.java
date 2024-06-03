package com.citiustech.routes;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.citiustech.processors.DiagnosisBackupProcessor;

public class ActiveMQRoute extends RouteBuilder {
	
	public String patientXlateTopic;
	public String patientDiagnosisDetailsTopic;
	public String patientDiagnosisBackupDirect;
	
	public String getPatientXlateTopic() {
		return patientXlateTopic;
	}
	public void setPatientXlateTopic(String patientXlateTopic) {
		this.patientXlateTopic = patientXlateTopic;
	}

	public String getPatientDiagnosisDetailsTopic() {
		return patientDiagnosisDetailsTopic;
	}

	public void setPatientDiagnosisDetailsTopic(String patientDiagnosisDetailsTopic) {
		this.patientDiagnosisDetailsTopic = patientDiagnosisDetailsTopic;
	}
	
	public String getPatientDiagnosisBackupDirect() {
		return patientDiagnosisBackupDirect;
	}
	public void setPatientDiagnosisBackupDirect(String patientDiagnosisBackupDirect) {
		this.patientDiagnosisBackupDirect = patientDiagnosisBackupDirect;
	}
	@Override
	public void configure() throws Exception {
		
		from(getPatientXlateTopic())
		.unmarshal().json(JsonLibrary.Jackson,LinkedHashMap.class)
		.log("Data received after Xslate: ${body} ")
        .to(getPatientDiagnosisBackupDirect());

		//Patient Backup Route 
		from(getPatientDiagnosisBackupDirect())
		.log("Patient Diagnosis Data Backup Route")
		.process(new DiagnosisBackupProcessor())
		.marshal().json(JsonLibrary.Jackson)
		.to(getPatientDiagnosisDetailsTopic());
	}
}
