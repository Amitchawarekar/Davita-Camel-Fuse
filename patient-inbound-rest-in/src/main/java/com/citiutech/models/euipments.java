package com.citiutech.models;

public class euipments {
	
	public String equipment_id;
	public String patient_id;
	public String equipment_name;
	public String usage_details;
	
	public String getEquipment_id() {
		return equipment_id;
	}
	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getEquipment_name() {
		return equipment_name;
	}
	public void setEquipment_name(String equipment_name) {
		this.equipment_name = equipment_name;
	}
	public String getUsage_details() {
		return usage_details;
	}
	public void setUsage_details(String usage_details) {
		this.usage_details = usage_details;
	}
	@Override
	public String toString() {
		return "euipments [equipment_id=" + equipment_id + ", patient_id=" + patient_id + ", equipment_name="
				+ equipment_name + ", usage_details=" + usage_details + "]";
	}
}
