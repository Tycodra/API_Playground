package com.example.demo;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {
    private static final String CONNECTION_STRING = "mongodb://localhost:27017"; // Update if necessary
    private static final String DATABASE_NAME = "Users";

    public static MongoDatabase getDatabase() {
        MongoClient mongoClient = MongoClients.create(CONNECTION_STRING);
        return mongoClient.getDatabase(DATABASE_NAME);
    }
}

