package com.citiustech.models;
import java.io.Serializable;

public class EquipmentDetails{
    
	private String EquipmentName;
    private String EquipmentStatus;

    // Getters and Setters
    public String getEquipmentName() {
        return EquipmentName;
    }
    public void setEquipmentName(String equipmentName) {
        EquipmentName = equipmentName;
    }
    public String getEquipmentStatus() {
        return EquipmentStatus;
    }
    public void setEquipmentStatus(String equipmentStatus) {
        EquipmentStatus = equipmentStatus;
    }
}