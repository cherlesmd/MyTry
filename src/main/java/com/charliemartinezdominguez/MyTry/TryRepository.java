package com.charliemartinezdominguez.MyTry;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TryRepository extends MongoRepository<Try, ObjectId> {

  
}
