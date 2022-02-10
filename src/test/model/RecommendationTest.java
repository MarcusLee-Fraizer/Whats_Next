package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class RecommendationTest {
    Recommendation testRecommendation;

    @BeforeEach
    void runBefore() {
        testRecommendation = new Recommendation();
    }

    @Test
    void ConstructorTest() {
        assertTrue(testRecommendation.getDatabase().isEmpty());
    }

    @Test
    void addToDatabaseTest() {
        Movie m1 = new Movie("The Thing");
        Movie m2 = new Movie("Halloween");
        ArrayList<Movie> database = testRecommendation.getDatabase();

        assertTrue(testRecommendation.addToDatabase(m1));
        assertTrue(testRecommendation.addToDatabase(m2));
        assertFalse(testRecommendation.addToDatabase(m1));
        assertEquals(2,database.size());
        assertEquals(m1,database.get(0));
        assertEquals(m2,database.get(1));
    }

    @Test
    void recommendByGenreTest() {
        Movie m1 = new Movie("The Thing");
        Movie m2 = new Movie("Titanic");
        Movie m3 = new Movie("Halloween");
        m1.addGenre("sci-fi");
        m1.addGenre("horror");
        m2.addGenre("drama");
        m3.addGenre("horror");
        assertTrue(testRecommendation.addToDatabase(m1));
        assertTrue(testRecommendation.addToDatabase(m2));
        assertTrue(testRecommendation.addToDatabase(m3));

        ArrayList<Movie> horror = testRecommendation.recommendByGenre("horror");
        ArrayList<Movie> drama = testRecommendation.recommendByGenre("drama");
        ArrayList<Movie> comedy = testRecommendation.recommendByGenre("comedy");

        assertEquals(2,horror.size());
        assertEquals(m1,horror.get(0));
        assertEquals(m3,horror.get(1));
        assertEquals(1,drama.size());
        assertEquals(m2,drama.get(0));
        assertTrue(comedy.isEmpty());
    }

    @Test
    void recommendBySimilarMovieTest() {
        Movie m1 = new Movie("The Thing");
        Movie m2 = new Movie("Titanic");
        Movie m3 = new Movie("Halloween");
        m1.addGenre("sci-fi");
        m1.addGenre("horror");
        m2.addGenre("drama");
        m3.addGenre("horror");
        assertTrue(testRecommendation.addToDatabase(m1));
        assertTrue(testRecommendation.addToDatabase(m2));
        assertTrue(testRecommendation.addToDatabase(m3));

        Movie m4 = new Movie("The Dark Knight");
        Movie m5 = new Movie("It");
        m4.addGenre("action");
        m5.addGenre("horror");

        ArrayList<Movie> action = testRecommendation.recommendBySimilarMovie(m4);
        ArrayList<Movie> horror = testRecommendation.recommendBySimilarMovie(m5);

        assertTrue(action.isEmpty());
        assertEquals(2,horror.size());
        assertEquals(m1,horror.get(0));
        assertEquals(m3,horror.get(1));
    }
}
