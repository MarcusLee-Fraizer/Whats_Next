package model;

import java.util.ArrayList;

// Represents a movie with a title, list of genres, a user rating, and a streaming service.
public class Movie {
    private String title;
    private ArrayList<String> genres;
    private int userRating;
    private String streamingService;

    // REQUIRES: title is non-empty string
    // EFFECTS: constructs a movie with a title, an empty list of genres,
    // user rating of 0, and is available on no streaming services.
    public Movie(String title) {
        this.title = title;
        genres = new ArrayList<>();
        userRating = 0;
        streamingService = "Not Available";
    }

    // MODIFIES: this
    // EFFECTS: adds given genre to genres if it is not already in
    // the list
    public void addGenre(String genre) {
        if (!genres.contains(genre)) {
            genres.add(genre);
        }
    }

    // REQUIRES: rating is in the range 0-5
    // MODIFIES: this
    // EFFECTS: sets the user rating of this movie to the given rating.
    public void setUserRating(int rating) {
        userRating = rating;
    }

    // MODIFIES: this
    // EFFECTS: sets streaming service of this movie
    public void setStreamingService(String service) {
        streamingService = service;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public String getStreamingService() {
        return streamingService;
    }

    public int getUserRating() {
        return userRating;
    }

}
