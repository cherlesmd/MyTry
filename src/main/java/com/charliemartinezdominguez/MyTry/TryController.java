package com.charliemartinezdominguez.MyTry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/tries")
public class TryController {

    @Autowired
    private TryService tryService;

    @GetMapping
    public ResponseEntity<List<Try>> getAllNear(@RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude, @RequestParam("distance") double distance) {

        return new ResponseEntity<List<Try>>(
                tryService.findNear("661f43c121e852e0fdc00e81", longitude, latitude, distance), HttpStatus.OK);
    }
}
