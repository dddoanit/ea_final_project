package edu.mum.cs544.project.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

	private String city;
	private String state;
	private String country;
	private String zipcode;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	@Override
	public String toString() {
		String addr = "";

		if (country != null && !country.isEmpty()) {
			addr += country;
		}
		
		if (city != null && !city.isEmpty()) {
			addr +=", " + city;
		}
		if (state != null && !state.isEmpty()) {
			addr += ", " + state;
		}
		 
		if (zipcode != null && !zipcode.isEmpty()) {
			addr += ", " + zipcode;
		} 
		return addr;
	}

}
