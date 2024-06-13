package com.citiustech.processors;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import com.citiustech.models.DiagnosisDetails;
import com.citiustech.models.EquipmentDetails;
import com.citiustech.models.NurseDetails;
import com.citiustech.models.Patient;
import com.citiustech.models.PatientDemographicDetails;
import com.citiustech.models.PatientInfo;
import com.citiustech.models.PatientTreatmentDetails;

public class PatientDataTransformProcessor implements Processor {

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		Patient patient = exchange.getIn().getBody(Patient.class);
		exchange.getIn().setBody(patient);
		
		PatientDemographicDetails patientDemographicDetails = new PatientDemographicDetails();
		patientDemographicDetails.setPatientId(patient.getPatientId());
		patientDemographicDetails.setPatientFirstName(patient.getPatientFirstName());
		patientDemographicDetails.setPatientLastName(patient.getPatientLastName());
		patientDemographicDetails.setPatientAge(patient.getPatientAge());
		patientDemographicDetails.setPatientDOB(patient.getPatientDOB());
		patientDemographicDetails.setPatientGender(patient.getPatientGender());
		patientDemographicDetails.setPatientEmail(patient.getPatientEmail());
				
		DiagnosisDetails diagnosisDetails = new DiagnosisDetails();
		diagnosisDetails.setDiagnosisDisease(patient.getDiagnosisDisease());
		diagnosisDetails.setDiagnosisSymptoms(patient.getDiagnosisSymptoms());
		diagnosisDetails.setPatientStatus(patient.getPatientStatus());
		
		NurseDetails nurseDetails = new NurseDetails();
		nurseDetails.setNurseId(patient.getNurseId());
		nurseDetails.setNurseName(patient.getNurseName());
		nurseDetails.setNurseGender(patient.getNurseGender());
		nurseDetails.setNurseEmail(patient.getNurseEmail());
		
		EquipmentDetails equipmentDetails = new EquipmentDetails();
		equipmentDetails.setEquipmentName(patient.getEquipmentName());
		equipmentDetails.setEquipmentStatus(patient.getEquipmentStatus());
		
		PatientTreatmentDetails patientTreatmentDetails = new PatientTreatmentDetails();
		patientTreatmentDetails.setDiagnosisDetails(diagnosisDetails);
		patientTreatmentDetails.setEquipment(equipmentDetails);
		patientTreatmentDetails.setNurseDetails(nurseDetails);
		
		PatientInfo patientInfo = new PatientInfo();
		patientInfo.setPatientDemographicDetails(patientDemographicDetails);
		patientInfo.setPatientTreatmentDetails(patientTreatmentDetails);
				
		exchange.getIn().setBody(patientInfo);
	}

}
