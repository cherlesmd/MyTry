package com.charliemartinezdominguez.MyTry.tries;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import com.charliemartinezdominguez.MyTry.config.JwtService;

@Service
public class TryService {

    @Autowired
    private TryRepository tryRepository;
    private JwtService jwtService;

    public List<Try> findNear(String header, TryRequest request) {
        final String accessToken;
        final String userName;

        accessToken = header.substring(7);
        userName = jwtService.extractUsername(accessToken);

        GeoJsonPoint point = new GeoJsonPoint(request.getLongitude(), request.getLatitude());
        Distance maxDistance = new Distance(request.getDistance(), Metrics.KILOMETERS);

        return tryRepository.findByUsernameAndLocationNear(userName, point, maxDistance);
    }

    public boolean deleteTry(String header, DeleteRequest request) {

        final String accessToken;
        final String userName;

        accessToken = header.substring(7);
        userName = jwtService.extractUsername(accessToken);
        GeoJsonPoint point = new GeoJsonPoint(request.getLongitude(), request.getLatitude());
        Optional<Try> userTry = tryRepository.findByNameAndLocationAndUsername(request.getName(), point, userName);

        if (userTry.isPresent()) {
            tryRepository.delete(userTry.get());
            return true;
        }
        return false;
    }

    public Try createTry(String header, AddRequest request) {
        final String accessToken;
        final String userName;

        accessToken = header.substring(7);
        userName = jwtService.extractUsername(accessToken);

        GeoJsonPoint point = new GeoJsonPoint(request.getLongitude(), request.getLatitude());
        Try t = new Try(null, request.getName(), request.getAddress(), point, userName);
        return tryRepository.save(t);
    }
}
