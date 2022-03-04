package persistence;

import model.Movie;
import model.Profile;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class JsonReaderTest extends JsonTest {

    @Test
        // Citation: JsonSerializationDemo,
        // VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/nothing.json");
        try {
            Profile profile = reader.read();
            fail("IOException expected - not caught");
        } catch (IOException e) {
            // pass
        }
    }


    @Test
        // Citation: JsonSerializationDemo,
        // VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    void testReaderEmptyProfile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyProfile.json");
        try {
            Profile profile = reader.read();
            assertEquals("Empty", profile.getName());
            assertEquals(0, profile.getWatchedMovies().size());
            assertEquals(0, profile.getRecommendedMovies().size());
        } catch (IOException e) {
            fail("File was not readable");
        }
    }

    @Test
        // Citation: JsonSerializationDemo,
        // VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    void testReaderGeneralProfile() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralProfile.json");
        try {
            Profile profile = reader.read();
            assertEquals("Dylan", profile.getName());
            List<Movie> watched = profile.getWatchedMovies();
            List<Movie> recommended = profile.getRecommendedMovies();
            assertEquals(2, watched.size());
            assertEquals(2, recommended.size());

            ArrayList<String> g1 = new ArrayList<>();
            ArrayList<String> g2 = new ArrayList<>();
            ArrayList<String> g3 = new ArrayList<>();
            ArrayList<String> g4 = new ArrayList<>();
            g2.add("drama");
            g2.add("romance");
            g3.add("comedy");
            g4.add("horror");
            g4.add("thriller");

            checkMovie("Saw", g1, 0, "Not Available", watched.get(0));
            checkMovie("Titanic", g2, 0, "Not Available", watched.get(1));
            checkMovie("Mean Girls", g3, 0, "Not Available", recommended.get(0));
            checkMovie("The Ring", g4, 0, "Not Available", recommended.get(1));
        } catch (IOException e) {
            fail("File was not readable");
        }
    }
}
