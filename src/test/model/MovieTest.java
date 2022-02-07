package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MovieTest {
    private Movie testMovie;

    @BeforeEach
    void runBefore() {
        testMovie = new Movie("Malignant");
    }

    @Test
    void ConstructorTest() {
        assertEquals("Malignant",testMovie.getTitle());
        ArrayList<String> genres = testMovie.getGenres();
        assertTrue(genres.isEmpty());
        assertEquals(0,testMovie.getUserRating());
    }

    @Test
    void addGenreTest() {
        ArrayList<String> genres = testMovie.getGenres();
        assertTrue(genres.isEmpty());
        testMovie.addGenre("horror");
        assertEquals("horror", genres.get(0));
        assertEquals(1,genres.size());

        testMovie.addGenre("thriller");
        testMovie.addGenre("comedy");
        assertEquals(3,genres.size());
        assertEquals("thriller",genres.get(1));
        assertEquals("comedy",genres.get(2));


    }

    @Test
    void addSameGenreTest() {
        ArrayList<String> genres = testMovie.getGenres();
        assertTrue(genres.isEmpty());
        testMovie.addGenre("horror");
        testMovie.addGenre("horror");
        assertEquals("horror", genres.get(0));
        assertEquals(1,genres.size());
    }

    @Test
    void setUserRatingTest() {
        assertEquals(0,testMovie.getUserRating());
        testMovie.setUserRating(4);
        assertEquals(4,testMovie.getUserRating());
    }

}