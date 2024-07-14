package com.charliemartinezdominguez.MyTry.tries;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import com.charliemartinezdominguez.MyTry.config.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TryService {

    @Autowired
    private final TryRepository tryRepository;
    private final JwtService jwtService;

    public List<Try> findNear(String header, double longitude, double latitude, double distance) {
        final String accessToken;
        final String userName;

        accessToken = header.substring(7);
        userName = jwtService.extractUsername(accessToken);

        GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
        Distance maxDistance = new Distance(distance, Metrics.KILOMETERS);
        return tryRepository.findByUsernameAndLocationNear(userName, point, maxDistance);
    }

    public boolean deleteTry(String header, String name, double longitude, double latitude) {

        final String accessToken;
        final String userName;

        accessToken = header.substring(7);
        userName = jwtService.extractUsername(accessToken);
        GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
        Optional<Try> userTry = tryRepository.findByNameAndLocationAndUsername(name, point, userName);

        if (userTry.isPresent()) {
            tryRepository.delete(userTry.get());
            return true;
        }
        return false;
    }

    public Try createTry(String header, String name, String address, double longitude, double latitude) {
        final String accessToken;
        final String userName;

        accessToken = header.substring(7);
        userName = jwtService.extractUsername(accessToken);

        GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
        Try t = new Try(null, name, address, point, userName);
        return tryRepository.save(t);
    }
}
