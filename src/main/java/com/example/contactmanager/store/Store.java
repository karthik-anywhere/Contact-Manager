package com.example.contactmanager.store;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;


import com.google.appengine.api.datastore.*;


import com.google.cloud.datastore.Datastore;
import com.google.cloud.datastore.DatastoreOptions;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.contactmanager.store.hashstorage.HashStorage;

public abstract class Store {

	HashStorage hs = HashStorage.getInstance();

	protected HashMap<String, Contact> contactMap = hs.getMap();
//	protected DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
//static Datastore datastore = DatastoreOptions.newBuilder().setProjectId("karthik-intern").build().getService();
//	protected Entity e = new Entity("Contact");
	protected Query q = new Query("Contact");
	public static String generateID() {
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		return uuidAsString;
	}

	public List<Entity> getContactsFromStore() {

		PreparedQuery pq = datastore.prepare(q);
		return pq.asList(FetchOptions.Builder.withDefaults());
	}

	public Entity getContactsFromStore(String id) {
		q.setFilter(new Query.FilterPredicate("contactId", Query.FilterOperator.EQUAL,id));
		PreparedQuery pq = ds.prepare(q);
		return pq.asSingleEntity();
	}

	// for test case , this method is overloaded by id
	public abstract String storeContactInStore(String id, String firstName, String lastName, String email);

	public abstract String storeContactInStore(String firstName, String lastName, String email);

	public abstract String updateContactInStore(String key, Contact contactobj);

	public abstract List<Entity> searchByFirstName(String firstName);

	public abstract List<Entity> searchBylastName(String lastName);

	public abstract List<Entity> searchByEmail(String email);

	public abstract List<Entity> searchByALL(String firstName, String lastName, String email);

	public abstract JSONObject deleteContactById(String id) throws JSONException;
}
