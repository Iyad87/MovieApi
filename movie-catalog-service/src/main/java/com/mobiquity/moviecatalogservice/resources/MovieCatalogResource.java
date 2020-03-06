package com.mobiquity.moviecatalogservice.resources;


import com.mobiquity.moviecatalogservice.model.CatalogItem;
import com.mobiquity.moviecatalogservice.model.Movie;
import com.mobiquity.moviecatalogservice.model.UserRating;
import com.mobiquity.moviecatalogservice.model.Rating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {
    @Autowired
    private  RestTemplate restTemplate;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){
        UserRating ratings =  restTemplate.getForObject ( "http://rating-data-service/ratingsdata/users/"+ userId, UserRating.class );
           return ratings.getUserRating ().stream ().map ( rating -> {
               // For Each Movie ID, call movie info service and get details
              Movie movie =  restTemplate.getForObject ( "http://movie-info-service/movies/" + rating.getMovieId (), Movie.class );
              // Put them all together
               return new CatalogItem(movie.getName (), "Test", rating.getRating ());


            })
                    .collect ( Collectors.toList ( ) );

    }
}
