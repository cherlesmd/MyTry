package com.charliemartinezdominguez.MyTry.demo;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
public class DemoController {
    
    @GetMapping
    public ResponseEntity<List<String>> sayHello() {
        return new ResponseEntity<List<String>>((Arrays.asList("first", "second")), HttpStatus.OK);
    }
}
