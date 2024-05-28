package com.citiutech.models;

public class patient_nurses {
	
	public String patient_nurse_id;
	public String patient_id;
	public String nurse_id;
	public String assignment_date;
	public String getPatient_nurse_id() {
		return patient_nurse_id;
	}
	public void setPatient_nurse_id(String patient_nurse_id) {
		this.patient_nurse_id = patient_nurse_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getNurse_id() {
		return nurse_id;
	}
	public void setNurse_id(String nurse_id) {
		this.nurse_id = nurse_id;
	}
	public String getAssignment_date() {
		return assignment_date;
	}
	public void setAssignment_date(String assignment_date) {
		this.assignment_date = assignment_date;
	}
	
	@Override
	public String toString() {
		return "patient_nurses [patient_nurse_id=" + patient_nurse_id + ", patient_id=" + patient_id + ", nurse_id="
				+ nurse_id + ", assignment_date=" + assignment_date + "]";
	}
	
	
}
