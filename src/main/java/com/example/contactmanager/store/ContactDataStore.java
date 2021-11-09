package com.example.contactmanager.store;

import com.google.api.client.util.Lists;
import com.google.cloud.datastore.*;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContactDataStore extends AbstractContactStore implements Store{

    static Datastore datastore = DatastoreOptions.newBuilder().setProjectId("karthik-intern").build().getService();

    @Override
    public Object getContactsFromStore() {
        Query<Entity> query = Query.newEntityQueryBuilder()
                .setKind("Contact")
                .build();
        QueryResults<Entity> contacts = datastore.run(query);
        ArrayList<Entity> allContact = Lists.newArrayList(contacts);
        return allContact;
    }

    @Override
    public Object getContactsFromStore(String id) {
        Key contactKey = datastore.newKeyFactory().setKind("Contact").newKey(id);
        Entity contact = datastore.get(contactKey);
        return contact;
    }

    @Override
    public String storeContactInStore(String id, String firstName, String lastName, String email) {
        Key contactKey = datastore.newKeyFactory()
                .setKind("Contact")
                .newKey(id);
        Entity contact = Entity.newBuilder(contactKey)
                .set("firstName", firstName)
                .set("lastName", lastName)
                .set("email", email)
                .build();
        datastore.put(contact);
        return id;
    }

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
        return id;
    }

    @Override
    public String updateContactInStore(String key, Contact contactObj) {
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
            return jsonData.toString();
        }
        else {
            jsonData.put("error code", 404);
            jsonData.put("error message", "Resource not found for this ID");
            return jsonData.toString();
        }
    }

    @Override
    public Object searchByFirstName(String firstName) {
        //cloud data store
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Contact")
                .setFilter(StructuredQuery.PropertyFilter.eq("firstName", firstName))
                .build();
        QueryResults<Entity> contact = datastore.run(query);
        ArrayList<Entity> contacts = com.google.common.collect.Lists.newArrayList(contact);
        return contacts;
    }

    @Override
    public Object searchByLastName(String lastName) {
        //cloud data store
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Contact")
                .setFilter(StructuredQuery.PropertyFilter.eq("lastName", lastName))
                .build();
        QueryResults<Entity> contact = datastore.run(query);
        ArrayList<Entity> contacts = com.google.common.collect.Lists.newArrayList(contact);
        return contacts;
    }

    @Override
    public Object searchByEmail(String email) {
        //cloud data store
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Contact")
                .setFilter(StructuredQuery.PropertyFilter.eq("email", email))
                .build();
        QueryResults<Entity> contact = datastore.run(query);
        ArrayList<Entity> contacts = com.google.common.collect.Lists.newArrayList(contact);
        return contacts;
    }

    @Override
    public Object searchByALL(String firstName, String lastName, String email) {
        //cloud data store
        Query<Entity> query = Query.newEntityQueryBuilder().setKind("Contact")
                .setFilter(StructuredQuery.PropertyFilter.eq("firstName", firstName))
                .setFilter(StructuredQuery.PropertyFilter.eq("lastName", lastName))
                .setFilter(StructuredQuery.PropertyFilter.eq("email", email))
                .build();
        QueryResults<Entity> contact = datastore.run(query);
        ArrayList<Entity> contacts = com.google.common.collect.Lists.newArrayList(contact);
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
