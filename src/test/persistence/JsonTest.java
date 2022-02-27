package persistence;

import model.Movie;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkMovie(String title, ArrayList<String> genreList, int userRating, String streamingService,
                              Movie movie) {
        assertEquals(title, movie.getTitle());
        assertEquals(userRating, movie.getUserRating());
        assertEquals(streamingService, movie.getStreamingService());

        ArrayList<String> genres = movie.getGenres();
        for (int counter = 0; counter < genres.size(); counter++) {

            assertEquals(genres.get(counter), genreList.get(counter));

        }
    }
}
