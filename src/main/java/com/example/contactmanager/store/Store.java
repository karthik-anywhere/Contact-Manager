package com.example.contactmanager.store;


import org.json.JSONException;
import org.json.JSONObject;


public interface Store {

	public Object getContactsFromStore() ;

	public Object getContactsFromStore(String id);

	// for test case , this method is overloaded by id
	public abstract String storeContactInStore(String id, String firstName, String lastName, String email);

	public abstract String storeContactInStore(String firstName, String lastName, String email);

	public abstract String updateContactInStore(String key, Contact contactObj);

	public abstract Object searchByFirstName(String firstName);

	public abstract Object searchByLastName(String lastName);

	public abstract Object searchByEmail(String email);

	public abstract Object searchByALL(String firstName, String lastName, String email);

	public abstract JSONObject deleteContactById(String id) throws JSONException;
}
