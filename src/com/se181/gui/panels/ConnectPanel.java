package com.se181.gui.panels;

import com.se181.gui.listeners.ConnectButtonListener;

import javax.swing.*;
import java.awt.*;

public class ConnectPanel extends JPanel {

    public static final Dimension initialDimensions = new Dimension(400, 150);

    private GridBagConstraints constraints;
    private JLabel titleLabel;
    private JLabel nickNameLabel;
    public JTextArea nickNameArea;
    public JTextArea serverIPArea;
    private JButton connectButton;
    public JLabel notificationLabel;

    public ConnectPanel() {
        super();

        // Set the layout to be variable-sized grid
        this.setLayout(new GridBagLayout());
        constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.HORIZONTAL;

        constraints.gridx = 0;
        constraints.gridy = 0;
        titleLabel = new JLabel("Welcome to ChessOnAir");
        titleLabel.setFont(new Font(titleLabel.getFont().getName(), Font.BOLD, 24));
        this.add(titleLabel, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        nickNameLabel = new JLabel("Enter your nickname:");
        this.add(nickNameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridy = 2;
        constraints.insets = new Insets(0, 0, 0, 10);
        nickNameArea = new JTextArea();
        this.add(nickNameArea, constraints);

        constraints.gridx = 0;
        constraints.gridy = 3;
        nickNameLabel = new JLabel("Enter the server IP address:");
        this.add(nickNameLabel, constraints);

        constraints.gridx = 0;
        constraints.gridwidth = 2;
        constraints.gridy = 4;
        constraints.insets = new Insets(0, 0, 0, 10);
        serverIPArea = new JTextArea();
        this.add(serverIPArea, constraints);

        constraints.gridx = 2;
        constraints.gridy = 4;
        connectButton = new JButton("Connect");
        connectButton.addActionListener(new ConnectButtonListener());
        this.add(connectButton, constraints);

        constraints.gridx = 0;
        constraints.gridy = 5;
        notificationLabel = new JLabel();
        notificationLabel.setForeground(Color.RED);
        this.add(notificationLabel, constraints);

    }

    public void displayErrorMessage(String message) {
        notificationLabel.setText(message);
    }

    public String getPlayerNickName() {
        return this.nickNameArea.getText();
    }
}
