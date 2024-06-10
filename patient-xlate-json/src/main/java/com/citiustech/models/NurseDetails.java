package com.citiustech.models;
import java.io.Serializable;

public class NurseDetails{
   
	private String NurseId;
    private String NurseName;
    private String NurseGender;
    private String NurseEmail;

    // Getters and Setters
    public String getNurseId() {
        return NurseId;
    }
    public void setNurseId(String nurseId) {
        NurseId = nurseId;
    }
    public String getNurseName() {
        return NurseName;
    }
    public void setNurseName(String nurseName) {
        NurseName = nurseName;
    }
    public String getNurseGender() {
        return NurseGender;
    }
    public void setNurseGender(String nurseGender) {
        NurseGender = nurseGender;
    }
    public String getNurseEmail() {
        return NurseEmail;
    }
    public void setNurseEmail(String nurseEmail) {
        NurseEmail = nurseEmail;
    }
}
