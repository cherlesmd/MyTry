package com.charliemartinezdominguez.MyTry;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.stereotype.Service;

@Service
public class TryService {

    @Autowired
    private TryRepository tryRepository;

    public List<Try> findNear(String userId, double longitude, double latitude, double distance) {

        ObjectId objectUserId = new ObjectId(userId);
        GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
        Distance maxDistance = new Distance(distance, Metrics.KILOMETERS);

        return tryRepository.findByUserIdAndLocationNear(objectUserId, point, maxDistance);
    }

    public boolean deleteTry(String name, double longitude, double latitude, String userId) {

        GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
        ObjectId objectUserId = new ObjectId(userId);
        Optional<Try> userTry = tryRepository.findByNameAndLocationAndUserId(name, point, objectUserId);

        if (userTry.isPresent()) {
            tryRepository.delete(userTry.get());
            return true;
        }
        return false;
    }

    public Try createTry(String name, String address, double longitude, double latitude, String userId) {
        
        GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
        ObjectId objectUserId = new ObjectId(userId);
        Try t = new Try(null, name, address, point, objectUserId);
        return tryRepository.save(t);
    }
}
