package ui.tabs;

import model.Movie;
import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;


// Represents the watched tab of the UI
public class WatchedTab extends Tab {
    private List<Movie> watchedMovies;
    private JList displayList;
    private DefaultListModel listModel;

    // EFFECTS: constructs a watched tab for the app UI that displays a profiles watched movies
    public WatchedTab(WhatsNextUI appUI) {
        super(appUI);

        watchedMovies = super.getAppUI().getWatchedMovies();
        displayWatchedList(watchedMovies);
    }

    // EFFECTS: displays watched movie list on the watched tab of GUI on a scroll pane
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
