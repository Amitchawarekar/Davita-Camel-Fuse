package com.citiustech.models;
import java.io.Serializable;

public class DiagnosisDetails {
   
	private String DiagnosisDisease;
    private String DiagnosisSymptoms;
    private String PatientStatus;

    // Getters and Setters
    public String getDiagnosisDisease() {
        return DiagnosisDisease;
    }
    public void setDiagnosisDisease(String diagnosisDisease) {
        DiagnosisDisease = diagnosisDisease;
    }
    public String getDiagnosisSymptoms() {
        return DiagnosisSymptoms;
    }
    public void setDiagnosisSymptoms(String diagnosisSymptoms) {
        DiagnosisSymptoms = diagnosisSymptoms;
    }
    public String getPatientStatus() {
        return PatientStatus;
    }
    public void setPatientStatus(String patientStatus) {
        PatientStatus = patientStatus;
    }
}