package com.citiustech.models;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientInfo {
	
	@JsonProperty("PatientDemographicDetails")
    private PatientDemographicDetails PatientDemographicDetails;
	
	@JsonProperty("PatientTreatmentDetails")
    private PatientTreatmentDetails PatientTreatmentDetails;

    // Getters and Setters
    public PatientDemographicDetails getPatientDemographicDetails() {
        return PatientDemographicDetails;
    }
    public void setPatientDemographicDetails(PatientDemographicDetails patientDemographicDetails) {
        PatientDemographicDetails = patientDemographicDetails;
    }
    public PatientTreatmentDetails getPatientTreatmentDetails() {
        return PatientTreatmentDetails;
    }
    public void setPatientTreatmentDetails(PatientTreatmentDetails patientTreatmentDetails) {
        PatientTreatmentDetails = patientTreatmentDetails;
    }
}
