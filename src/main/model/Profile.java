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
        this.name = name;
        watchedMovies = new ArrayList<>();
        recommendedMovies = new ArrayList<>();
    }

    // REQUIRES: rating must be in the range 0-5
    // EFFECTS: given a user rating, returns a list of watched movies with
    // that rating or above.
    public ArrayList<Movie> searchByUserRating(int rating) {
        ArrayList<Movie> ratedMovies = new ArrayList<>();
        for (Movie movie : watchedMovies) {
            if (movie.getUserRating() >= rating) {
                ratedMovies.add(movie);
            }
        }
        return ratedMovies;
    }


    // EFFECTS: given a genre and a list of movies, returns a list of
    // those movies with that genre.
    public ArrayList<Movie> searchByGenre(ArrayList<Movie> movies, String genre) {
        ArrayList<Movie> moviesWithGenre = new ArrayList<>();
        for (Movie movie: movies) {
            ArrayList<String> genres = movie.getGenres();
            if (genres.contains(genre)) {
                moviesWithGenre.add(movie);
            }
        }
        return moviesWithGenre;
    }

    // EFFECTS: given a genre, returns a list of watched movies with
    // that genre.
    public ArrayList<Movie> searchWatchedByGenre(String genre) {
        return searchByGenre(watchedMovies, genre);
    }

    // EFFECTS: given a genre, returns a list of recommended movies with
    // that genre.
    public ArrayList<Movie> searchRecommendedByGenre(String genre) {
        return searchByGenre(recommendedMovies, genre);
    }


    public String getName() {
        return name;
    }

    public ArrayList<Movie> getWatchedMovies() {
        return watchedMovies;
    }

    public ArrayList<Movie> getRecommendedMovies() {
        return recommendedMovies;
    }

}
