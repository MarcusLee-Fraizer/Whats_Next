package ui.tabs;

import ui.WhatsNextApp;
import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

public class HomeTab extends Tab {
    private String initTitle;
    private JLabel actionText;

    //EFFECTS: constructs a home tab for console with buttons and a greeting
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    public HomeTab(WhatsNextUI appUI) {
        super(appUI);

        setLayout(new GridLayout(3, 1));

        initTitle = "What's Next?";

        placeTitle();
        placeHomeButtons();
    }

    //EFFECTS: places title at top of console
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    private void placeTitle() {
        actionText = new JLabel(initTitle, JLabel.CENTER);
        actionText.setSize(WIDTH, HEIGHT / 3);
        this.add(actionText);
    }

    //EFFECTS: creates Arrive and Leave buttons that change greeting message when clicked
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    private void placeHomeButtons() {
        JButton saveButton = new JButton("Save Profile");
        JButton loadButton = new JButton("Load Profile");
        WhatsNextApp app = super.getAppUI().getApp();

        JPanel buttonRow = formatButtonRow(saveButton);
        buttonRow.add(loadButton);
        buttonRow.setSize(WIDTH, HEIGHT / 6);

        saveButtonAction(saveButton,app);

        loadButtonAction(loadButton,app);

        this.add(buttonRow);
    }

    private void saveButtonAction(JButton saveButton, WhatsNextApp app) {
        saveButton.addActionListener(e -> {
            try {
                app.saveProfile();
                actionText.setText("Profile saved successfully!");
            } catch (FileNotFoundException f) {
                actionText.setText("Unable to save profile :(");
            }
        }
        );
    }

    private void loadButtonAction(JButton loadButton, WhatsNextApp app) {
        loadButton.addActionListener(e -> {
            try {
                app.loadProfile();
                actionText.setText("Profile was loaded!");
            } catch (IOException io) {
                actionText.setText("Unable to load profile :(");
            }
        }
        );
    }
}
