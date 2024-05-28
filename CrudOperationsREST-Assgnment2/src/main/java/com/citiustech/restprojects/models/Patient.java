package com.citiustech.restprojects.models;

import java.io.Serializable;

public class Patient implements Serializable{
	
	private static final long serialVersionUID = 4450252617829248449L;
	int id;
	String firstName;
	String lastname;
	String address;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "Patient [id=" + id + ", firstName=" + firstName + ", lastname=" + lastname + ", address=" + address
				+ "]";
	}
}
