package ui.tabs;

import model.Movie;
import model.Profile;
import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;

// Represents the Add Movie tab on GUI, that adds movies to watched list
public class AddMovieTab extends Tab {
    private static final String INIT_PROMPT = "Add a Movie to Your Watched List";
    private static final String TITLE_PROMPT = "What is the title of the movie?";
    private static final String GENRE_PROMPT = "What genre is the movie in?";
    private static final String ADDITIONAL_PROMPT = "Does this movie have another genre?";
    private static final String STREAMING_SERVICE_PROMPT = "What streaming service is the movie on?";

    private JLabel actionLabel;
    private JTextField titleBar;
    private JTextField genreBar;
    private JTextField additionalBar;
    private JTextField streamingBar;
    private JPanel panel;

    public AddMovieTab(WhatsNextUI appUI) {
        super(appUI);

        setLayout(new GridLayout(10, 1));
        placeTitle();
        createTextFields();
        placeAddButton();
    }

    // EFFECTS: places title at top of console
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    private void placeTitle() {
        actionLabel = new JLabel(INIT_PROMPT, JLabel.CENTER);
        actionLabel.setSize(super.getAppUI().WIDTH, super.getAppUI().HEIGHT / 3);
        this.add(actionLabel);
    }

    // EFFECTS: instantiates textFields and places them on a panel on the Add Movie Tab
    public void createTextFields() {
        titleBar = new JTextField(10);
        genreBar = new JTextField(10);
        additionalBar = new JTextField(10);
        streamingBar = new JTextField(10);

        createTextPanel(titleBar, TITLE_PROMPT);
        createTextPanel(genreBar, GENRE_PROMPT);
        createTextPanel(additionalBar, ADDITIONAL_PROMPT);
        createTextPanel(streamingBar, STREAMING_SERVICE_PROMPT);
    }

    // EFFECTS: returns a panel with a label of given text and a standard text field
    public void createTextPanel(JTextField textField, String text) {
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new FlowLayout());

        JLabel label = new JLabel(text);

        textPanel.add(label);
        textPanel.add(textField);

        this.add(textPanel);
    }

    // EFFECTS: places an add movie button that adds a movie to the watched list
    private void placeAddButton() {
        JButton addButton = new JButton("Add Movie");

        JPanel buttonRow = formatButtonRow(addButton);

        buttonRow.setSize(super.getAppUI().WIDTH, super.getAppUI().HEIGHT / 6);

        addMovieAction(addButton);

        this.add(buttonRow);
    }

    // EFFECTS: changes action text if text fields have invalid inputs, otherwise adds movie to watched list
    private void addMovieAction(JButton addButton) {
        addButton.addActionListener(e -> {
            if (titleBar.getText().equals("")) {
                actionLabel.setText("Title is not valid...");
            } else if (genreBar.getText().equals("")) {
                actionLabel.setText("Genre is not valid...");
            } else {
                addMovieToWatched();
            }
        });
    }

    // MODIFIES: movie, profile
    // REQUIRES: title, genre are not empty strings
    // EFFECTS: takes text from text fields, creates a movie, and adds it to a profile's watched list
    private void addMovieToWatched() {
        String title = titleBar.getText();

        Movie newMovie = new Movie(title.toUpperCase());

        addGenres(newMovie);
        addStreamingService(newMovie);

        Profile profile = super.getAppUI().getProfile();
        profile.addToWatchedList(newMovie);
        displayMovie(newMovie);
    }

    // MODIFIES: movie
    // EFFECTS: adds genres to given movie
    public void addGenres(Movie movie) {
        String genre = genreBar.getText();
        String otherGenre = additionalBar.getText();

        movie.addGenre(genre.toUpperCase());

        if (!otherGenre.equals("")) {
            movie.addGenre(otherGenre.toUpperCase());
        }
    }

    // MODIFIES: movie
    // EFFECTS: adds a streaming service to a given movie
    public void addStreamingService(Movie movie) {
        String streamingService = streamingBar.getText();
        streamingService = streamingService.toUpperCase();

        if (streamingService.equals("")) {
            movie.setStreamingService("Not Available");
        } else {
            movie.setStreamingService(streamingService);
        }
    }

    // EFFECTS: displays given movie on display on GUI
    private void displayMovie(Movie movie) {
        if (panel != null) {
            this.remove(panel);
        }
        panel = new JPanel();
        panel.setLayout(new GridLayout(2, 1));
        JLabel label = new JLabel("The following movie was added to your watched list:");

        DefaultListModel listModel = new DefaultListModel();

        listModel.addElement("Title: " + movie.getTitle());
        listModel.addElement("Genres: " + movie.getGenres());
        listModel.addElement("Rating: " + movie.getUserRating());
        listModel.addElement("Streaming Service: " + movie.getStreamingService());
        listModel.addElement(" ");

        JList displayList = new JList(listModel);
        displayList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        displayList.setLayoutOrientation(JList.VERTICAL);
        displayList.setVisibleRowCount(5);

        JScrollPane display = new JScrollPane(displayList);
        display.setPreferredSize(new Dimension(500, 500));

        panel.add(label);
        panel.add(display);
        this.add(panel);
    }


}
