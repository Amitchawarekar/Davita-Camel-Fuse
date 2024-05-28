package com.citiutech.models;

public class nurses {
	
	public String nurse_id;
	public String first_name;
	public String last_name;
	public String contact_number;
	public String email;
	
	public String getNurse_id() {
		return nurse_id;
	}
	public void setNurse_id(String nurse_id) {
		this.nurse_id = nurse_id;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getContact_number() {
		return contact_number;
	}
	public void setContact_number(String contact_number) {
		this.contact_number = contact_number;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return "nurses [nurse_id=" + nurse_id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", contact_number=" + contact_number + ", email=" + email + "]";
	}
	
	
}
