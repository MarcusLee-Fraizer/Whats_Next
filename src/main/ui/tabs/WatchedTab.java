package ui.tabs;

import model.Movie;
import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;
import java.util.List;


// Represents the watched tab of the UI
public class WatchedTab extends Tab {
    private JLabel title;
    private List<Movie> watchedMovies;
    private JList displayList;
    private DefaultListModel listModel;

    // EFFECTS: constructs a watched tab for the app UI that displays a profile's watched movies
    public WatchedTab(WhatsNextUI appUI) {
        super(appUI);

        setLayout(new GridLayout(3,1));
        
        placeTitle();

        watchedMovies = super.getAppUI().getWatchedMovies();
        displayWatchedList(watchedMovies);

        // TODO: Maybe implement refresh button
    }

    // EFFECTS: places title at top of console
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    private void placeTitle() {
        title = new JLabel("Watched Movies", JLabel.CENTER);
        title.setSize(super.getAppUI().WIDTH, super.getAppUI().HEIGHT / 3);
        this.add(title);
    }

    // EFFECTS: displays watched movie list on the watched tab of GUI on a scroll pane
    private void displayWatchedList(List<Movie> movies) {

        listModel = new DefaultListModel();

        for (Movie movie : movies) {
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
