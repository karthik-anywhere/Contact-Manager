package com.example.contactmanager.store;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;


import com.google.cloud.datastore.*;
import com.google.common.collect.Lists;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.contactmanager.store.hashstorage.HashStorage;

public abstract class Store {

	HashStorage hs = HashStorage.getInstance();

	protected HashMap<String, Contact> contactMap = hs.getMap();
//	protected DatastoreService ds = DatastoreServiceFactory.getDatastoreService();
static Datastore datastore = DatastoreOptions.newBuilder().setProjectId("karthik-intern").build().getService();
//	protected Entity e = new Entity("Contact");
//	protected Query q = new Query("Contact");
	public static String generateID() {
		UUID uuid = UUID.randomUUID();
		String uuidAsString = uuid.toString();
		return uuidAsString;
	}

	public List<Entity> getContactsFromStore() {
		Query<Entity> query = Query.newEntityQueryBuilder()
				.setKind("Contact")
				.build();
		QueryResults<Entity> contacts = datastore.run(query);
		ArrayList<Entity> allContact = Lists.newArrayList(contacts);
//		PreparedQuery pq = datastore.prepare(q);
//		return pq.asList(FetchOptions.Builder.withDefaults());
		return allContact;
	}

	public Entity getContactsFromStore(String id) {
		Key contactKey = datastore.newKeyFactory().setKind("Contact").newKey(id);
		Entity contacts = datastore.get(contactKey);
//		q.setFilter(new Query.FilterPredicate("contactId", Query.FilterOperator.EQUAL,id));
//		PreparedQuery pq = ds.prepare(q);
//		return pq.asSingleEntity();
		return  contacts;
	}

	// for test case , this method is overloaded by id
	public abstract String storeContactInStore(String id, String firstName, String lastName, String email);

	public abstract String storeContactInStore(String firstName, String lastName, String email);

	public abstract Object updateContactInStore(String key, Contact contactobj);

	public abstract ArrayList<Entity> searchByFirstName(String firstName);

	public abstract ArrayList<Entity> searchBylastName(String lastName);

	public abstract ArrayList<Entity> searchByEmail(String email);

	public abstract ArrayList<Entity> searchByALL(String firstName, String lastName, String email);

	public abstract JSONObject deleteContactById(String id) throws JSONException;
}
