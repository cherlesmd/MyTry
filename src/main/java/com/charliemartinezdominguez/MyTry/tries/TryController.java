package com.charliemartinezdominguez.MyTry.tries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/tries")
public class TryController {

    @Autowired
    private TryService tryService;

    @GetMapping("")
    public ResponseEntity<List<Try>> getAllNear(@CookieValue(value = "accessToken") String token,
            @RequestParam(required = false, name = "longitude", defaultValue = "0") double longitude,
            @RequestParam(required = false, name = "latitude", defaultValue = "0") double latitude,
            @RequestParam("distance") double distance) {

        if (longitude == 0) {
            return ResponseEntity.noContent().build();
        }

        return new ResponseEntity<List<Try>>(
                tryService.findNear(token, longitude, latitude, distance), HttpStatus.OK);
    }

    @GetMapping("/{itinerary}")
    public ResponseEntity<List<Try>> getItinerary(@CookieValue(value = "accessToken") String token,
            @PathVariable String itinerary) {

        return new ResponseEntity<List<Try>>(
                tryService.getItinerary(token, itinerary), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteUserTry(@CookieValue(value = "accessToken") String token,
            @RequestParam("name") String name, @RequestParam("longitude") double longitude,
            @RequestParam("latitude") double latitude) {

        boolean deleted = tryService.deleteTry(token, name, longitude, latitude);

        if (deleted) {
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("unable to delete", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Try> newUserTry(@CookieValue(value = "accessToken") String token,
            @RequestParam("name") String name, @RequestParam("address") String address,
            @RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude) {

        return new ResponseEntity<Try>(
                tryService.createTry(token, name, address, longitude, latitude), HttpStatus.OK);
    }

    @PostMapping("/{tag}/{name}")
    public ResponseEntity<String> updateTryTag(@CookieValue(value = "accessToken") String token,
            @PathVariable String tag, @PathVariable String name) {
        boolean res = tryService.updateTryItineraries(token, tag, name);

        if (res) {
            return new ResponseEntity<>("added", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("unable to add", HttpStatus.NOT_FOUND);
        }
    }
}
