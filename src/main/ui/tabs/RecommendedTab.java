package ui.tabs;

import model.Movie;
import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;

// Represents the recommended tab of the app gui
public class RecommendedTab extends Tab {
    private List<Movie> recommendedMovies;
    private JList displayList;
    private DefaultListModel listModel;

    // EFFECTS: constructs a recommended tab for the app UI that displays a profile's recommended movies
    public RecommendedTab(WhatsNextUI appUI) {
        super(appUI);

        recommendedMovies = super.getAppUI().getRecommendedMovies();
        displayWatchedList(recommendedMovies);
    }

    // EFFECTS: displays watched movie list on the recommended tab of GUI on a scroll pane
    private void displayWatchedList(List<Movie> movies) {

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

        JScrollPane listScrollPane = new JScrollPane(displayList);
        listScrollPane.setPreferredSize(new Dimension(500, 500));
        this.add(listScrollPane);
    }
}
