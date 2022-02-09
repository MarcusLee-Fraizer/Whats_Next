package model;

import java.util.ArrayList;

// Represents a movie recommendation with a database of movies that can be
// recommended to the user.
public class Recommendation {
    private ArrayList<Movie> database;

    // EFFECTS: constructs a new recommendation with an empty database
    // of movies.
    public Recommendation() {
        //stub
    }

    public ArrayList<Movie> getDatabase() {
        return null; //stub
    }

    // MODIFIES: this
    // EFFECTS: if the movie is not in the database, adds given movie
    // to database and returns true. If the movie is in database,
    // returns false.
    public boolean addToDatabase(Movie movie) {
        return false; //stub
    }

    // EFFECTS: returns a list of movies from the database with the given genre.
    // If no such movie exists in the database, an empty list is returned.
    public ArrayList<Movie> recommendByGenre(String genre) {
        return null; //stub
    }

    // EFFECTS: returns a list of movies from the database with one or
    // more matching genres to the given movie. If no such movie is in
    // the database, return an empty list.
    public ArrayList<Movie> recommendBySimilarMovie(Movie movie) {
        return null; //stub
    }



}
