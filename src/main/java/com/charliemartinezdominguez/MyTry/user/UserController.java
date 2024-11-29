package com.charliemartinezdominguez.MyTry.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tries")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("")
  public ResponseEntity<String> deleteUserTry(@CookieValue(value = "accessToken") String token,
      @RequestParam("itinerary") String itinerary) {

    boolean added = userService.addItin(token, itinerary);

    if (added) {
      return new ResponseEntity<>("Itinerary added", HttpStatus.OK);
    } else {
      return new ResponseEntity<>("Unable to add new Ininerary", HttpStatus.NOT_FOUND);
    }
  }
}
