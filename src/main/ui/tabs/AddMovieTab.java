package ui.tabs;

import ui.WhatsNextUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddMovieTab extends Tab implements ActionListener {
    protected static final String searchPrompt = "Would you like to search by TITLE or GENRE?";
    protected static final String titlePrompt = "What is the title of the movie?";
    protected static final String genrePrompt = "What genre would you like to search for?";
    protected static final String buttonString = "JButton";

    protected JLabel actionLabel;

    public AddMovieTab(WhatsNextUI appUI) {
        super(appUI);

        setLayout(new BorderLayout());
        createTextField(searchPrompt);

    }

    public void createTextField(String text) {
        JTextField textField = new JTextField(10);
        textField.setActionCommand(searchPrompt);
        textField.addActionListener(this);

        //Create some labels for the fields.
        JLabel textFieldLabel = new JLabel(searchPrompt + ": ");
        textFieldLabel.setLabelFor(textField);

        //Create a label to put messages during an action event.
        actionLabel = new JLabel("Type text in a field and press Enter.");
        actionLabel.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));

        //Lay out the text controls and the labels.
        JPanel textControlsPane = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();

        textControlsPane.setLayout(gridbag);

        JLabel[] labels = {textFieldLabel};
        JTextField[] textFields = {textField};
        addLabelTextRows(labels, textFields, gridbag, textControlsPane);

        c.gridwidth = GridBagConstraints.REMAINDER; //last
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 1.0;
        textControlsPane.add(actionLabel, c);
        textControlsPane.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Text Fields"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));

        JPanel leftPane = new JPanel(new BorderLayout());
        leftPane.add(textControlsPane,
                BorderLayout.PAGE_START);
        this.add(leftPane);
    }

    private void addLabelTextRows(JLabel[] labels,
                                  JTextField[] textFields,
                                  GridBagLayout gridbag,
                                  Container container) {
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.EAST;
        int numLabels = labels.length;

        for (int i = 0; i < numLabels; i++) {
            c.gridwidth = GridBagConstraints.RELATIVE; //next-to-last
            c.fill = GridBagConstraints.NONE;      //reset to default
            c.weightx = 0.0;                       //reset to default
            container.add(labels[i], c);

            c.gridwidth = GridBagConstraints.REMAINDER;     //end row
            c.fill = GridBagConstraints.HORIZONTAL;
            c.weightx = 1.0;
            container.add(textFields[i], c);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String prefix = "You typed \"";
        if (searchPrompt.equals(e.getActionCommand())) {
            JTextField source = (JTextField) e.getSource();
            actionLabel.setText(prefix + source.getText() + "\"");
        }
    }
}
