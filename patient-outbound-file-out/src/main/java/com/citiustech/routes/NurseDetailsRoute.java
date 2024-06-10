package com.citiustech.routes;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import org.apache.activemq.ConnectionFailedException;
import org.apache.camel.LoggingLevel;
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
		
		//Exception Handling
		// ActiveMQ connection Exception
		onException(ConnectionFailedException.class)
		.handled(true)
		.log("ActiveMQ Connection Failed : ${exception.message}")
		.maximumRedeliveries(3)
		.maximumRedeliveryDelay("1000")
		.retryAttemptedLogLevel(LoggingLevel.WARN);
		
		//File Not Found Exception
		onException(FileNotFoundException.class)
		.handled(true)
		.log("File Not Exist : ${exception.message}")
		.retryAttemptedLogLevel(LoggingLevel.WARN);
				
		//Default Error Handler
		onException(Exception.class)
		.handled(true)
		.log("Exception occurred: ${exception.message}");
		
		from(getPatientXlateTopic())
		.log(LoggingLevel.INFO,"Patient Data received from activeMQ topic")
		.unmarshal().json(JsonLibrary.Jackson,LinkedHashMap.class)
		.log(LoggingLevel.INFO,"Patient Data - ${body} ")
        .to(getNurseStatusDetailsDirect());
		
		
		//Nurse Detail To File Route
		from(getNurseStatusDetailsDirect()).routeId("NurseRoute")
		.log(LoggingLevel.INFO,"Sending Active and Inactive Nurses to appropriate file route")
		.setHeader("PatientStatus",simple("${body[patientTreatmentDetails][diagnosisDetails][patientStatus]}"))
		.setHeader("PatientId",simple("${body[patientDemographicDetails][PatientId]}"))
		.setHeader("CamelFileName",simple("NurseId-" +"${body[patientTreatmentDetails][nurseDetails][nurseId]}"))
		.setBody(simple("${body[PatientTreatmentDetails][nursedetails]}"))
		.marshal().jacksonxml()
		.choice()
	 	.when(header("PatientStatus").isEqualTo("Active"))
	 	.to(getActiveNurseFilePath())
	 	.otherwise()
	 	.to(getInactiveNurseFilePath())	
	 	.end();
	}
}