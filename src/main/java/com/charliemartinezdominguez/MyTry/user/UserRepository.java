package com.charliemartinezdominguez.MyTry.user;

import java.util.Optional;
import org.bson.types.ObjectId;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId> {
  @Query("{ 'username' : ?0 }")
  void appendItinerary(String username, String itinerary);

  Optional<User> findByUsername(String username);
}
