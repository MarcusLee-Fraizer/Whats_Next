package model;

import java.util.ArrayList;

// Represents a movie with a title, list of genres, and a user rating.
public class Movie {
    private String title;
    private ArrayList<String> genres;
    private int userRating;

    // REQUIRES: title is non-empty string
    // EFFECTS: constructs a movie with a title, an empty list of genres
    // and a user rating of 0.
    public Movie(String title) {
        this.title = title;
        genres = new ArrayList<>();
        userRating = 0;
    }

    // MODIFIES: this
    // EFFECTS: adds given genre to genres
    public void addGenre(String genre) {
        genres.add(genre);
    }

    // MODIFIES: this
    // EFFECTS: sets the user rating of this movie to the given rating.
    public void setUserRating(int rating) {
        userRating = rating;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public int getUserRating() {
        return userRating;
    }

}
