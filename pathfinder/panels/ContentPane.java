package panels;

import javax.swing.*;
import java.awt.*;

public class ContentPane extends JPanel {
    private GridPanel gridPanel;
    private OptionPanel optionPanel;

    public ContentPane() {
        // Set the content panel layout
        setLayout(new BorderLayout(20, 0));

        // Create new Grid and option panels
        gridPanel = new GridPanel(800, 800);
        optionPanel = new OptionPanel();

        // Add option panel and grid to main window
        add(gridPanel);
        add(optionPanel, BorderLayout.WEST);
    }
}
