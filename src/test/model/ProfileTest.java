package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileTest {
    Profile testProfile;

    @BeforeEach
    void runBefore() {
        testProfile = new Profile("Anthony");
    }

    @Test
    void ConstructorTest() {
        assertEquals("Anthony", testProfile.getName());
        ArrayList<Movie> watchedMovies = testProfile.getRecommendedMovies();
        ArrayList<Movie> recommendedMovies = testProfile.getRecommendedMovies();
        assertTrue(watchedMovies.isEmpty());
        assertTrue(recommendedMovies.isEmpty());
    }

    @Test
    void searchByGenreTest() {
        ArrayList<Movie> movies = new ArrayList<>();
        Movie m1 = new Movie("Star Wars");
        Movie m2 = new Movie("Raiders of the Lost Ark");
        Movie m3 = new Movie("Jurassic Park");
        m1.addGenre("sci-fi");
        m2.addGenre("action");
        m2.addGenre("adventure");
        m3.addGenre("adventure");
        m3.addGenre("sci-fi");
        movies.add(m1);
        movies.add(m2);
        movies.add(m3);

        ArrayList<Movie> sciFi = testProfile.searchByGenre(movies,"sci-fi");
        ArrayList<Movie> adventure = testProfile.searchByGenre(movies,"adventure");
        ArrayList<Movie> action = testProfile.searchByGenre(movies,"action");
        assertEquals(2,sciFi.size());
        assertEquals(2,adventure.size());
        assertEquals(1,action.size());
        assertEquals(m1, sciFi.get(0));
        assertEquals(m3, sciFi.get(1));
        assertEquals(m3, adventure.get(1));
    }

    @Test
    void searchWatchedByGenreTest() {
        ArrayList<Movie> movies = testProfile.getWatchedMovies();
        Movie m1 = new Movie("Star Wars");
        Movie m2 = new Movie("Raiders of the Lost Ark");
        Movie m3 = new Movie("Jurassic Park");
        m1.addGenre("sci-fi");
        m2.addGenre("action");
        m2.addGenre("adventure");
        m3.addGenre("adventure");
        m3.addGenre("sci-fi");
        movies.add(m1);
        movies.add(m2);
        movies.add(m3);

        ArrayList<Movie> sciFi = testProfile.searchWatchedByGenre("sci-fi");
        ArrayList<Movie> adventure = testProfile.searchWatchedByGenre("adventure");
        ArrayList<Movie> action = testProfile.searchWatchedByGenre("action");
        assertEquals(2,sciFi.size());
        assertEquals(2,adventure.size());
        assertEquals(1,action.size());
        assertEquals(m1, sciFi.get(0));
        assertEquals(m3, sciFi.get(1));
        assertEquals(m3, adventure.get(1));
    }

    @Test
    void searchRecommendedByGenreTest() {
        ArrayList<Movie> movies = testProfile.getRecommendedMovies();
        Movie m1 = new Movie("Star Wars");
        Movie m2 = new Movie("Raiders of the Lost Ark");
        Movie m3 = new Movie("Jurassic Park");
        m1.addGenre("sci-fi");
        m2.addGenre("action");
        m2.addGenre("adventure");
        m3.addGenre("adventure");
        m3.addGenre("sci-fi");
        movies.add(m1);
        movies.add(m2);
        movies.add(m3);

        ArrayList<Movie> sciFi = testProfile.searchRecommendedByGenre("sci-fi");
        ArrayList<Movie> adventure = testProfile.searchRecommendedByGenre("adventure");
        ArrayList<Movie> action = testProfile.searchRecommendedByGenre("action");
        assertEquals(2,sciFi.size());
        assertEquals(2,adventure.size());
        assertEquals(1,action.size());
        assertEquals(m1, sciFi.get(0));
        assertEquals(m3, sciFi.get(1));
        assertEquals(m3, adventure.get(1));
    }

    @Test
    void searchByUserRatingTest() {
        ArrayList<Movie> watchedMovies = testProfile.getWatchedMovies();
        Movie m1 = new Movie("Star Wars");
        Movie m2 = new Movie("Raiders of the Lost Ark");
        Movie m3 = new Movie("Jurassic Park");
        m1.setUserRating(2);
        m2.setUserRating(4);
        m3.setUserRating(3);
        watchedMovies.add(m1);
        watchedMovies.add(m2);
        watchedMovies.add(m3);

        ArrayList<Movie> searchZero = testProfile.searchByUserRating(0);
        ArrayList<Movie> searchThree = testProfile.searchByUserRating(3);
        ArrayList<Movie> searchFive = testProfile.searchByUserRating(5);
        assertEquals(3,searchZero.size());
        assertEquals(2,searchThree.size());
        assertEquals(0,searchFive.size());
        assertEquals(m1, searchZero.get(0));
        assertTrue(searchThree.contains(m2));
        assertTrue(searchThree.contains(m3));
    }

    @Test
    void addToMovieListTest() {
        ArrayList<Movie> movies = new ArrayList<>();
        Movie m1 = new Movie("Star Wars");
        Movie m2 = new Movie("Raiders of the Lost Ark");

        assertTrue(testProfile.addToMovieList(movies,m1));
        testProfile.addToMovieList(movies,m1);
        assertTrue(testProfile.addToMovieList(movies,m2));
        testProfile.addToMovieList(movies,m2);
        assertFalse(testProfile.addToMovieList(movies,m1));
        testProfile.addToMovieList(movies,m1);

        assertEquals(2,movies.size());
        assertEquals(m1,movies.get(0));
        assertEquals(m2,movies.get(1));

    }

    @Test
    void addToWatchedListTest() {
        ArrayList<Movie> movies = testProfile.getWatchedMovies();
        Movie m1 = new Movie("Star Wars");
        Movie m2 = new Movie("Raiders of the Lost Ark");

        assertTrue(testProfile.addToWatchedList(m1));
        testProfile.addToWatchedList(m1);
        assertTrue(testProfile.addToWatchedList(m2));
        testProfile.addToWatchedList(m2);
        assertFalse(testProfile.addToWatchedList(m1));
        testProfile.addToWatchedList(m1);

        assertEquals(2,movies.size());
        assertEquals(m1,movies.get(0));
        assertEquals(m2,movies.get(1));

    }

    @Test
    void addToRecommendedListTest() {
        ArrayList<Movie> movies = testProfile.getRecommendedMovies();
        Movie m1 = new Movie("Star Wars");
        Movie m2 = new Movie("Raiders of the Lost Ark");

        assertTrue(testProfile.addToRecommendedList(m1));
        testProfile.addToRecommendedList(m1);
        assertTrue(testProfile.addToRecommendedList(m2));
        testProfile.addToRecommendedList(m2);
        assertFalse(testProfile.addToRecommendedList(m1));
        testProfile.addToRecommendedList(m1);

        assertEquals(2,movies.size());
        assertEquals(m1,movies.get(0));
        assertEquals(m2,movies.get(1));

    }
}
