package persistence;

import model.Movie;
import model.Profile;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Represents a reader that reads a profile from JSON data stored in file
// Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    // Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads the profile from a file and returns it;
    // throws IOException if an error occurs reading data from file
    // Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public Profile read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseProfile(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    // Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses the profile from JSON object and returns it
    // Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private Profile parseProfile(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        Profile profile = new Profile(name);
        addMovieLists(profile, jsonObject);
        return profile;
    }

    // MODIFIES: profile
    // EFFECTS: parses movie lists from JSON object and adds them to profile
    // watchedMovies and recommendedMovies
    // Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void addMovieLists(Profile profile, JSONObject jsonObject) {
        JSONArray jsonArrayWatched = jsonObject.getJSONArray("watched");
        for (Object json : jsonArrayWatched) {
            JSONObject nextMovie = (JSONObject) json;
            ArrayList<Movie> watched = profile.getWatchedMovies();
            addMovie(watched, nextMovie);
        }

        JSONArray jsonArrayRecommended = jsonObject.getJSONArray("recommended");
        for (Object json : jsonArrayRecommended) {
            JSONObject nextMovie = (JSONObject) json;
            ArrayList<Movie> recommended = profile.getRecommendedMovies();
            addMovie(recommended, nextMovie);
        }
    }

    // MODIFIES: profile
    // EFFECTS: parses movies from JSON object and adds them to given list
    // Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    private void addMovie(ArrayList<Movie> movies, JSONObject jsonObject) {
        String title = jsonObject.getString("title");
        int userRating = jsonObject.getInt("userRating");
        String streamingService = jsonObject.getString("streamingService");

        Movie movie = new Movie(title);
        movie.setUserRating(userRating);
        movie.setStreamingService(streamingService);

        ArrayList<String> genres = movie.getGenres();
        JSONArray genreList = jsonObject.getJSONArray("genres");
        if (!genreList.isEmpty()) {
            for (int counter = 0; counter < genreList.length(); counter++) {
                genres.add(genreList.getString(counter));
            }
        }
        movies.add(movie);

    }

}
