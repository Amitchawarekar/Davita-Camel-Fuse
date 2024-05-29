package com.citiustech.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Nurse {

	@Id
	@Column(name = "nurse_id")
	private int nurseId;
	@Column(name = "first_name")
	private String firstName;
	@Column(name = "last_name")
	private String lastName;
	private String gender;
	@Column(name = "contact_number")
	private String contactNumber;
	private String email;

	public Nurse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getNurseId() {
		return nurseId;
	}

	public void setNurseId(int nurseId) {
		this.nurseId = nurseId;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Nurse [nurseId=" + nurseId + ", firstName=" + firstName + ", lastName=" + lastName + ", gender="
				+ gender + ", contactNumber=" + contactNumber + ", email=" + email + "]";
	}

	
}
