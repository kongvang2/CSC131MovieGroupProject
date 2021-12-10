package com.example.OmdbApi;

//Jason: addition to project working with omdb API
public class OmdbController {
    private String jsonResponse;
    public void setJsonResponse (String response) { jsonResponse = response; }
    public String getJsonResponse () { return jsonResponse; }

    public OmdbController() {}
    public OmdbController(String response) {
        jsonResponse = response;
    }
}
