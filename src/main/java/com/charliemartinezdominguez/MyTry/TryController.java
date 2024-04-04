package com.charliemartinezdominguez.MyTry;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.Distance;
import org.springframework.data.geo.Metrics;
import org.springframework.data.geo.Point;
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
    public ResponseEntity<List<Try>> getAllNear(@RequestParam("longitude") double longitude, @RequestParam("latitude") double latitude) {
        return new ResponseEntity<List<Try>>(
                tryService.findNear(new Point(Double.valueOf(longitude), Double.valueOf(latitude)),
                        new Distance(25.0, Metrics.KILOMETERS)), HttpStatus.OK);
    }
}
