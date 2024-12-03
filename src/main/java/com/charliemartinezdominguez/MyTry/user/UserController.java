package com.charliemartinezdominguez.MyTry.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("")
  public ResponseEntity<String> addItinerary(@CookieValue(value = "accessToken") String token,
      @RequestParam("itinerary") String itinerary) {

    boolean added = userService.addItinerary(token, itinerary);

    if (added) {
      return new ResponseEntity<>("Itinerary added", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Unable to add new Ininerary", HttpStatus.NOT_FOUND);
    }
  }

  @GetMapping("")
  public ResponseEntity<List<String>> getItineraries(@CookieValue(value = "accessToken") String token) {
    return new ResponseEntity<List<String>>(userService.getItins(token), HttpStatus.OK);
  }
}
