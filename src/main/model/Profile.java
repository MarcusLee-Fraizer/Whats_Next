package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Savable;

import java.util.ArrayList;

// Represents a user profile with the user's name, a list of watched movies
// and a list of recommended movies.
public class Profile implements Savable {
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
    // return that movie. If not, return null.
    public Movie searchByTitle(ArrayList<Movie> movies, String title) {
        for (Movie movie: movies) {
            if (movie.getTitle().equals(title)) {
                return movie;
            }
        }
        return null;
    }

    // REQUIRES: searchByTitle()
    // EFFECTS: if a movie with the given title is in the watchedMovies list,
    // return the movie. If not, return null.
    public Movie searchWatchedByTitle(String title) {
        return searchByTitle(watchedMovies, title);
    }

    // REQUIRES: searchByTitle()
    // EFFECTS: if a movie with the given title is in the recommendedMovies list,
    // return the movie. If not, return null.
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

    // REQUIRES: searchByGenre()
    // EFFECTS: given a genre, returns a list of watched movies with
    // that genre.
    public ArrayList<Movie> searchWatchedByGenre(String genre) {
        return searchByGenre(watchedMovies, genre);
    }

    // REQUIRES: searchByGenre()
    // EFFECTS: given a genre, returns a list of recommended movies with
    // that genre.
    public ArrayList<Movie> searchRecommendedByGenre(String genre) {
        return searchByGenre(recommendedMovies, genre);
    }

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

    // REQUIRES: addToMovieList()
    // MODIFIES: this
    // EFFECTS: if the given movie has a title unique to the watched
    // movies list, adds movie to the list
    public boolean addToWatchedList(Movie newMovie) {
        if (addToMovieList(watchedMovies,newMovie)) {
            EventLog.getInstance()
                    .logEvent(new Event(newMovie.getTitle() + " was added to the Watched List."));
        }
        return addToMovieList(watchedMovies,newMovie);
    }

    // REQUIRES: addToMovieList()
    // MODIFIES: this
    // EFFECTS: if the given movie has a title unique to the recommended
    // movies list, adds movie to the list
    public boolean addToRecommendedList(Movie newMovie) {
        if (addToMovieList(recommendedMovies,newMovie)) {
            EventLog.getInstance()
                    .logEvent(new Event(newMovie.getTitle() + " was added to the Recommended List."));
        }
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

    @Override
    // Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("watched", moviesToJson(watchedMovies));
        json.put("recommended", moviesToJson(recommendedMovies));
        return json;
    }

    // EFFECTS: returns a movie list in this as a JSON array
    // Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private JSONArray moviesToJson(ArrayList<Movie> movies) {
        JSONArray jsonArray = new JSONArray();

        for (Movie movie : movies) {
            jsonArray.put(movie.toJson());
        }

        return jsonArray;
    }
}
