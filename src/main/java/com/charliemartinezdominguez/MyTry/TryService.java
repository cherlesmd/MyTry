package com.charliemartinezdominguez.MyTry;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Point;
import org.springframework.stereotype.Service;


@Service
public class TryService {

    @Autowired
    private TryRepository tryRepository;

    public List<Try> findNear(Point point, Distance distance) {
        return tryRepository.findByLocationNear(point, distance);
    }
}
