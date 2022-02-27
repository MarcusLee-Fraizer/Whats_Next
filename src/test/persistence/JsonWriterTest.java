package persistence;

import model.Movie;
import model.Profile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Profile profile = new Profile("Invalid");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("Expected IOException was caught");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyProfile() {
        try {
            Profile profile = new Profile("Empty");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyProfile.json");
            writer.open();
            writer.write(profile);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyProfile.json");
            profile = reader.read();
            assertEquals("Empty", profile.getName());
            assertEquals(0,profile.getWatchedMovies().size());
            assertEquals(0,profile.getRecommendedMovies().size());
        } catch (IOException e) {
            fail("Unexpected exception caught");
        }
    }

    @Test
    void testWriterGeneralWorkroom() {
        try {
            Profile profile = new Profile("Dylan");
            Movie m1 = new Movie("Saw");
            Movie m2 = new Movie("Titanic");
            Movie m3 = new Movie("Mean Girls");
            Movie m4 = new Movie("The Ring");
            m1.addGenre("horror");
            m2.addGenre("drama");
            m2.addGenre("romance");
            m3.addGenre("comedy");
            m4.addGenre("horror");
            m4.addGenre("thriller");
            ArrayList<String> g1 = m1.getGenres();
            ArrayList<String> g2 = m2.getGenres();
            ArrayList<String> g3 = m3.getGenres();
            ArrayList<String> g4 = m4.getGenres();

            profile.addToWatchedList(m1);
            profile.addToWatchedList(m2);
            profile.addToRecommendedList(m3);
            profile.addToRecommendedList(m4);
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralProfile.json");
            writer.open();
            writer.write(profile);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralProfile.json");
            profile = reader.read();
            assertEquals("Dylan", profile.getName());
            List<Movie> watched = profile.getWatchedMovies();
            List<Movie> recommended = profile.getRecommendedMovies();
            assertEquals(2, watched.size());
            assertEquals(2, recommended.size());
            checkMovie("Saw",g1,0,"Not Available",watched.get(0));
            checkMovie("Titanic",g2,0,"Not Available",watched.get(1));
            checkMovie("Mean Girls",g3,0,"Not Available",recommended.get(0));
            checkMovie("The Ring",g4,0,"Not Available",recommended.get(1));

        } catch (IOException e) {
            fail("Unexpected exception caught");
        }
    }
}
