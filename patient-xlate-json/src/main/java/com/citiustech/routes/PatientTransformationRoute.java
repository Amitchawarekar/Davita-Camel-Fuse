package com.citiustech.routes;

import org.apache.camel.Exchange;
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

	public String getTransformedJson() {
		return transformedJson;
	}

	public void setTransformedJson(String transformedJson) {
		this.transformedJson = transformedJson;
	}


	@Override
	public void configure() throws Exception {
		System.out.println(getTransformedJson());

        from("activemq:queue:patientDetailsQueue")
        .unmarshal().json(JsonLibrary.Jackson)
        .setBody(simple(getTransformedJson()))
        .to("activemq:queue:patientXlateQueue");





}

}