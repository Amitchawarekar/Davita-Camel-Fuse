package com.citiustech.testcases;

import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class InboundTestSuite extends CamelBlueprintTestSupport {
	
	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/patient-inbound-rest-in-blueprint.xml";
	}
	
	@Test
	public void httpInboundRouteTest() throws Exception {
		context.getRouteDefinition("PatientReportRoute").adviceWith(context, new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
//				To replace the route input with a new endpoint
				replaceFromWith("direct:in");
//				Manipulates the route at the node IDs that matches the pattern.
//				weaveById("patientreportqueue")
//				   .replace()
//				   .setBody(constant("1001")).to("mock:result");
 
//				also replacing original endPoint with mock end point
				weaveByToUri("activemq:queue:patientDetailsQueue").replace().to("mock:result");
			}
		});
		context.start();
 
//		By using exchange method we are sending 1001 as a body
		Exchange exchnage = ExchangeBuilder.anExchange(context).withBody("1004").build();
		template.send("direct:in", exchnage);

		MockEndpoint mock = getMockEndpoint("mock:result");
		mock.expectedMessageCount(1);
		assertMockEndpointsSatisfied();
 
	}
	
	@Test
	public void testValidIDsInFile() throws Exception{
		context.getRouteDefinition("PatientReportRoute").adviceWith(context, new AdviceWithRouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				replaceFromWith("direct:in");
				weaveByToUri("http://localhost:8081/patient/").replace().to("mock:result");
			}
		});
		
		context.start();
		
		MockEndpoint mockResult = getMockEndpoint("mock:result");
		
		String fileContent = "1001\n1002\n1003\n1004";
		mockResult.expectedBodiesReceived("1001","1002","1003","1004");
		System.out.println(mockResult.toString());
		template.sendBody("direct:in",fileContent);
		MockEndpoint.assertIsSatisfied(context);
		context.stop();
	}
	
	@Test
	public void testEmptyFile() throws Exception {
		context.getRouteDefinition("PatientReportRoute").adviceWith(context, new AdviceWithRouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				replaceFromWith("direct:in");
				
				weaveByToUri("http://localhost:8081/patient/").replace().to("mock:result");
			}
		});
		
		context.start();
		
		MockEndpoint mockResult = getMockEndpoint("mock:result");
		String fileContent = "";
		mockResult.expectedMessageCount(0);
		
		template.sendBody("direct:in",fileContent);
		MockEndpoint.assertIsSatisfied(context);
		
		context.stop();
	}
	
	@Test
	public void testFileWithWhitespaces() throws Exception{
		context.getRouteDefinition("PatientReportRoute").adviceWith(context, new AdviceWithRouteBuilder() {
			
			@Override
			public void configure() throws Exception {
				// TODO Auto-generated method stub
				replaceFromWith("direct:in");
				weaveByToUri("http://localhost:8081/patient/").replace().to("mock:result");
				
			}
		
	});
	
	context.start();
	
	MockEndpoint mockResult = getMockEndpoint("mock:result");
	
	String fileContent = "1001 \n1002 \n1003 \n1004 ";
	mockResult.expectedBodiesReceived("1001","1002","1003","1004");
	template.sendBody("direct:in",fileContent);
	MockEndpoint.assertIsSatisfied(context);
	context.stop();
	}
}

