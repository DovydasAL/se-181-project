package com.se181.gui.panels;

import javax.swing.*;
import java.awt.*;

public class HelpPanel extends JPanel {
    public static final Dimension initialDimensions = new Dimension(500, 500);

    public HelpPanel() {
        this.setLayout(new BorderLayout());
        JTextPane helpPane = new JTextPane();
        helpPane.setContentType("text/html");
        JScrollPane helpScrollPane = new JScrollPane(helpPane);
        helpScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        helpScrollPane.setPreferredSize(new Dimension(250, 250));
        helpPane.setText("<html><h1 style=\"text-align:center\">Section 1</h1>" +
                "<p>Here is help for section 1</p><br><br><br><br><br><br><br><br><br><br><br><br><p>Another Line</p><br><br><br><br>" +
                "<h1 style=\"text-align:center\">Section 2</h1>" +
                "<p>Here is help for section 2</p><br><br><br><br>" +
                "<h1 style=\"text-align:center\">Section 3</h1>" +
                "<p>Help for section 3</p></html>");
        helpPane.setCaretPosition(0);
        helpPane.setEditable(false);
        this.add(helpScrollPane, BorderLayout.CENTER);
    }
}
