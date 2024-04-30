package com.charliemartinezdominguez.MyTry;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/{id}")
    public ResponseEntity<List<Try>> getAllNear(@PathVariable("id") String id, @RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude, @RequestParam("distance") double distance) {

        // userid fixed after user validation
        return new ResponseEntity<List<Try>>(
                tryService.findNear(id, longitude, latitude, distance), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUserTry(@PathVariable("id") String id, @RequestParam("name") String name,
            @RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude) {

        boolean deleted = tryService.deleteTry(name, longitude, latitude, id);

        if (deleted) {
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("unable to delete", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}")
    public ResponseEntity<Try> newUserTry(@PathVariable("id") String id, @RequestParam("name") String name, @RequestParam("address") String address,
            @RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude) {
        
        return new ResponseEntity<Try>(
                tryService.createTry(name, address, longitude, latitude, id), HttpStatus.OK);
    }
}
