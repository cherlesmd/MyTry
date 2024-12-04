package com.charliemartinezdominguez.MyTry.tries;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import com.charliemartinezdominguez.MyTry.config.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TryService {

  @Autowired
  private MongoTemplate mongoTemplate;
  @Autowired
  private final TryRepository tryRepository;
  private final JwtService jwtService;

  public List<Try> findNear(String accessToken, double longitude, double latitude, double distance) {
    final String userName;

    userName = jwtService.extractUsername(accessToken);

    GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
    Distance maxDistance = new Distance(distance, Metrics.KILOMETERS);
    return tryRepository.findByUsernameAndLocationNear(userName, point, maxDistance);
  }

  public List<Try> getItinerary(String accessToken, String itinerary) {

    final String userName;
    userName = jwtService.extractUsername(accessToken);

    return tryRepository.findByUsernameAndItinerariesContaining(userName, itinerary);
  }

  public boolean deleteTry(String accessToken, String name, double longitude, double latitude) {

    final String userName;

    userName = jwtService.extractUsername(accessToken);
    GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
    Optional<Try> userTry = tryRepository.findByNameAndLocationAndUsername(name, point, userName);

    if (userTry.isPresent()) {
      tryRepository.delete(userTry.get());
      return true;
    }
    return false;
  }

  public Try createTry(String accessToken, String name, String address, double longitude, double latitude) {
    final String userName;

    userName = jwtService.extractUsername(accessToken);

    GeoJsonPoint point = new GeoJsonPoint(longitude, latitude);
    Try t = new Try(null, name, address, point, userName, new ArrayList<>());
    return tryRepository.save(t);
  }

  public boolean updateTryItineraries(String accessToken, String tag, String name) {
    final String userName;

    userName = jwtService.extractUsername(accessToken);

    Optional<Try> updateTry = tryRepository.findByNameAndUsername(name, userName);
    if (updateTry.isPresent()) {
      Query query = new Query();
      query.addCriteria(Criteria.where("username").is(userName));
      query.addCriteria(Criteria.where("name").is(name));

      Update update = new Update();
      update.addToSet("itineraries", tag);

      mongoTemplate.updateFirst(query, update, Try.class);
      return true;
    }
    return false;
  }
}
