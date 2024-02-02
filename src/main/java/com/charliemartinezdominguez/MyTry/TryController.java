package com.charliemartinezdominguez.MyTry;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tries")
public class TryController {

  @Autowired
  private TryService tryService;

  @GetMapping
  public ResponseEntity<List<Try>> getAllTries() {
    return new ResponseEntity<List<Try>>(tryService.allTries(), HttpStatus.OK);
  }
  
  @GetMapping("/{name}")
  public ResponseEntity<Optional<Try>> getSingleTry(@PathVariable String name) {
    return new ResponseEntity<Optional<Try>>(tryService.singleTry(name), HttpStatus.OK);
  }
}
