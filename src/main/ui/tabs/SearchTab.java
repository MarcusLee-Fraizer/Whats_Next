package ui.tabs;

import model.Movie;
import model.Profile;
import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;


public class SearchTab extends Tab {
    protected String searchPrompt = "Would you like to search by TITLE or GENRE?";
    protected String titlePrompt = "What is the title of the movie?";
    protected String genrePrompt = "What genre would you like to search for?";

    private JLabel actionText;
    private DefaultListModel listModel;
    private JList displayList;
    private JScrollPane display;

    public SearchTab(WhatsNextUI appUI) {
        super(appUI);

        setLayout(new GridLayout(15,10));

        placeTitle();

        listModel = new DefaultListModel();
        displayList = new JList(listModel);
        display = new JScrollPane(displayList);
        display.setPreferredSize(new Dimension(500, 500));

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

    private void genreButtonAction(JButton genreButton) {
        genreButton.addActionListener(e -> {
            JTextField searchBar = new JTextField(5);
            JLabel label = new JLabel(genrePrompt);
            label.setLabelFor(searchBar);
            searchBar.addActionListener(e1 -> {
                JTextField source = (JTextField) e1.getSource();
                String genre = source.getText();
                searchByGenre(genre);
            });
            this.add(searchBar);
        });
    }

    private void titleButtonAction(JButton titleButton) {
        titleButton.addActionListener(e -> {
            JTextField searchBar = new JTextField(5);
            JLabel label = new JLabel(titlePrompt);
            label.setLabelFor(searchBar);
            searchBar.addActionListener(e1 -> {
                JTextField source = (JTextField) e1.getSource();
                String title = source.getText();
                searchByTitle(title);
            });
            this.add(searchBar);
        });
    }

    private void searchByGenre(String genre) {
        Profile profile = super.getAppUI().getProfile();
        ArrayList<Movie> searchedList = profile.searchWatchedByGenre(genre);

        if (searchedList.isEmpty()) {
            actionText.setText("We could not find any movies in that genre...");
        } else {
            actionText.setText("The following movie(s) match your search:");
            for (Movie movie : searchedList) {
                displayMovie(movie);
            }
        }
    }

    private void searchByTitle(String title) {
        Profile profile = super.getAppUI().getProfile();
        Movie searchedMovie = profile.searchWatchedByTitle(title);

        if (searchedMovie == null) {
            actionText.setText("We could not find that movie...");
        } else {
            actionText.setText("The following movie(s) match your search:");
            displayMovie(searchedMovie);
        }
    }

    private void displayMovie(Movie movie) {

        listModel.addElement("Title: " + movie.getTitle());
        listModel.addElement("Genres: " + movie.getGenres());
        listModel.addElement("Rating: " + movie.getUserRating());
        listModel.addElement("Streaming Service: " + movie.getStreamingService());
        listModel.addElement(" ");


        displayList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        displayList.setLayoutOrientation(JList.VERTICAL);
        displayList.setVisibleRowCount(5);

        this.add(display);
    }


}
