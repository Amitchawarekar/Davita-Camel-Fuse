package com.citiustech.model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PatientDemographicDetails {

	@Id
	@Column(name = "patient_id")
	private int patientId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	@Column(name="dob")
	private String dateOfBirth;
	private String gender;
	private String address;
	@Column(name = "phone_number")
	private String phoneNumber;
	private String email;

	public PatientDemographicDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getPatientId() {
		return patientId;
	}

	public void setPatientId(int patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "PatientDemographicDetails [patientId=" + patientId + ", firstName=" + firstName + ", lastName="
				+ lastName + ", dateOfBirth=" + dateOfBirth + ", gender=" + gender + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", email=" + email + "]";
	}

	

}
