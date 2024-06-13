package com.citiustech.routes;
import org.apache.activemq.ConnectionFailedException;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.json.simple.JsonObject;
import org.apache.camel.model.dataformat.JsonLibrary;
import com.citiustech.processors.PatientDataTransformProcessor;
import com.citiustech.models.DiagnosisDetails;
import com.citiustech.models.Patient;
import com.citiustech.models.PatientDemographicDetails;
import com.citiustech.models.PatientInfo;
import com.citiustech.models.PatientTreatmentDetails;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientTransformationRoute extends RouteBuilder {
	
	public String patientDetailsQueue;
	public String patientXlateTopic;

	public String getPatientDetailsQueue() {
		return patientDetailsQueue;
	}
	public void setPatientDetailsQueue(String patientDetailsQueue) {
		this.patientDetailsQueue = patientDetailsQueue;
	}
	public String getPatientXlateTopic() {
		return patientXlateTopic;
	}
	public void setPatientXlateTopic(String patientXlateTopic) {
		this.patientXlateTopic = patientXlateTopic;
	}

	@Override
	public void configure() throws Exception {
		//Exception Handling
		//ActiveMQ connection Exception
		onException(ConnectionFailedException.class)
		.handled(true)
		.log("ActiveMQ Connection Failed : ${exception.message}")
		.maximumRedeliveries(3)
		.maximumRedeliveryDelay("1000")
		.retryAttemptedLogLevel(LoggingLevel.ERROR);
		
		//Default Error Handler
		onException(Exception.class)
		.handled(true)
		.log("Exception occurred: ${exception.message}");			
		
		from(getPatientDetailsQueue())
		.log(LoggingLevel.INFO,"Patient Data received from ActiveMQ queue - ${body}")
		.unmarshal().json(JsonLibrary.Jackson,Patient.class)
		.log(LoggingLevel.INFO,"Patient Data is Transformed")
		.process(new PatientDataTransformProcessor())
		.marshal().json(JsonLibrary.Jackson)
		.to(getPatientXlateTopic())
		.log("${body}");
	}
}