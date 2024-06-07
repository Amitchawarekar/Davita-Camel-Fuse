package com.citiustech.testcases;

import org.apache.camel.Exchange;
import org.apache.camel.builder.AdviceWithRouteBuilder;
import org.apache.camel.builder.ExchangeBuilder;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.junit.Test;

public class NurseDetailsRouteTest extends CamelBlueprintTestSupport{
	
	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/patient-outbound-file-out-blueprint.xml";
	}
	
	@Test
	public void testActiveNurseRouting() throws Exception {
		context.getRouteDefinition("NurseRoute").adviceWith(context, new AdviceWithRouteBuilder() {
			@Override
			public void configure() throws Exception {
//				To replace the route input with a new endpoint
				replaceFromWith("direct:in");
//				
				weaveByToUri("file:src/main/resources/data/Nurse/Active/").replace().to("mock:result");
			}
		});
		
		context.start();
		
		MockEndpoint mockResult = getMockEndpoint("mock:result");
		
		String fileContent = "1001\n1002\n1003\n1004";
		mockResult.expectedBodiesReceived("1001","1002","1003","1004");;
		System.out.println(mockResult.toString());
		template.sendBody("direct:in",fileContent);
		MockEndpoint.assertIsSatisfied(context);
		context.stop();
 
	}

}
