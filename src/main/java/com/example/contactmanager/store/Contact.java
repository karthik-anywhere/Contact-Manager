package com.example.contactmanager.store;

public class Contact {
	private String firstName, lastName, email;

	Contact(String firstName, String lastName, String email) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Contact getContact() {
		return this;
	}

	public static String convertName(String name) {
		String convertedName;
		convertedName = name.substring(0, 1).toUpperCase();
		convertedName = convertedName + name.substring(1).toLowerCase();
		return convertedName;
	}

	public String getFirstName() {
		if (firstName != null) {
			return convertName(firstName);
		} else {
			return null;
		}
	}

	public String getLastName() {
		if (lastName != null) {
			return convertName(lastName);
		} else {
			return null;
		}
	}

	public String getEmail() {
		return email;
	}

}
