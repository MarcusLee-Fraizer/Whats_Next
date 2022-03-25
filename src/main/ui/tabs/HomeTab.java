package ui.tabs;

import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;


// Represents the home tab of the UI.
public class HomeTab extends Tab {
    protected String initTitle = "What's Next";
    private JLabel actionText;

    // EFFECTS: constructs a home tab for console with buttons and a Title
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    public HomeTab(WhatsNextUI appUI) {
        super(appUI);

        setLayout(new GridLayout(3, 1));

        placeTitle();
        placeImage();
        placeHomeButtons();
    }

    // EFFECTS: places title at top of console
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    private void placeTitle() {
        actionText = new JLabel(initTitle, JLabel.CENTER);
        actionText.setSize(super.getAppUI().WIDTH, super.getAppUI().HEIGHT / 3);
        this.add(actionText);
    }

    // EFFECTS: places an image on the home tab
    public void placeImage() {
        ImageIcon i = new ImageIcon("./data/movie_image.jpg");
        JLabel picture = new JLabel(i);
        this.add(picture);
    }

    // EFFECTS: creates Save Profile and Load Profile buttons that change title message when clicked
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    private void placeHomeButtons() {
        JButton saveButton = new JButton("Save Profile");
        JButton loadButton = new JButton("Load Profile");

        JPanel buttonRow = formatButtonRow(saveButton);
        buttonRow.add(loadButton);
        buttonRow.setSize(super.getAppUI().WIDTH, super.getAppUI().HEIGHT / 6);

        saveButtonAction(saveButton);

        loadButtonAction(loadButton);

        this.add(buttonRow);
    }

    // EFFECTS: saves current profile, with recommended and watched lists, and changes title message
    private void saveButtonAction(JButton saveButton) {
        saveButton.addActionListener(e -> {
                    try {
                        super.getAppUI().saveProfile();
                        actionText.setText("Profile saved successfully!");
                    } catch (FileNotFoundException f) {
                        actionText.setText("Unable to save profile :(");
                    }
                }
        );
    }

    // EFFECTS: loads previous profile, with recommended and watched lists, and changes title message
    private void loadButtonAction(JButton loadButton) {
        loadButton.addActionListener(e -> {
                    try {
                        super.getAppUI().loadProfile();
                        actionText.setText("Profile was loaded!");
                    } catch (IOException io) {
                        actionText.setText("Unable to load profile :(");
                    }
                }
        );
    }
}
