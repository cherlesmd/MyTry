package com.charliemartinezdominguez.MyTry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tries")
public class TryController {

  @GetMapping
  public ResponseEntity<String> getAllTries() {
    return new ResponseEntity<String>("All your Tries!", HttpStatus.OK);
  }
}
