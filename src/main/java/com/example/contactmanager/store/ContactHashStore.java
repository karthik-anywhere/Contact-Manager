package com.example.contactmanager.store;

import com.example.contactmanager.store.hashstorage.HashStorage;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class ContactHashStore extends AbstractContactStore implements Store{

    HashStorage hs = HashStorage.getInstance();

    protected HashMap<String, Contact> contactMap = hs.getMap();

    @Override
    public Object getContactsFromStore() {
        return contactMap;
    }

    @Override
    public Object getContactsFromStore(String id) {
       return contactMap.get(id);
    }

    @Override
    public String storeContactInStore(String id, String firstName, String lastName, String email) {
        contactMap.put(id, new Contact(firstName, lastName, email));
        return id;
    }

    @Override
    public String storeContactInStore(String firstName, String lastName, String email) {
        String id = generateID();
        contactMap.put(id, new Contact(firstName, lastName, email));
        return id;
    }

    @Override
    public String updateContactInStore(String key, Contact contactObj) {
        contactMap.replace(key, contactObj);
        return key;
    }

    @Override
    public Object searchByFirstName(String firstName) {
        for (Map.Entry<String, Contact> i : contactMap.entrySet()) {
            if (i.getValue().getFirstName().equals(Contact.convertName(firstName))) {
                return contactMap.get(i.getKey());
            }
        }
        return null;
    }

    @Override
    public Object searchByLastName(String lastName) {
        for (Map.Entry<String, Contact> i : contactMap.entrySet()) {
            if (i.getValue().getLastName().equals(Contact.convertName(lastName))) {
                return contactMap.get(i.getKey());
            }
        }
        return null;
    }

    @Override
    public Object searchByEmail(String email) {
        for (Map.Entry<String, Contact> i : contactMap.entrySet()) {
            if (i.getValue().getEmail().equals(email)) {
                return contactMap.get(i.getKey());
            }
        }
        return null;
    }

    @Override
    public Object searchByALL(String firstName, String lastName, String email) {
        for (Map.Entry<String, Contact> i : contactMap.entrySet()) {
            if ((i.getValue().getFirstName().equals(Contact.convertName(firstName)))
                    && (i.getValue().getLastName().equals(Contact.convertName(lastName)))
                    && (i.getValue().getEmail().equals(email))) {
                return contactMap.get(i.getKey());
            }
        }
        return null;
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
