package ui.tabs;

import model.Movie;
import model.Profile;
import model.Recommendation;
import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

// Represents the New Movie Tab on the GUI, for movie recommendations
public class NewMovieTab extends Tab {
    protected String searchPrompt = "Do you want a recommendation based on a GENRE or MOVIE in your watched list?";
    protected String titlePrompt = "Title of the movie you want to base your recommendations on:";
    protected String genrePrompt = "What genre would you like to see?";

    private JLabel actionText;
    private DefaultListModel listModel;
    private JList displayList;
    private JScrollPane display;

    // EFFECTS: constructs a New Movie tab with a title, two search buttons, and a display
    public NewMovieTab(WhatsNextUI appUI) {
        super(appUI);

        setLayout(new GridLayout(15, 10));

        placeTitle();

        placeButtons();
    }


    // EFFECTS: places title at top of console
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    private void placeTitle() {
        actionText = new JLabel(searchPrompt, JLabel.CENTER);
        actionText.setSize(super.getAppUI().WIDTH, super.getAppUI().HEIGHT / 3);
        this.add(actionText);
    }


    // EFFECTS: places a recommend by title button and a recommend by genre button
    // on the console
    private void placeButtons() {
        JButton genreButton = new JButton("Genre");
        JButton titleButton = new JButton("Title");

        JPanel buttonRow = formatButtonRow(genreButton);
        buttonRow.add(titleButton);
        buttonRow.setSize(super.getAppUI().WIDTH, super.getAppUI().HEIGHT / 6);

        genreButtonAction(genreButton);

        titleButtonAction(titleButton);

        this.add(buttonRow);
    }

    // MODIFIES: this
    // EFFECTS: creates a search bar when the title button is pressed and prompts the user
    // to enter a genre for recommendation
    private void genreButtonAction(JButton genreButton) {
        genreButton.addActionListener(e -> {
            JTextField searchBar = new JTextField(5);
            JLabel label = new JLabel(genrePrompt);
            label.setLabelFor(searchBar);
            searchBar.addActionListener(e1 -> {
                JTextField source = (JTextField) e1.getSource();
                String genre = source.getText();
                recommendGenre(genre);
            });
            this.add(searchBar);
        });
    }

    // MODIFIES: this
    // EFFECTS: creates a search bar when the title button is pressed and prompts the user
    // to enter a title for recommendation
    private void titleButtonAction(JButton titleButton) {
        titleButton.addActionListener(e -> {
            JTextField searchBar = new JTextField(5);
            JLabel label = new JLabel(titlePrompt);
            label.setLabelFor(searchBar);
            searchBar.addActionListener(e1 -> {
                JTextField source = (JTextField) e1.getSource();
                String title = source.getText();
                recommendTitle(title);
            });
            this.add(searchBar);
        });
    }

    // EFFECTS: recommends movies from recommendation object, based on genre
    private void recommendGenre(String genre) {
        Profile profile = super.getAppUI().getProfile();
        Recommendation recommendation = super.getAppUI().getRecommendation();
        ArrayList<Movie> newMovies = recommendation.recommendByGenre(genre.toUpperCase());
        ArrayList<Movie> uniqueMovies = new ArrayList<>();

        if (newMovies.isEmpty()) {
            actionText.setText("Sorry, we have no recommendations for you :(");
        } else {
            actionText.setText("We recommend these movies:");
            for (Movie movie : newMovies) {
                if (!uniqueMovies.contains(movie)) {
                    uniqueMovies.add(movie);
                }
                profile.addToRecommendedList(movie);
            }
            for (Movie movie : uniqueMovies) {
                displayMovie(movie);
            }
        }
    }

    // EFFECTS: recommends movies from recommendation object, based on title
    private void recommendTitle(String title) {
        Profile profile = super.getAppUI().getProfile();
        Recommendation recommendation = super.getAppUI().getRecommendation();
        Movie watchedMovie = profile.searchWatchedByTitle(title.toUpperCase());
        ArrayList<Movie> uniqueMovies = new ArrayList<>();

        if (watchedMovie == null) {
            actionText.setText("Sorry, we have no recommendations for you :(");
        } else {
            ArrayList<Movie> newMovies = recommendation.recommendBySimilarMovie(watchedMovie);

            actionText.setText("We recommend these movies:");
            for (Movie movie : newMovies) {
                if (!uniqueMovies.contains(movie)) {
                    uniqueMovies.add(movie);
                }
                profile.addToRecommendedList(movie);
            }
            for (Movie movie : uniqueMovies) {
                displayMovie(movie);
            }
        }
    }

    // EFFECTS: displays given movie on display on GUI
    private void displayMovie(Movie movie) {

        listModel = new DefaultListModel();

        listModel.addElement("Title: " + movie.getTitle());
        listModel.addElement("Genres: " + movie.getGenres());
        listModel.addElement("Rating: " + movie.getUserRating());
        listModel.addElement("Streaming Service: " + movie.getStreamingService());
        listModel.addElement(" ");

        displayList = new JList(listModel);
        displayList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        displayList.setLayoutOrientation(JList.VERTICAL);
        displayList.setVisibleRowCount(5);

        display = new JScrollPane(displayList);
        display.setPreferredSize(new Dimension(500, 500));
        this.add(display);
    }
}

