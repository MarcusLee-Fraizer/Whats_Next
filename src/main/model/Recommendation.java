package model;

import java.util.ArrayList;

// Represents a movie recommendation with a database of movies that can be
// recommended to the user.
public class Recommendation {
    private ArrayList<Movie> database;

    // EFFECTS: constructs a new recommendation with an empty database
    // of movies.
    public Recommendation() {
        database = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: if the movie is not in the database, adds given movie
    // to database and returns true. If the movie is in database,
    // returns false.
    public void addToDatabase(Movie movie) {
        if (!database.contains(movie)) {
            database.add(movie);
        }
    }

    // EFFECTS: returns a list of movies from the database with the given genre.
    // If no such movie exists in the database, an empty list is returned.
    public ArrayList<Movie> recommendByGenre(String genre) {
        ArrayList<Movie> recommendations = new ArrayList<>();
        for (Movie movie: database) {
            if (movie.getGenres().contains(genre)) {
                recommendations.add(movie);
            }
        }
        return recommendations;
    }

    // EFFECTS: returns a list of movies from the database with one or
    // more matching genres to the given movie. If no such movie is in
    // the database, return an empty list.
    public ArrayList<Movie> recommendBySimilarMovie(Movie movie) {
        ArrayList<Movie> recommendations = new ArrayList<>();
        ArrayList<String> genres = movie.getGenres();
        for (Movie m: database) {
            for (String genre: genres) {
                if (m.getGenres().contains(genre)) {
                    recommendations.add(m);
                }
            }
        }
        return recommendations;
    }

    public ArrayList<Movie> getDatabase() {
        return database;
    }

}
