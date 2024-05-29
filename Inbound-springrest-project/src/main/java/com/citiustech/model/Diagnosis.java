package com.citiustech.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Diagnosis {

	@Id
	@Column(name = "diagnosis_id")
	private int diagnosisId;
	private String diagnosis;
	
	@Column(name="diagnosis_date")
	private Date diagnosisDate;
	private String description;
	
	public int getDiagnosisId() {
		return diagnosisId;
	}
	public void setDiagnosisId(int diagnosisId) {
		this.diagnosisId = diagnosisId;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public Date getDiagnosisDate() {
		return diagnosisDate;
	}
	public void setDiagnosisDate(Date diagnosisDate) {
		this.diagnosisDate = diagnosisDate;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@Override
	public String toString() {
		return "Diagnosis [diagnosisId=" + diagnosisId + ", diagnosis=" + diagnosis + ", diagnosisDate=" + diagnosisDate
				+ ", description=" + description + "]";
	}

	

}
