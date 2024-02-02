package com.charliemartinezdominguez.MyTry;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TryRepository extends MongoRepository<Try, ObjectId> {

  Optional<Try> findTryByName(String name);
}
