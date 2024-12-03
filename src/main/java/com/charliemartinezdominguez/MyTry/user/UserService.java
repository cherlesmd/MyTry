package com.charliemartinezdominguez.MyTry.user;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.charliemartinezdominguez.MyTry.config.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

  @Autowired
  private final UserRepository userRepository;
  @Autowired
  private MongoTemplate mongoTemplate;
  private final JwtService jwtService;

  public boolean addItinerary(String accessToken, String newItin) {

    final String userName;

    userName = jwtService.extractUsername(accessToken);
    Optional<User> user = userRepository.findByUsername(userName);

    if (user.isPresent()) {
      Query query = new Query();
      query.addCriteria(Criteria.where("username").is(userName));

      Update update = new Update();
      update.addToSet("itineraries", newItin);

      mongoTemplate.updateFirst(query, update, User.class);
      return true;
    }
    return false;
  }

  public List<String> getItins(String accessToken) {

    final String userName;

    userName = jwtService.extractUsername(accessToken);
    Optional<User> user = userRepository.findByUsername(userName);
    return user.get().getItineraries();

  }
}
