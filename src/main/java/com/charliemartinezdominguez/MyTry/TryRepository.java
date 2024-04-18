package com.charliemartinezdominguez.MyTry;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Distance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TryRepository extends MongoRepository<Try, ObjectId> {

    List<Try> findByUserIdAndLocationNear(ObjectId userId, Point point, Distance distance);
}
