package com.charliemartinezdominguez.MyTry;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TryRepository extends MongoRepository<Try, ObjectId> {

    List<Try> findByUserIdAndLocationNear(ObjectId userId, GeoJsonPoint point, Distance distance);
    Optional<Try> findByNameAndLocation(String name, GeoJsonPoint point);
}
