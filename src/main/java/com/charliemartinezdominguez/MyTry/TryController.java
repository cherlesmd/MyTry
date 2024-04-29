package com.charliemartinezdominguez.MyTry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

        // userid fixed after user validation
        return new ResponseEntity<List<Try>>(
                tryService.findNear("661f43c121e852e0fdc00e81", longitude, latitude, distance), HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUserTry(@RequestParam("name") String name,
            @RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude) {

        boolean deleted = tryService.deleteTry(name, longitude, latitude);

        if (deleted) {
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("unable to delete", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Try> newUserTry(@RequestParam("name") String name, @RequestParam("address") String address,
            @RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude) {
        
        return new ResponseEntity<Try>(
                tryService.createTry(name, address, longitude, latitude), HttpStatus.OK);
    }
}
