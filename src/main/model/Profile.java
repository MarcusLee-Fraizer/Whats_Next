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

    // EFFECTS: if a movie in the given list has a given title,
    // return that movie.
    public Movie searchByTitle(ArrayList<Movie> movies, String title) {
        for (Movie movie: movies) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    public Movie searchWatchedByTitle(String title) {
        return searchByTitle(watchedMovies, title);
    }

    public Movie searchRecommendedByTitle(String title) {
        return searchByTitle(recommendedMovies, title);
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

    // REQUIRES: movie title is a non-empty string.
    // MODIFIES: this
    // EFFECTS: if the given movie has a title unique to the given
    // movies list, adds movie to the list
    public boolean addToMovieList(ArrayList<Movie> movieList, Movie newMovie) {
        for (Movie movie: movieList) {
            if (newMovie.getTitle().equals(movie.getTitle())) {
                return false;
            }
        }
        movieList.add(newMovie);
        return true;
    }

    // REQUIRES: movie title is a non-empty string.
    // MODIFIES: this
    // EFFECTS: if the given movie has a title unique to the watched
    // movies list, adds movie to the list
    public boolean addToWatchedList(Movie newMovie) {
        return addToMovieList(watchedMovies,newMovie);
    }

    // REQUIRES: movie title is a non-empty string.
    // MODIFIES: this
    // EFFECTS: if the given movie has a title unique to the recommended
    // movies list, adds movie to the list
    public boolean addToRecommendedList(Movie newMovie) {
        return addToMovieList(recommendedMovies,newMovie);
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
