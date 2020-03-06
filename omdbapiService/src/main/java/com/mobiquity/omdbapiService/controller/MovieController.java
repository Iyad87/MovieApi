package com.mobiquity.omdbapiService.controller;


import com.mobiquity.omdbapiService.service.MoviesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/movie")
public class MovieController {

    @Autowired
    MoviesService services;
    @GetMapping("/{movie}")
    public ResponseEntity<?> getMoviesHandler(@PathVariable("movie") String movie) {
        try {
            return new ResponseEntity<> ( services.getMovie ( movie ), HttpStatus.ACCEPTED );
        } catch (Exception ex) {
            return new ResponseEntity<> ( "Error With request", HttpStatus.NOT_FOUND );
        }
    }
}
