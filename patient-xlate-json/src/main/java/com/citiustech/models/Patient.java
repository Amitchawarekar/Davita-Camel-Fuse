package com.citiustech.models;
import java.io.Serializable;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Patient{
	
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
	
	@JsonProperty("DiagnosisDisease")
    private String diagnosisDisease =null;
	
	@JsonProperty("DiagnosisSymptoms")
    private String diagnosisSymptoms;
	
	@JsonProperty("PatientStatus")
    private String patientStatus;
	
	@JsonProperty("EquipmentName")
    private String equipmentName;
	
	@JsonProperty("EquipmentStatus")
    private String equipmentStatus;
	
	@JsonProperty("NurseId")
    private String nurseId;
	
	@JsonProperty("NurseName")
    private String nurseName;
	
	@JsonProperty("NurseGender")
    private String nurseGender;
	
	@JsonProperty("NurseEmail")
    private String nurseEmail;

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
	public String getDiagnosisDisease() {
		return diagnosisDisease;
	}
	public void setDiagnosisDisease(String diagnosisDisease) {
		this.diagnosisDisease = diagnosisDisease;
	}
	public String getDiagnosisSymptoms() {
		return diagnosisSymptoms;
	}
	public void setDiagnosisSymptoms(String diagnosisSymptoms) {
		this.diagnosisSymptoms = diagnosisSymptoms;
	}
	public String getPatientStatus() {
		return patientStatus;
	}
	public void setPatientStatus(String patientStatus) {
		this.patientStatus = patientStatus;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public String getEquipmentStatus() {
		return equipmentStatus;
	}
	public void setEquipmentStatus(String equipmentStatus) {
		this.equipmentStatus = equipmentStatus;
	}
	public String getNurseId() {
		return nurseId;
	}
	public void setNurseId(String nurseId) {
		this.nurseId = nurseId;
	}
	public String getNurseName() {
		return nurseName;
	}
	public void setNurseName(String nurseName) {
		this.nurseName = nurseName;
	}
	public String getNurseGender() {
		return nurseGender;
	}
	public void setNurseGender(String nurseGender) {
		this.nurseGender = nurseGender;
	}
	public String getNurseEmail() {
		return nurseEmail;
	}
	public void setNurseEmail(String nurseEmail) {
		this.nurseEmail = nurseEmail;
	}

	@Override
	public String toString() {
		return " [patientId=" + patientId + ", patientFirstName=" + patientFirstName
				+ ", patientLastName=" + patientLastName + ", patientAge=" + patientAge + ", patientDOB=" + patientDOB
				+ ", patientGender=" + patientGender + ", patientEmail=" + patientEmail + ", diagnosisDisease="
				+ diagnosisDisease + ", diagnosisSymptoms=" + diagnosisSymptoms + ", patientStatus=" + patientStatus
				+ ", equipmentName=" + equipmentName + ", equipmentStatus=" + equipmentStatus + ", nurseId=" + nurseId
				+ ", nurseName=" + nurseName + ", nurseGender=" + nurseGender + ", nurseEmail=" + nurseEmail + "]";
	}
}
