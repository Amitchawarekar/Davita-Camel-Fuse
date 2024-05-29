package com.citiustech.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Equipment {

	@Id
	private int equipmentId;
	private String equipmentName;
	private int quantity;
	private String usageDetails;
	public Equipment() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(int equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getUsageDetails() {
		return usageDetails;
	}
	public void setUsageDetails(String usageDetails) {
		this.usageDetails = usageDetails;
	}
	@Override
	public String toString() {
		return "Equipment [equipmentId=" + equipmentId + ", equipmentName=" + equipmentName + ", quantity=" + quantity
				+ ", usageDetails=" + usageDetails + "]";
	}
}
