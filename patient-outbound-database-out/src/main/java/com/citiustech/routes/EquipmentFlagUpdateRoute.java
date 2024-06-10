package com.citiustech.routes;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import org.apache.activemq.ConnectionFailedException;
import org.apache.camel.LoggingLevel;
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
		
		//error Handling
		// ActiveMQ connection Exception
		onException(ConnectionFailedException.class)
		.handled(true)
		.log("ActiveMQ Connection Failed : ${exception.message}")
		.maximumRedeliveries(3)
		.maximumRedeliveryDelay("1000")
		.retryAttemptedLogLevel(LoggingLevel.WARN);
		
		//SQL Exception 
		onException(SQLException.class)
		.handled(true)
		.log("SQL Exception occurred: ${exception.message}");		
				
		//Default Error Handler
		onException(Exception.class)
		.handled(true)
		.log("Exception occurred: ${exception.message}");	
			
		//Getting data from ActiveMQ topic
		from(getPatientXlateTopic())
		.log(LoggingLevel.INFO,"Patient Data Received from activeMQ topic")
		.unmarshal().json(JsonLibrary.Jackson,LinkedHashMap.class)
		.log("Patient Data: ${body} ")
        .to(getDatabaseEquipmentFlagUpdateDirect());

		//database Update route for changing Equipment Status
		from(getDatabaseEquipmentFlagUpdateDirect())
		.setHeader("PatientStatus",simple("${body[patientTreatmentDetails][diagnosisDetails][patientStatus]}"))
		.setHeader("PatientId",simple("${body[patientDemographicDetails][PatientId]}"))
		.choice()
		.when(header("PatientStatus").isEqualTo("Active"))
		.to(getEquipmentflagActiveSqlQuery())
		.otherwise()
		.to(getEquipmentflagInactiveSqlQuery())
		.end()
		.log(LoggingLevel.INFO,"Equipment Flag got updated according to the Patient Status");		
	}
}