package com.example.contactmanager.contact;

import com.google.gson.Gson;
import com.example.contactmanager.store.ContactStore;

public class ContactSearch {

	Gson gson = new Gson();
	ContactStore contact = new ContactStore();

	public String getDataFromStore(String firstName, String lastName, String email) {

		if (firstName != null && lastName != null && email != null) {
			return gson.toJson(contact.searchByALL(firstName, lastName, email));
		} else if (firstName != null) {
			return gson.toJson(contact.searchByFirstName(firstName));
		} else if (lastName != null) {
			return gson.toJson(contact.searchBylastName(lastName));
		} else if (email != null) {
			return gson.toJson(contact.searchByEmail(email));
		} else {
			return gson.toJson(contact.getContactsFromStore());
		}
	}

	public String getContactsFromStore() {
		return gson.toJson(contact.getContactsFromStore());
	}

	public String getDataFromStoreById(String id) {
		return gson.toJson(contact.getContactsFromStore(id));
	}

}
