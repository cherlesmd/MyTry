package com.charliemartinezdominguez.MyTry;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TryService {

  @Autowired
  private TryRepository tryRepository;

  public List<Try> allTries() {
    return tryRepository.findAll();
  }

  public Optional<Try> singleTry(String name) {
    return tryRepository.findTryByName(name);
  }
}
