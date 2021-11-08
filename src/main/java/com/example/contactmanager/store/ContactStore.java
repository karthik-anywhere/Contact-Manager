package com.example.contactmanager.store;


import com.google.cloud.datastore.*;
import com.google.common.collect.Lists;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContactStore extends Store {

	@Override
	public String storeContactInStore(String firstName, String lastName, String email) {
		String id = generateID();
		Key contactKey = datastore.newKeyFactory()
				.setKind("Contact")
				.newKey(id);
		Entity contact = Entity.newBuilder(contactKey)
				.set("firstName", firstName)
				.set("lastName", lastName)
				.set("email", email)
				.build();
		datastore.put(contact);
//		contactMap.put(generateID(), new Contact(firstName, lastName, email));
		return id;
	}

	@Override
	public String storeContactInStore(String id, String firstName, String lastName, String email) {
//		e.setProperty("contactId",id);
//		e.setProperty("firstName",firstName);
//		e.setProperty("lastName",lastName);
//		e.setProperty("email",email);
//		ds.put(e);
		contactMap.put(id, new Contact(firstName, lastName, email));
		return id;
	}

	@Override
	public JSONObject updateContactInStore(String key, Contact contactObj) {
		Key contactKey = datastore.newKeyFactory().setKind("Contact").newKey(key);
		JSONObject jsonData = new JSONObject();
		if(datastore.get(contactKey) != null){
			Entity task = Entity.newBuilder(datastore.get(contactKey))
					.set("firstName", contactObj.getFirstName())
					.set("lastName", contactObj.getLastName())
					.set("email", contactObj.getEmail())
					.build();
			datastore.update(task);
			jsonData.put("id",key);
			return jsonData;
		}
		 else {
			jsonData.put("error code", 404);
			jsonData.put("error message", "Resource not found for this ID");
			return jsonData;
		}
//		contactMap.replace(key, contactObj);
	}

	@Override
	public ArrayList<Entity> searchByFirstName(String firstName) {
//		q.setFilter(new Query.FilterPredicate("firstName", Query.FilterOperator.EQUAL,firstName));
//		PreparedQuery pq = ds.prepare(q);
//		return pq.asList(FetchOptions.Builder.withDefaults());

		//cloud data store
		Query<Entity> query = Query.newEntityQueryBuilder().setKind("Contact")
				.setFilter(StructuredQuery.PropertyFilter.eq("firstName", firstName))
				.build();
		QueryResults<Entity> contact = datastore.run(query);
		ArrayList<Entity> contacts = Lists.newArrayList(contact);
		return contacts;
	}

	@Override
	public ArrayList<Entity> searchBylastName(String lastName) {
//		q.setFilter(new Query.FilterPredicate("lastName", Query.FilterOperator.EQUAL,lastName));
//		PreparedQuery pq = ds.prepare(q);
//		return pq.asList(FetchOptions.Builder.withDefaults());

//cloud data store
		Query<Entity> query = Query.newEntityQueryBuilder().setKind("Contact")
				.setFilter(StructuredQuery.PropertyFilter.eq("lastName", lastName))
				.build();
		QueryResults<Entity> contact = datastore.run(query);
		ArrayList<Entity> contacts = Lists.newArrayList(contact);
		return contacts;
	}

	@Override
	public ArrayList<Entity> searchByEmail(String email) {
//		q.setFilter(new Query.FilterPredicate("email", Query.FilterOperator.EQUAL,email));
//		PreparedQuery pq = ds.prepare(q);
//		return pq.asList(FetchOptions.Builder.withDefaults());

		//cloud data store
		Query<Entity> query = Query.newEntityQueryBuilder().setKind("Contact")
				.setFilter(StructuredQuery.PropertyFilter.eq("email", email))
				.build();
		QueryResults<Entity> contact = datastore.run(query);
		ArrayList<Entity> contacts = Lists.newArrayList(contact);
		return contacts;
	}

	@Override
	public ArrayList<Entity> searchByALL(String firstName, String lastName, String email) {
//		q.setFilter(new Query.FilterPredicate("firstName", Query.FilterOperator.EQUAL,firstName) );
//		q.setFilter(new Query.FilterPredicate("lastName", Query.FilterOperator.EQUAL,lastName) );
//		q.setFilter(new Query.FilterPredicate("email", Query.FilterOperator.EQUAL,email) );
//		PreparedQuery pq = ds.prepare(q);
//		return pq.asList(FetchOptions.Builder.withDefaults());

		//cloud data store
		Query<Entity> query = Query.newEntityQueryBuilder().setKind("Contact")
				.setFilter(StructuredQuery.PropertyFilter.eq("firstName", firstName))
				.setFilter(StructuredQuery.PropertyFilter.eq("lastName", lastName))
				.setFilter(StructuredQuery.PropertyFilter.eq("email", email))
				.build();
		QueryResults<Entity> contact = datastore.run(query);
		ArrayList<Entity> contacts = Lists.newArrayList(contact);
		return contacts;

	}

	@Override
	public JSONObject deleteContactById(String id) throws JSONException {
		Key contactKey = datastore.newKeyFactory().setKind("Contact").newKey(id);
		JSONObject jsonData = new JSONObject();
		if(datastore.get(contactKey) != null){
			datastore.delete(contactKey);
			jsonData.put("id", id);
			return jsonData;
		}
		 else {
			jsonData.put("error code", 404);
			jsonData.put("error message", "Resource not found for this ID");
			return jsonData;
		}
	}
}
