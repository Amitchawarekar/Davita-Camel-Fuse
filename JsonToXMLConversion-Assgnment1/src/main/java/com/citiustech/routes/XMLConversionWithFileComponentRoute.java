package com.citiustech.routes;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class XMLConversionWithFileComponentRoute extends RouteBuilder {

	public String sourceURI;
	public String filecomponentDestinationURI;
	
	public String getSourceURI() {
		return sourceURI;
	}

	public void setSourceURI(String sourceURI) {
		this.sourceURI = sourceURI;
	}

	public String getFilecomponentDestinationURI() {
		return filecomponentDestinationURI;
	}

	public void setFilecomponentDestinationURI(String filecomponentDestinationURI) {
		this.filecomponentDestinationURI = filecomponentDestinationURI;
	}

	@Override
	public void configure() throws Exception {
		/* 
		 * You can define here the Camel Route.
		 * For instance, start by calling from() method, then use the Fluent API to build the Camel Route definition.
		 */
		
		from(getSourceURI())
        .log("JSON file name: ${header.CamelFileName}")
	    .unmarshal().json(JsonLibrary.Jackson)
	    .log("JSON file Content: ${body}")
	    .log("Number of records in JSON: ${body.size()}")
	    .marshal().jacksonxml(true)
	    .log("Converted XML content: ${body}")
	    .to(getFilecomponentDestinationURI())
        .log("XML file saved to: data/output/WithFileComponent/PersonDetailsXML.xml");		
	}
}
