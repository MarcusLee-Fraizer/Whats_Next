package model;

import java.util.ArrayList;

// Represents a user profile with the user's name, a list of watched movies
// and a list of recommended movies.
public class Profile {
    private String name;
    private ArrayList<Movie> watchedMovies;
    private ArrayList<Movie> recommendedMovies;

    // EFFECTS: constructs a profile with a name, an empty list of watched
    // movies, and an empty list of recommended movies.
    public Profile(String name) {

    }

    // REQUIRES: rating must be in the range 0-5
    // EFFECTS: given a user rating, returns a list of watched movies with
    // that rating or above.
    public ArrayList<Movie> searchByUserRating(int rating) {
        return null;
    }

    // EFFECTS: given a genre, returns a list of watched movies with
    // that genre.
    public ArrayList<Movie> searchByGenre(ArrayList<Movie> movies,String genre) {
        return null;
    }

    // EFFECTS: given a genre, returns a list of watched movies with
    // that genre.
    public void searchWatchedByGenre(String genre) {
        searchByGenre(watchedMovies, genre);
    }

    // EFFECTS: given a genre, returns a list of recommended movies with
    // that genre.
    public void searchRecommendedByGenre(String genre) {
        searchByGenre(recommendedMovies, genre);
    }

    public String getName() {
        return null;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return null;
    }

    public ArrayList<Movie> getRecommendedMovies() {
        return null;
    }

}
