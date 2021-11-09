package com.example.contactmanager.store;

import java.util.UUID;

public abstract class AbstractContactStore implements Store{
    public static String generateID() {
        UUID uuid = UUID.randomUUID();
        String uuidAsString = uuid.toString();
        return uuidAsString;
    }
}
