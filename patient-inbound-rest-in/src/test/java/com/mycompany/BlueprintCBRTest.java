package com.mycompany;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;

import org.apache.camel.EndpointInject;
import org.apache.camel.Exchange;
import org.apache.camel.Produce;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

/**
 * A unit test to verify the Camel route works as designed.
 */
public class BlueprintCBRTest extends CamelBlueprintTestSupport {
	
	@Test
    public void OneIdAtATimeTest() throws Exception {
		MockEndpoint fileEndpoint = getMockEndpoint("mock:test");
		template.sendBodyAndHeader("file:output", "1003\n1004",
				 Exchange.FILE_NAME, "patientIds.txt");
		fileEndpoint.expectedMessageCount(4);
	//	fileEndpoint.assertIsSatisfied();
		System.out.println(fileEndpoint);
    }
	
	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint-inbound.xml";
	}
	
	
	@Test
	public void queueInboundXlateRouteTest() throws Exception {
 
		context.getRouteDefinitions().get(1).adviceWith(context, new AdviceWithRouteBuilder() {
 
			@Override
			public void configure() throws Exception {
				replaceFromWith("direct:resultOut");
 
				weaveByToUri("activemq:queue:patientInboundDestinationQueue").replace().to("mock:mockQueue");
			}
		});
 
		context.start();
 
		Exchange exchange = ExchangeBuilder.anExchange(context).withBody("1001").build();
 
		template.sendBody("direct:resultOut", exchange);
 
		MockEndpoint mockEndpoint = getMockEndpoint("mock:mockQueue");
		mockEndpoint.expectedMessageCount(1);
 
		assertMockEndpointsSatisfied();
	}
	
}




