package ui.tabs;

import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;

// Represents a generic tab located on the What's Next? ui
// Citation: LongFormProblemStarters - SmartHome, VCS link:
// https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
public abstract class Tab extends JPanel {
    private final WhatsNextUI appUI;

    //REQUIRES: application ui that holds this tab
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    public Tab(WhatsNextUI appUI) {
        this.appUI = appUI;
    }

    //EFFECTS: creates and returns row with button included
    // Citation: LongFormProblemStarters - SmartHome, VCS link:
    // https://github.students.cs.ubc.ca/CPSC210/LongFormProblemStarters.git
    public JPanel formatButtonRow(JButton b) {
        JPanel p = new JPanel();
        p.setLayout(new FlowLayout());
        p.add(b);

        return p;
    }

    // EFFECTS: returns the application ui for this tab
    public WhatsNextUI getAppUI() {
        return appUI;
    }

}