package com.charliemartinezdominguez.MyTry.tries;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/tries")
public class TryController {

    @Autowired
    private TryService tryService;

    @GetMapping("")
    public ResponseEntity<List<Try>> getAllNear(@RequestHeader("Authorization") String header, @RequestBody TryRequest request) {
        
        return new ResponseEntity<List<Try>>(
                tryService.findNear(header, request), HttpStatus.OK);
    }

    @DeleteMapping("")
    public ResponseEntity<String> deleteUserTry(@RequestHeader("Authorization") String header, @RequestBody DeleteRequest request) {

        boolean deleted = tryService.deleteTry(header, request);

        if (deleted) {
            return new ResponseEntity<>("deleted", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("unable to delete", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("")
    public ResponseEntity<Try> newUserTry(@RequestHeader("Authorization") String header, @RequestBody AddRequest request) {

        return new ResponseEntity<Try>(
                tryService.createTry(header, request), HttpStatus.OK);
    }

}
