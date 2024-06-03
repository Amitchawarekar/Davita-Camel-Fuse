package com.citiustech.routes;

import java.util.LinkedHashMap;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;

public class EquipmentFlagUpdateRoute  extends RouteBuilder{
	
	public String equipmentflagInactiveSqlQuery;
	public String equipmentflagActiveSqlQuery;
	public String patientXlateTopic;
	public String databaseEquipmentFlagUpdateDirect;
	
	
	public String getEquipmentflagInactiveSqlQuery() {
		return equipmentflagInactiveSqlQuery;
	}

	public void setEquipmentflagInactiveSqlQuery(String equipmentflagInactiveSqlQuery) {
		this.equipmentflagInactiveSqlQuery = equipmentflagInactiveSqlQuery;
	}

	public String getEquipmentflagActiveSqlQuery() {
		return equipmentflagActiveSqlQuery;
	}

	public void setEquipmentflagActiveSqlQuery(String equipmentflagActiveSqlQuery) {
		this.equipmentflagActiveSqlQuery = equipmentflagActiveSqlQuery;
	}

	public String getPatientXlateTopic() {
		return patientXlateTopic;
	}

	public void setPatientXlateTopic(String patientXlateTopic) {
		this.patientXlateTopic = patientXlateTopic;
	}

	public String getDatabaseEquipmentFlagUpdateDirect() {
		return databaseEquipmentFlagUpdateDirect;
	}

	public void setDatabaseEquipmentFlagUpdateDirect(String databaseEquipmentFlagUpdateDirect) {
		this.databaseEquipmentFlagUpdateDirect = databaseEquipmentFlagUpdateDirect;
	}
	
	@Override
	public void configure() throws Exception {
		from(getPatientXlateTopic())
		.unmarshal().json(JsonLibrary.Jackson,LinkedHashMap.class)
		.log("Data: ${body} ")
        .to(getDatabaseEquipmentFlagUpdateDirect());

		//database Update route for changing Equipment Status
		from(getDatabaseEquipmentFlagUpdateDirect())
		.log("Equipment Status update in database route")
			.setHeader("PatientStatus",simple("${body[PatientTreatmentDetails][DiagnosisDetails][PatientStatus]}"))
			.setHeader("PatientId",simple("${body[PatientDemographicDetails][PatientId]}"))
			.log("header.PatientStatus")
		.choice()
		 	.when(header("PatientStatus").isEqualTo("Active"))
		 		.to(getEquipmentflagActiveSqlQuery())
		.otherwise()
				.to(getEquipmentflagInactiveSqlQuery());

	}

}