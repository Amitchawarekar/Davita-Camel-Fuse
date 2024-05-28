package com.citiutech.models;

import java.util.Date;

public class diagnoses {
	
	public String diagnosis_id;
	public String patient_id;
	public Date diagnosis_date;
	public String diagnosis_details;
	public String getDiagnosis_id() {
		return diagnosis_id;
	}
	public void setDiagnosis_id(String diagnosis_id) {
		this.diagnosis_id = diagnosis_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public Date getDiagnosis_date() {
		return diagnosis_date;
	}
	public void setDiagnosis_date(Date diagnosis_date) {
		this.diagnosis_date = diagnosis_date;
	}
	public String getDiagnosis_details() {
		return diagnosis_details;
	}
	public void setDiagnosis_details(String diagnosis_details) {
		this.diagnosis_details = diagnosis_details;
	}
	@Override
	public String toString() {
		return "diagnoses [diagnosis_id=" + diagnosis_id + ", patient_id=" + patient_id + ", diagnosis_date="
				+ diagnosis_date + ", diagnosis_details=" + diagnosis_details + "]";
	}
	
	
}
