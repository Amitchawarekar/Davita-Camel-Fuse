package com.citiustech.models;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class PatientDemographicDetails {
	
	@JsonProperty("PatientId")
    private String patientId;
	
	@JsonProperty("PatientFirstName")
    private String patientFirstName;
	
	@JsonProperty("PatientLastName")
    private String patientLastName;
	
	@JsonProperty("PatientAge")
    private int patientAge;
	
	@JsonProperty("PatientDOB")
    private String patientDOB;
	
	@JsonProperty("PatientGender")
    private String patientGender;
	
	@JsonProperty("PatientEmail")
    private String patientEmail;
    
    
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientFirstName() {
		return patientFirstName;
	}
	public void setPatientFirstName(String patientFirstName) {
		this.patientFirstName = patientFirstName;
	}
	public String getPatientLastName() {
		return patientLastName;
	}
	public void setPatientLastName(String patientLastName) {
		this.patientLastName = patientLastName;
	}
	public int getPatientAge() {
		return patientAge;
	}
	public void setPatientAge(int patientAge) {
		this.patientAge = patientAge;
	}
	public String getPatientDOB() {
		return patientDOB;
	}
	public void setPatientDOB(String patientDOB) {
		this.patientDOB = patientDOB;
	}
	public String getPatientGender() {
		return patientGender;
	}
	public void setPatientGender(String patientGender) {
		this.patientGender = patientGender;
	}
	public String getPatientEmail() {
		return patientEmail;
	}
	public void setPatientEmail(String patientEmail) {
		this.patientEmail = patientEmail;
	}
	@Override
	public String toString() {
		return "PatientDemographicDetails [patientId=" + patientId + ", patientFirstName=" + patientFirstName
				+ ", patientLastName=" + patientLastName + ", patientAge=" + patientAge + ", patientDOB=" + patientDOB
				+ ", patientGender=" + patientGender + ", patientEmail=" + patientEmail + "]";
	}
}
