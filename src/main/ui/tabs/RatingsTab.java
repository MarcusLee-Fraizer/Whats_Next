package ui.tabs;

import model.Movie;
import model.Profile;
import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;

public class RatingsTab extends Tab {
    private static final String TITLE_PROMPT = "Title of the movie that you want to rate:";
    private static final String RATING_PROMPT = "What is your new rating?";

    private JLabel actionLabel;
    private JTextField titleBar;
    private JTextField ratingBar;


    public RatingsTab(WhatsNextUI appUI) {
        super(appUI);

        setLayout(new GridLayout(10, 1));

        placeTitle();

        titleBar = new JTextField(10);
        createTextPanel(titleBar,TITLE_PROMPT);

        titleBarAction();
    }

    // EFFECTS: places title at top of console
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    private void placeTitle() {
        actionLabel = new JLabel("Rate a movie you've watched", JLabel.CENTER);
        actionLabel.setSize(super.getAppUI().WIDTH, super.getAppUI().HEIGHT / 3);
        this.add(actionLabel);
    }

    // EFFECTS: returns a panel with a label of given text and a standard text field
    public void createTextPanel(JTextField textField, String text) {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());

        JLabel label = new JLabel(text);

        panel.add(label);
        panel.add(textField);

        this.add(panel);
    }

    // EFFECTS: searches profile for title matching text in titleBar
    public void titleBarAction() {
        titleBar.addActionListener(e -> {
            String title = titleBar.getText().toUpperCase();
            Profile profile = super.getAppUI().getProfile();
            Movie ratedMovie = profile.searchWatchedByTitle(title);

            if (ratedMovie == null) {
                actionLabel.setText("We could not find that movie...");
            } else {
                actionLabel.setText("You are currently rating: " + title);
                ratingBar = new JTextField(10);
                createTextPanel(ratingBar, RATING_PROMPT);
                ratingBarAction(ratedMovie);
            }
        });
    }

    // EFFECTS: when user enters a value into ratingBar, passes movie and rating to rateMovie()
    private void ratingBarAction(Movie movie) {
        ratingBar.addActionListener(e -> {
            String ratingString = ratingBar.getText();
            int rating = Integer.parseInt(ratingString);
            rateMovie(rating,movie);
        });
    }

    // MODIFIES: movie
    // EFFECTS: if the given rating is in the range of 0-5, sets the movie's userRating to rating,
    // otherwise displays message of invalid rating, catches ArrayOutOfBoundsException
    private void rateMovie(int rating, Movie movie) {
        if (rating >= 0 && rating <= 5) {
            movie.setUserRating(rating);
            actionLabel.setText("Your rating has been updated!");
            try {
                this.remove(2);
            } catch (ArrayIndexOutOfBoundsException e) {
                //
            }
        } else {
            actionLabel.setText("Invalid rating...");
        }
    }




}
