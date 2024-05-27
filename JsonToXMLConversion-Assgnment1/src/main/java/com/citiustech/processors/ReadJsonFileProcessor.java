package com.citiustech.processors;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class ReadJsonFileProcessor implements Processor {
	
	public String jsonFileSourceURI;

	public String getJsonFileSourceURI() {
		return jsonFileSourceURI;
	}
	public void setJsonFileSourceURI(String jsonFileSourceURI) {
		this.jsonFileSourceURI = jsonFileSourceURI;
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		File file = new File(getJsonFileSourceURI());
		if (!file.exists()) {
			exchange.getIn().setBody("File Not Found");
		}
		StringBuilder Json = new StringBuilder();
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				Json.append(line);
			}
		} catch (IOException e) {
			exchange.getIn().setBody("Error is " + e.getMessage());
		}
		exchange.getIn().setBody(Json.toString());
	}
}
