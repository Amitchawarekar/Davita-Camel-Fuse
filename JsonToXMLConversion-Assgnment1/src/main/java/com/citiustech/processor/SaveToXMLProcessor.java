package com.citiustech.processor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SaveToXMLProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		String filePath = (String) exchange.getIn().getHeader("FilePath");
		File file = new File(filePath);
		try (FileWriter fileWriter = new FileWriter(file)){
			fileWriter.write(exchange.getIn().getBody(String.class));
			fileWriter.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
