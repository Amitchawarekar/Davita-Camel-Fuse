package com.citiustech.routes;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.activemq.ConnectionFailedException;
import org.apache.camel.LoggingLevel;
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

		// ActiveMQ connection Exception
		onException(ConnectionFailedException.class)
		.handled(true)
		.log("ActiveMQ Connection Failed : ${exception.message}")
		.maximumRedeliveries(3)
		.maximumRedeliveryDelay("1000")
		.retryAttemptedLogLevel(LoggingLevel.WARN);
		
		//Default Error Handler
		onException(Exception.class)
		.handled(true)
		.log("Exception occurred: ${exception.message}");		

		// Route to send Diagnosis data to topic as backup
		from(getPatientXlateTopic())
		.unmarshal().json(JsonLibrary.Jackson,LinkedHashMap.class)
		.log(LoggingLevel.INFO,"Patient Data received from ActiveMQ topic")
		.log("${body}")
		.log("Patient Data received : ${body} ")
		.to(getPatientDiagnosisBackupDirect());

		from(getPatientDiagnosisBackupDirect())
		.process(new DiagnosisBackupProcessor())
		.log(LoggingLevel.INFO,"Patient Data is processed and diagnosis data is fetched with patientId")
		.marshal().json(JsonLibrary.Jackson)
		.to(getPatientDiagnosisDetailsTopic())
		.log(LoggingLevel.INFO,"Patient Diagnosis Data is sent to activeMQ topic as backup - ${body}");
	}
}
