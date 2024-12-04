package com.charliemartinezdominguez.MyTry.tries;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TryRepository extends MongoRepository<Try, ObjectId> {

  List<Try> findByUsernameAndLocationNear(String username, GeoJsonPoint point, Distance distance);

  List<Try> findByUsernameAndItinerariesContaining(String username, String itinerary);

  Optional<Try> findByNameAndLocationAndUsername(String name, GeoJsonPoint point, String username);

  Optional<Try> findByNameAndUsername(String name, String Username);
}
