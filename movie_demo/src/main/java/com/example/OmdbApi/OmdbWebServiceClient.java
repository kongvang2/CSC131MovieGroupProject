//this file is working with Omdb to get json information of a movie
//apikey=fe57eabc
package com.example.OmdbApi;

import java.io.*;
import java.lang.StringBuffer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
// import OmdbApi.OmdbController;

//Jason: addition to project, working with OMDB API
public class OmdbWebServiceClient {
    public static final String SEARCH_URL = "http://www.omdbapi.com/?s=TITLE&apikey=APIKEY";
    public static final String SEARCH_BY_IMDB_URL = "http://www.omdbapi.com/?i=IMDB&apikey=APIKEY";

    public static String sendGetRequest(String requestUrl) {
        StringBuffer response = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            //open connection to url
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //configure the connection to be a get (retrieve the json data), will use the PUT to actually post it to the localhost url
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "/");
            connection.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            InputStream stream = connection.getInputStream();
            InputStreamReader reader = new InputStreamReader(stream);
            BufferedReader buffer = new BufferedReader(reader);

            //read and concatenate into a local string
            String line;
            while ((line = buffer.readLine()) != null) {
                response.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response.toString();
    }

    public static String searchMovieByTitle (String title, String key) {
        //change to "UTF-8" if error occurs
        title = URLEncoder.encode(title, StandardCharsets.UTF_8);
        String requestUrl = SEARCH_URL.replaceAll("TITLE", title).replaceAll("APIKEY", key);
        return sendGetRequest(requestUrl);

    }

    public static String searchMovieByImdb (String imdb, String key) {
        String requestUrl = SEARCH_BY_IMDB_URL.replaceAll("IMDB", imdb).replaceAll("APIKEY", key);
        return sendGetRequest(requestUrl);
    }


    public static void main (String[] args) {
        //returns all movies with "batman" in the title
        String title = "batman";
        String response = OmdbWebServiceClient.searchMovieByTitle(title, "fe57eabc");
        OmdbController jsonStuff = new OmdbController(response);
        
        System.out.println(jsonStuff.getJsonResponse());

        //searching by imdb id
//        jsonResponse = OmdbWebServiceClient.searchMovieByImdb("tt0372784", "fe57eabc");
//        System.out.println(jsonResponse);
    }
}
