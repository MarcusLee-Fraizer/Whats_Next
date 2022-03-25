package ui.tabs;

import model.Movie;
import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Represents the recommended tab of the app gui
public class RecommendedTab extends Tab {
    JLabel title;
    private List<Movie> recommendedMovies;
    private JList displayList;
    private DefaultListModel listModel;
    private JScrollPane listScrollPane;

    // EFFECTS: constructs a recommended tab for the app UI that displays a profile's recommended movies
    public RecommendedTab(WhatsNextUI appUI) {
        super(appUI);

        setLayout(new GridLayout(3,1));

        placeTitle();

        recommendedMovies = super.getAppUI().getRecommendedMovies();
        displayRecommendedList(recommendedMovies);

        placeRefreshButton();
    }

    // EFFECTS: places title at top of console
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    private void placeTitle() {
        title = new JLabel("Recommended Movies", JLabel.CENTER);
        title.setSize(super.getAppUI().WIDTH, super.getAppUI().HEIGHT / 3);
        this.add(title);
    }

    // EFFECTS: displays watched movie list on the recommended tab of GUI on a scroll pane
    private void displayRecommendedList(List<Movie> movies) {

        listModel = new DefaultListModel();

        for (Movie movie: movies) {
            listModel.addElement("Title: " + movie.getTitle());
            listModel.addElement("Genres: " + movie.getGenres());
            listModel.addElement("Rating: " + movie.getUserRating());
            listModel.addElement("Streaming Service: " + movie.getStreamingService());
            listModel.addElement(" ");
        }

        displayList = new JList(listModel);
        displayList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        displayList.setLayoutOrientation(JList.VERTICAL);
        displayList.setVisibleRowCount(5);

        listScrollPane = new JScrollPane(displayList);
        listScrollPane.setPreferredSize(new Dimension(500, 500));
        this.add(listScrollPane, 1);
    }

    // EFFECTS: places a refresh button that updates the display of movies when pressed
    private void placeRefreshButton() {
        JButton refreshButton = new JButton("Refresh");

        JPanel buttonRow = formatButtonRow(refreshButton);

        buttonRow.setSize(super.getAppUI().WIDTH, super.getAppUI().HEIGHT / 6);

        refreshAction(refreshButton);

        this.add(buttonRow);
    }

    // EFFECTS: updates list of movies displayed
    private void refreshAction(JButton refreshButton) {
        refreshButton.addActionListener(e -> {
            recommendedMovies = super.getAppUI().getRecommendedMovies();
            if (!recommendedMovies.isEmpty()) {
                this.remove(listScrollPane);
                this.displayRecommendedList(recommendedMovies);
            }
        });
    }
}
