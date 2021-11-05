package com.example.contactmanager.store.hashstorage;

import java.util.HashMap;

import com.example.contactmanager.store.Contact;

public class HashStorage {
	private static HashStorage hs;
	private HashMap<String, Contact> h = new HashMap<String, Contact>();

	private HashStorage() {
	}

	public static HashStorage getInstance() {

		if (hs == null) {
			hs = new HashStorage();
		}
		return hs;
	}

	public HashMap<String, Contact> getMap() {
		return h;
	}
}
