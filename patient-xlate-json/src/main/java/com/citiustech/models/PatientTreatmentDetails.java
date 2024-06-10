package com.citiustech.models;
import java.io.Serializable;

public class PatientTreatmentDetails{
	private DiagnosisDetails DiagnosisDetails;
    private EquipmentDetails Equipment;
    private NurseDetails NurseDetails;

    // Getters and Setters
    public DiagnosisDetails getDiagnosisDetails() {
        return DiagnosisDetails;
    }
    public void setDiagnosisDetails(DiagnosisDetails diagnosisDetails) {
        DiagnosisDetails = diagnosisDetails;
    }
    public EquipmentDetails getEquipment() {
        return Equipment;
    }
    public void setEquipment(EquipmentDetails equipment) {
        Equipment = equipment;
    }
    public NurseDetails getNurseDetails() {
        return NurseDetails;
    }
    public void setNurseDetails(NurseDetails nurseDetails) {
        NurseDetails = nurseDetails;
    }

	@Override
	public String toString() {
		return "PatientTreatmentDetails [DiagnosisDetails=" + DiagnosisDetails + ", Equipment=" + Equipment
				+ ", NurseDetails=" + NurseDetails + "]";
	}
}
