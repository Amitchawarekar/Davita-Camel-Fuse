package com.citiustech.routes;

import org.apache.activemq.ConnectionFailedException;
import org.apache.camel.Exchange;
import org.apache.camel.LoggingLevel;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.json.simple.JsonObject;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PatientTransformationRoute extends RouteBuilder {

	public String transformedJson;
	public String patientDetailsQueue;
	public String patientXlateTopic;

	public String getTransformedJson() {
		return transformedJson;
	}

	public void setTransformedJson(String transformedJson) {
		this.transformedJson = transformedJson;
	}
	
	

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
		
		
		//Transformation Route
		from(getPatientDetailsQueue())
		.unmarshal().json(JsonLibrary.Jackson)
		.setBody(simple(getTransformedJson()))
		.to(getPatientXlateTopic());
	}

}