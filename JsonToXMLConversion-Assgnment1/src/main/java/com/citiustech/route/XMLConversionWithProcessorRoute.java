package com.citiustech.route;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

import com.citiustech.processor.ReadJsonFileProcessor;
import com.citiustech.processor.SaveToXMLProcessor;


public class XMLConversionWithProcessorRoute extends RouteBuilder {

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
		.setHeader("FilePath").constant("data/output/WithoutFileComponent/PersonalDetailsXML.xml")
		.process(new SaveToXMLProcessor());
		
//		from("timer:foo?period=60000").bean(JsonImpl2FileReader.class, "readJson").unmarshal().json(JsonLibrary.Jackson)
//				.log("JSON Content").log("${body}").log("Number of records in JSON: ${body.size()}").marshal()
//				.jacksonxml(true).log("Converted XML: ${body}").log("${body}")
//				.bean(JsonImpl2FileReader.class, "saveXMLtofile(${body},'data/output/Converted.xml')");
	}
}