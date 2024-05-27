package com.citiustech.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.citiustech.processors.ReadJsonFileProcessor;
import com.citiustech.processors.SaveToXMLProcessor;


public class XMLConversionWithProcessorRoute extends RouteBuilder {
	
	public String withoutfilecomponentDestinationURI;
	
	public String getWithoutfilecomponentDestinationURI() {
		return withoutfilecomponentDestinationURI;
	}

	public void setWithoutfilecomponentDestinationURI(String withoutfilecomponentDestinationURI) {
		this.withoutfilecomponentDestinationURI = withoutfilecomponentDestinationURI;
	}

	@Override
	public void configure() throws Exception {
//		// TODO Auto-generated method stub

		from("timer:foo?period=60000")
		.process(new ReadJsonFileProcessor())
		.unmarshal().json(JsonLibrary.Jackson)
		.log("JSON Content")
		.log("${body}")
		.log("Number of records in JSON: ${body.size()}")
		.marshal().jacksonxml(true)
		.log("Converted XML: ${body}")
		.setHeader("FilePath").constant(getWithoutfilecomponentDestinationURI())
		.process(new SaveToXMLProcessor());
	}
}