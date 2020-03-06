package com.mobiquity.moviecatalogservice;

import com.mobiquity.moviecatalogservice.model.Rating;
import com.mobiquity.moviecatalogservice.model.UserRating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/ratingsdata")
public class RatingDataResource {

    @RequestMapping("/{movieId}")
    public Rating getRating(@PathVariable("movieId") String movieId) {return new Rating ( movieId, 4 );}

        @RequestMapping("users/{userId}")
        public UserRating getUserRating (@PathVariable("userId") String userId ){

            List<Rating> ratings = Arrays.asList (

                    new Rating ( "12345", 4 ),
                    new Rating ( "456789", 3 )

            );

            UserRating userRating = new UserRating ();
            userRating.setUserRating ( ratings );

            return userRating;


        }
    }

