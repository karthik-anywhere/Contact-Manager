package com.example.contactmanager.store;


import com.google.cloud.datastore.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class ContactStore extends Store {

	@Override
	public String storeContactInStore(String firstName, String lastName, String email) {
		String id = generateID();
		Key taskKey = datastore.newKeyFactory()
				.setKind("Task")
				.newKey("sampleTask");
		Entity task = Entity.newBuilder(taskKey)
				.set("category", "Personal")
				.set("done", false)
				.set("priority", 4)
				.set("description", "Learn Cloud Datastore")
				.build();
//		e.setProperty("contactId",id);
//		e.setProperty("firstName",firstName);
//		e.setProperty("lastName",lastName);
//		e.setProperty("email",email);
		ds.put(e);
//		contactMap.put(generateID(), new Contact(firstName, lastName, email));
		return id;
	}

	@Override
	public String storeContactInStore(String id, String firstName, String lastName, String email) {
		e.setProperty("contactId",id);
		e.setProperty("firstName",firstName);
		e.setProperty("lastName",lastName);
		e.setProperty("email",email);
		ds.put(e);
		contactMap.put(id, new Contact(firstName, lastName, email));
		return id;
	}

	@Override
	public String updateContactInStore(String key, Contact contactObj) {
		contactMap.replace(key, contactObj);
		return key;
	}

	@Override
	public List<Entity> searchByFirstName(String firstName) {
		q.setFilter(new Query.FilterPredicate("firstName", Query.FilterOperator.EQUAL,firstName));
		PreparedQuery pq = ds.prepare(q);
		return pq.asList(FetchOptions.Builder.withDefaults());
//		for (Entry<String, Contact> i : contactMap.entrySet()) {
//			if (i.getValue().getFirstName().equals(Contact.convertName(firstName))) {
//				return contactMap.get(i.getKey());
//			}
//		}
//		return null;
	}

	@Override
	public List<Entity> searchBylastName(String lastName) {
		q.setFilter(new Query.FilterPredicate("lastName", Query.FilterOperator.EQUAL,lastName));
		PreparedQuery pq = ds.prepare(q);
		return pq.asList(FetchOptions.Builder.withDefaults());
//		for (Entry<String, Contact> i : contactMap.entrySet()) {
//			if (i.getValue().getLastName().equals(Contact.convertName(lastName))) {
//				return contactMap.get(i.getKey());
//			}
//		}
//		return null;
	}

	@Override
	public List<Entity> searchByEmail(String email) {
		q.setFilter(new Query.FilterPredicate("email", Query.FilterOperator.EQUAL,email));
		PreparedQuery pq = ds.prepare(q);
		return pq.asList(FetchOptions.Builder.withDefaults());
//		for (Entry<String, Contact> i : contactMap.entrySet()) {
//			if (i.getValue().getEmail().equals(email)) {
//				return contactMap.get(i.getKey());
//			}
//		}
//		return null;
	}

	@Override
	public List<Entity> searchByALL(String firstName, String lastName, String email) {
		q.setFilter(new Query.FilterPredicate("firstName", Query.FilterOperator.EQUAL,firstName) );
		q.setFilter(new Query.FilterPredicate("lastName", Query.FilterOperator.EQUAL,lastName) );
		q.setFilter(new Query.FilterPredicate("email", Query.FilterOperator.EQUAL,email) );
		PreparedQuery pq = ds.prepare(q);
		return pq.asList(FetchOptions.Builder.withDefaults());
//		for (Entry<String, Contact> i : contactMap.entrySet()) {
//			if ((i.getValue().getFirstName().equals(Contact.convertName(firstName)))
//					&& (i.getValue().getLastName().equals(Contact.convertName(lastName)))
//					&& (i.getValue().getEmail().equals(email))) {
//				return contactMap.get(i.getKey());
//			}
//		}
//		return null;
	}

	@Override
	public JSONObject deleteContactById(String id) throws JSONException {
		JSONObject jsonData = new JSONObject();
		if (contactMap.remove(id) != null) {
			jsonData.put("id", id);
			return jsonData;
		} else {
			jsonData.put("error code", 404);
			jsonData.put("error message", "Resource not found for this ID");
			return jsonData;
		}
	}
}
