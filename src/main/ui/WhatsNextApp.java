package ui;

import model.Movie;
import model.Profile;
import model.Recommendation;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

// What's Next? Movie logging and recommendation app.
public class WhatsNextApp {
    private static final String JSON_STORE = "./data/whatsNext.json";
    private Profile profile;
    private Recommendation recommendation;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs What's Next application
    public WhatsNextApp() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runWhatsNextApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    // Citation: TellerApp, VCS link - https://github.students.cs.ubc.ca/CPSC210/TellerApp
    private void runWhatsNextApp() {
        boolean continueRun = true;
        String command;

        initialize();

        System.out.println("Welcome Back, " + profile.getName() + "!");

        while (continueRun) {
            displayMenu();
            command = input.next();
            command = command.toUpperCase();

            if (command.equals("Q")) {
                continueRun = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nEnjoy your movies!");
    }

    // MODIFIES: this
    // EFFECTS: processes user commands
    private void processCommand(String command) {
        if (command.equals("WATCHED")) {
            viewWatchedMovies();
        } else if (command.equals("RATE")) {
            rateMovie();
        } else if (command.equals("ADD")) {
            addMovieToWatched();
        } else if (command.equals("SEARCH")) {
            searchWatchedMovies();
        } else if (command.equals("RECOMMENDED")) {
            viewRecommendedMovies();
        } else if (command.equals("NEW")) {
            getRecommendation();
        } else if (command.equals("SAVE")) {
            saveProfile();
        } else if (command.equals("LOAD")) {
            loadProfile();
        } else {
            System.out.println("Sorry we can't do that.");
        }
    }

    // MODIFIES: this
    // EFFECTS: creates and instantiates user profiles and
    // movie database
    private void initialize() {
        profile = new Profile("Nick");
        initWatchedMovies(profile);
        initDatabase();

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // MODIFIES: this and profile
    // EFFECTS: initiates watched movies for given profile
    private void initWatchedMovies(Profile profile) {
        Movie m1 = new Movie("INCEPTION");
        Movie m2 = new Movie("SCREAM");
        Movie m3 = new Movie("THE AVENGERS");
        Movie m4 = new Movie("IRON MAN");
        Movie m5 = new Movie("MISSION: IMPOSSIBLE");

        m1.addGenre("SCI-FI");
        m1.addGenre("ACTION");
        m2.addGenre("HORROR");
        m2.addGenre("COMEDY");
        m3.addGenre("SUPERHERO");
        m3.addGenre("ACTION");
        m4.addGenre("SUPERHERO");
        m4.addGenre("ACTION");
        m5.addGenre("ACTION");

        profile.addToWatchedList(m1);
        profile.addToWatchedList(m2);
        profile.addToWatchedList(m3);
        profile.addToWatchedList(m4);
        profile.addToWatchedList(m5);
    }

    // MODIFIES: this
    // EFFECTS: initiates movie database for potential recommendations
    private void initDatabase() {
        recommendation = new Recommendation();
        Movie m6 = new Movie("THE THING");
        Movie m7 = new Movie("SCREAM 2");
        Movie m8 = new Movie("THE BATMAN");
        Movie m9 = new Movie("NO TIME TO DIE");
        Movie m10 = new Movie("DEADPOOL");

        m6.addGenre("SCI-FI");
        m6.addGenre("HORROR");
        m7.addGenre("HORROR");
        m7.addGenre("COMEDY");
        m8.addGenre("ACTION");
        m8.addGenre("SUPERHERO");
        m9.addGenre("ACTION");
        m10.addGenre("SUPERHERO");
        m10.addGenre("COMEDY");

        recommendation.addToDatabase(m6);
        recommendation.addToDatabase(m7);
        recommendation.addToDatabase(m8);
        recommendation.addToDatabase(m9);
        recommendation.addToDatabase(m10);
    }

    // EFFECTS: displays options menu to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tWATCHED -> view a list of movies that you have watched");
        System.out.println("\tRATE -> rate a movie that you have watched");
        System.out.println("\tADD -> add a movie to your watched list");
        System.out.println("\tSEARCH -> search for a movie in your watched list");
        System.out.println("\tRECOMMENDED -> view a list of movies that were previously recommended");
        System.out.println("\tNEW -> get a new movie recommendation");
        System.out.println("\tSAVE -> save changes to your profile");
        System.out.println("\tLOAD -> load a previous profile");
        System.out.println("\tQ -> quit");
    }

    // MODIFIES: this
    // EFFECTS: prompts user to search for a movie in the watched list, by title.
    // Then prompts the user to change the user rating of the returned movie.
    private void rateMovie() {
        System.out.println("Title of the movie that you want to rate:\n");
        String title = input.next();
        title = title.toUpperCase();
        Movie ratedMovie = profile.searchWatchedByTitle(title);

        if (ratedMovie == null) {
            System.out.println("We could not find that movie...");
        } else {
            printMovie(ratedMovie);

            System.out.println("What is your new rating?\n");
            int rating = input.nextInt();

            if (rating >= 0 && rating <= 5) {
                ratedMovie.setUserRating(rating);

                System.out.println("Your rating has been updated!\n");
                printMovie(ratedMovie);
            } else {
                System.out.println("Invalid rating...");
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: prompts user to create a movie and add information to its fields
    // and adds the movie to the profile watched list
    private void addMovieToWatched() {
        System.out.println("Title of the movie you want to add:\n");

        String title = input.next();
        title = title.toUpperCase();

        Movie newMovie = new Movie(title);

        addGenres(newMovie);
        addStreamingService(newMovie);

        profile.addToWatchedList(newMovie);

        System.out.println("The following movie was added to your watched list:\n");
        printMovie(newMovie);
    }

    // MODIFIES: movie
    // EFFECTS: prompts user to add genres to a given movie
    private void addGenres(Movie movie) {
        System.out.println("What genre is this movie in?\n");
        String genre = input.next();
        genre = genre.toUpperCase();
        movie.addGenre(genre);

        boolean moreGenres = true;
        while (moreGenres) {
            System.out.println("Does this movie have another genre? (Y/N)\n");
            String command = input.next();
            command = command.toUpperCase();

            if (command.equals("Y")) {
                System.out.println("What genre is this movie in?\n");
                String nextGenre = input.next();
                nextGenre = nextGenre.toUpperCase();
                movie.addGenre(nextGenre);
            } else if (command.equals("N")) {
                moreGenres = false;
            } else {
                System.out.println("Invalid command...\n");
            }
        }
    }

    // MODIFIES: movie
    // EFFECTS: adds streaming service to given movie
    private void addStreamingService(Movie movie) {
        System.out.println("What streaming service is the movie on? (type N if none)\n");
        String stream = input.next();
        stream = stream.toUpperCase();

        if (stream.equals("N")) {
            movie.setStreamingService("Not Available");
        } else {
            movie.setStreamingService(stream);
        }
    }

    // EFFECTS: prompts the user to enter a title or genre. Prints a movie from the
    // watched list with the given title or prints a list of movies with the given
    // genre.
    private void searchWatchedMovies() {
        System.out.println("Would you like to search by TITLE or GENRE?\n");
        String command = input.next();
        command = command.toUpperCase();

        if (command.equals("TITLE")) {
            System.out.println("What is the title of the movie?");
            String title = input.next();
            title = title.toUpperCase();

            watchedTitleSearch(title);

        } else if (command.equals("GENRE")) {
            System.out.println("What genre would you like to search for?");
            String genre = input.next();
            genre = genre.toUpperCase();

            watchedGenreSearch(genre);

        } else {
            System.out.println("Invalid command...");
        }
    }

    // EFFECTS: prints movie in watched list with the given title
    private void watchedTitleSearch(String title) {
        Movie searchedMovie = profile.searchWatchedByTitle(title);

        if (searchedMovie == null) {
            System.out.println("We could not find that movie...");
        } else {
            System.out.println("The following movie(s) match your search:\n");
            printMovie(searchedMovie);
        }
    }

    // EFFECTS: prints list of movies in watched list with the given genre
    private void watchedGenreSearch(String genre) {
        ArrayList<Movie> searchedList = profile.searchWatchedByGenre(genre);

        if (searchedList.isEmpty()) {
            System.out.println("We could not find any movies in that genre...");
        } else {
            System.out.println("The following movie(s) match your search:\n");
            for (Movie movie : searchedList) {
                printMovie(movie);
            }
        }
    }

    // EFFECTS: prints out a profile's watched list to the screen
    private void viewWatchedMovies() {
        for (Movie movie : profile.getWatchedMovies()) {
            printMovie(movie);
        }
    }

    // EFFECTS: prints out a profile's recommended list to the screen
    private void viewRecommendedMovies() {
        ArrayList<Movie> recommended = profile.getRecommendedMovies();
        if (recommended.isEmpty()) {
            System.out.println("Sorry, you have no recommendations...");
        } else {
            for (Movie movie : profile.getRecommendedMovies()) {
                printMovie(movie);
            }
        }
    }

    // EFFECTS: prompts user to enter a title of a movie in the watched list
    // or a genre. Prints a list of recommended movies for given title or genre.
    private void getRecommendation() {
        System.out.println("Do you want a recommendation based on a GENRE or MOVIE in your watched list?\n");
        String command = input.next();
        command = command.toUpperCase();

        if (command.equals("GENRE")) {
            System.out.println("What genre would you like to see?\n");
            String genre = input.next();
            genre = genre.toUpperCase();

            recommendGenre(genre);

        } else if (command.equals("MOVIE")) {
            System.out.println("Title of the movie you want to base your recommendations on:\n");
            String title = input.next();
            title = title.toUpperCase();

            recommendTitle(title);

        } else {
            System.out.println("Invalid command...");
        }
    }

    // EFFECTS: prints a list of recommended movies based on the given title
    private void recommendTitle(String title) {
        Movie watchedMovie = profile.searchWatchedByTitle(title);
        ArrayList<Movie> uniqueMovies = new ArrayList<>();

        if (watchedMovie == null) {
            System.out.println("Sorry, we have no recommendations for you :(");
        } else {
            ArrayList<Movie> newMovies = recommendation.recommendBySimilarMovie(watchedMovie);

            System.out.println("We recommend these movies:\n");
            for (Movie movie : newMovies) {
                if (!uniqueMovies.contains(movie)) {
                    uniqueMovies.add(movie);
                }
                profile.addToRecommendedList(movie);
            }
            for (Movie movie: uniqueMovies) {
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Streaming service " + movie.getStreamingService());
            }
        }
    }

    // EFFECTS: prints a list of recommended movies based on the given genre
    private void recommendGenre(String genre) {
        ArrayList<Movie> newMovies = recommendation.recommendByGenre(genre);
        ArrayList<Movie> uniqueMovies = new ArrayList<>();

        if (newMovies.isEmpty()) {
            System.out.println("Sorry, we have no recommendations for you :(");
        } else {
            System.out.println("We recommend these movies:\n");
            for (Movie movie : newMovies) {
                if (!uniqueMovies.contains(movie)) {
                    uniqueMovies.add(movie);
                }
                profile.addToRecommendedList(movie);
            }
            for (Movie movie: uniqueMovies) {
                System.out.println("Title: " + movie.getTitle());
                System.out.println("Streaming service " + movie.getStreamingService());
            }
        }
    }

    // EFFECTS: prints out a given movie to the screen
    private void printMovie(Movie movie) {
        System.out.println("Title:\n" + movie.getTitle());
        System.out.println("Your Rating:\n" + movie.getUserRating() + " stars");
        System.out.println("Streaming service:\n" + movie.getStreamingService());
        System.out.println("Genres:");
        for (String genre : movie.getGenres()) {
            System.out.println(genre);
        }
        System.out.println("\n");
    }

    // EFFECTS: saves the profile to file
    private void saveProfile() {
        try {
            jsonWriter.open();
            jsonWriter.write(profile);
            jsonWriter.close();
            System.out.println("Your changes were successfully saved!");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads profile from file
    private void loadProfile() {
        try {
            profile = jsonReader.read();
            System.out.println("Your profile was successfully loaded.");
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}
