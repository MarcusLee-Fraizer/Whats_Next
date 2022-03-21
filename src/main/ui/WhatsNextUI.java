package ui;

import ui.tabs.*;

import javax.swing.*;

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

    private WhatsNextApp app;

    // EFFECTS: Constructs application UI with an app and loads menu with sidebar and tabs
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    public WhatsNextUI(WhatsNextApp app) {
        super("What's Next?");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        setApp(app);

        sidebar = new JTabbedPane();
        sidebar.setTabPlacement(JTabbedPane.LEFT);

        loadTabs();
        add(sidebar);

        setVisible(true);
    }

    // MODIFIES: this, ui
    // EFFECTS: sets app and sets this to ui
    public void setApp(WhatsNextApp app) {
        if (this.app != app) {
            if (app != null) {
                app.removeUI();
            }
            this.app = app;
            app.setUI(this);
        }
    }

    // MODIFIES: this, ui
    // EFFECTS: removes this from app and sets app to null
    public void removeApp() {
        if (app != null) {
            app.removeUI();
            app = null;
        }
    }

    // EFFECTS: return the app associated with this ui
    public WhatsNextApp getApp() {
        return app;
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
//        JPanel watchedTab = new WatchedTab(this);
//        JPanel recommendedTab = new RecommendedTab(this);
//        JPanel searchTab = new SearchTab(this);
//        JPanel newMovieTab = new NewMovieTab(this);
//        JPanel addMovieTab = new AddMovieTab(this);
//        JPanel ratingsTab = new RatingsTab(this);

        sidebar.add(homeTab, HOME_TAB_INDEX);
        sidebar.setTitleAt(HOME_TAB_INDEX, "Home");
//        sidebar.add(watchedTab, WATCHED_TAB_INDEX);
//        sidebar.setTitleAt(WATCHED_TAB_INDEX, "Watched");
//        sidebar.add(recommendedTab, RECOMMENDED_TAB_INDEX);
//        sidebar.setTitleAt(RECOMMENDED_TAB_INDEX, "Recommended");
//        sidebar.add(searchTab, SEARCH_TAB_INDEX);
//        sidebar.setTitleAt(SEARCH_TAB_INDEX, "Search");
//        sidebar.add(newMovieTab, NEW_MOVIE_TAB_INDEX);
//        sidebar.setTitleAt(NEW_MOVIE_TAB_INDEX, "New Movie");
//        sidebar.add(addMovieTab, ADD_MOVIE_TAB_INDEX);
//        sidebar.setTitleAt(ADD_MOVIE_TAB_INDEX, "Add Movie");
//        sidebar.add(ratingsTab, RATINGS_TAB_INDEX);
//        sidebar.setTitleAt(RATINGS_TAB_INDEX, "Ratings");
    }

}