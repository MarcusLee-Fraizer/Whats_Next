package ui;

import model.Movie;
import model.Profile;
import model.Recommendation;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.tabs.*;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

// Represents the graphical UI for the What's Next App
public class WhatsNextUI extends JFrame {

    public static final int HOME_TAB_INDEX = 0;
    public static final int WATCHED_TAB_INDEX = 1;
    public static final int RECOMMENDED_TAB_INDEX = 2;
    public static final int SEARCH_TAB_INDEX = 3;
    public static final int NEW_MOVIE_TAB_INDEX = 4;
    public static final int ADD_MOVIE_TAB_INDEX = 5;
    public static final int RATINGS_TAB_INDEX = 6;


    public static final int WIDTH = 2000;
    public static final int HEIGHT = 1500;

    private JTabbedPane sidebar;

    private static final String JSON_STORE = "./data/whatsNext.json";
    private Profile profile;
    private Recommendation recommendation;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // EFFECTS: Constructs application UI with an app and loads menu with sidebar and tabs
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    public WhatsNextUI() throws FileNotFoundException {
        super("What's Next?");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        initAppContent();

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    // EFFECTS: initiates application content with a profile of watched movies and a recommendation database
    // that is savable and loadable
    private void initAppContent() {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        profile = new Profile("Nick");
        initWatchedMovies(profile);
        initDatabase();
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

    // EFFECTS: returns watched movie list from profile
    public List<Movie> getWatchedMovies() {
        return profile.getWatchedMovies();
    }

    // EFFECTS: returns recommended movie list from profile
    public List<Movie> getRecommendedMovies() {
        return profile.getRecommendedMovies();
    }

    // EFFECTS: returns profile
    public Profile getProfile() {
        return profile;
    }

    // EFFECTS: return recommendation
    public Recommendation getRecommendation() {
        return recommendation;
    }

    // EFFECTS: saves the profile to file
    // Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public void saveProfile() throws FileNotFoundException {
        jsonWriter.open();
        jsonWriter.write(profile);
        jsonWriter.close();
        System.out.println("Your changes were successfully saved!");
    }


    // MODIFIES: this
    // EFFECTS: loads profile from file
    // Citation: JsonSerializationDemo, VCS link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
    public void loadProfile() throws IOException {
        profile = jsonReader.read();
        System.out.println("Your profile was successfully loaded.");
    }

    // EFFECTS: returns sidebar of this UI
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    public JTabbedPane getTabbedPane() {
        return sidebar;
    }

    // MODIFIES: this
    // EFFECTS: adds home tab, settings tab and report tab to this UI
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    private void loadTabs() {
        JPanel homeTab = new HomeTab(this);
        JPanel watchedTab = new WatchedTab(this);
        JPanel recommendedTab = new RecommendedTab(this);
        JPanel searchTab = new SearchTab(this);
//        JPanel newMovieTab = new NewMovieTab(this);
//        JPanel addMovieTab = new AddMovieTab(this);
//        JPanel ratingsTab = new RatingsTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
        sidebar.add(watchedTab, WATCHED_TAB_INDEX);
        sidebar.setTitleAt(WATCHED_TAB_INDEX, "Watched");
        sidebar.add(recommendedTab, RECOMMENDED_TAB_INDEX);
        sidebar.setTitleAt(RECOMMENDED_TAB_INDEX, "Recommended");
        sidebar.add(searchTab, SEARCH_TAB_INDEX);
        sidebar.setTitleAt(SEARCH_TAB_INDEX, "Search");
//        sidebar.add(newMovieTab, NEW_MOVIE_TAB_INDEX);
//        sidebar.setTitleAt(NEW_MOVIE_TAB_INDEX, "New Movie");
//        sidebar.add(addMovieTab, ADD_MOVIE_TAB_INDEX);
//        sidebar.setTitleAt(ADD_MOVIE_TAB_INDEX, "Add Movie");
//        sidebar.add(ratingsTab, RATINGS_TAB_INDEX);
//        sidebar.setTitleAt(RATINGS_TAB_INDEX, "Ratings");
    }

}