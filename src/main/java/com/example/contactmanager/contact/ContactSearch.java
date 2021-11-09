package com.example.contactmanager.contact;

import com.example.contactmanager.store.ContactDataStore;
import com.example.contactmanager.store.ContactHashStore;
import com.example.contactmanager.store.Store;
import com.google.gson.Gson;

public class ContactSearch {

	Gson gson = new Gson();
	Store contact = new ContactDataStore();

	public String getDataFromStore(String firstName, String lastName, String email) {

		if (firstName != null && lastName != null && email != null) {
			return gson.toJson(contact.searchByALL(firstName, lastName, email));
		} else if (firstName != null) {
			return gson.toJson(contact.searchByFirstName(firstName));
		} else if (lastName != null) {
			return gson.toJson(contact.searchByLastName(lastName));
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
