package com.charliemartinezdominguez.MyTry;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;

@Service
public class TryService {

    @Autowired
    private TryRepository tryRepository;

    public List<Try> findNear(String userId, double longitude, double latitude, double distance) {
        ObjectId objectUserId = new ObjectId(userId);
        Point point = new Point(longitude, latitude);
        Distance maxDistance = new Distance(distance, Metrics.KILOMETERS);
        return tryRepository.findByUserIdAndLocationNear(objectUserId, point, maxDistance);
    }
}
