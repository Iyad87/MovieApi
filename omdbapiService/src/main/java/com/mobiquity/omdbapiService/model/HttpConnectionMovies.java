package com.mobiquity.omdbapiService.model;


import com.mobiquity.omdbapiService.service.MoviesService;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service


public class HttpConnectionMovies implements MoviesService {

    private final  String APIKey = "e53646b8";

    public  String getMovie(String movie) throws Exception {
        String GET_URL = String.format ( "http://www.omdbapi.com/?t=%s&api&apikey=%s", movie , APIKey );
        try {

            URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");

            int responseCode = con.getResponseCode();
            System.out.println("GET Response Code :: " + responseCode);

            if (responseCode == HttpURLConnection.HTTP_OK) {
                // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                return response.toString();

            } else {
                System.out.println("GET request not worked");
            }
            throw new Exception ("Error With API.");
        } catch (IOException ex) {
            throw new Exception ("Error With API .");
        }
    }
}
