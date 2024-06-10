package com.citiustech.processors;
import java.util.LinkedHashMap;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DiagnosisBackupProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		LinkedHashMap<String, Object> PatientDemographic=(LinkedHashMap<String, Object>) exchange.getIn().getBody(LinkedHashMap.class).get("PatientDemographicDetails");
		LinkedHashMap<String, Object> PatientTreatment =(LinkedHashMap<String, Object>) exchange.getIn().getBody(LinkedHashMap.class).get("PatientTreatmentDetails");
		
		LinkedHashMap<String,Object> newBody= new LinkedHashMap<>();
		newBody.put("Patientid", PatientDemographic.get("PatientId"));
		newBody.put("PatientDiagnosis", PatientTreatment.get("DiagnosisDetails"));
		exchange.getIn().setBody(newBody, LinkedHashMap.class);	
	}
}
