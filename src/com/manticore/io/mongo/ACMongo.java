package com.manticore.io.mongo;

import java.net.UnknownHostException;

import com.google.code.morphia.Datastore;
import com.google.code.morphia.Morphia;
import com.mongodb.Mongo;
import com.mongodb.MongoException;

public class ACMongo extends Mongo implements AutoCloseable {

	public static final String HOST_NAME = "127.0.0.1";

	public static final String DB_NAME = "manticore";

	public ACMongo() throws UnknownHostException, MongoException {
		super(ACMongo.HOST_NAME);
	}

	@Override
	public void close() {
		super.close();
	}

	public Datastore createDatastore() {
		return new Morphia().createDatastore(this, ACMongo.DB_NAME);
	}
}
