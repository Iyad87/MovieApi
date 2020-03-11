package com.mobiquity.omdbapiService.persistence;

import com.mobiquity.omdbapiService.model.HttpConnectionMovies;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryPersistence  implements  MoviesPersistence{

    @Autowired
    HttpConnectionMovies externalAPI;
    private Map<String ,String> cache;
    public InMemoryPersistence() {
        cache = new ConcurrentHashMap<> ();
    }

    @Override
    public String getMovie(String movie) throws Exception {
        if(cache.containsKey(movie)){
            return cache.get(movie);
        }else{
            String mov = externalAPI.getMovie(movie);
            cache.put(movie, mov);
            return mov;
        }
    }

}
