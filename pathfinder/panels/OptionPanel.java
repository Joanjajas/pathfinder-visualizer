package panels;

import listeners.*;
import javax.swing.*;
import java.awt.*;

public class OptionPanel extends JPanel {
    private OptionPanelListener optionPanelListener;

    public OptionPanel() {

        // Set option panel layout and border
        setLayout(new GridLayout(0, 1, 0, 15));
        setBorder(BorderFactory.createTitledBorder("Options"));

        // Create listener
        optionPanelListener = new OptionPanelListener(this);

        // Add Buttons
        add(createButton("startButton", "Start"));
        add(createButton("randomButton", "Generate Random Maze"));
        add(createButton("clearButton", "Clear"));

	// Add Box
	String[] algorithms = {"Astar", "Djisktra"};
        add(createLabel("algorithmsLabel", "Algorithms"));
        add(new JComboBox<String>(algorithms));

	// Add CheckBoxes
        add(createCheckBox("showStepsCheckBox" ,"Show steps", true));
        add(createCheckBox("diagonalCheckBox" ,"Diagonal", false));

	// Add Sliders
	add(createLabel("sizeLabel", "Grid size: 20x20"));
        add(createSlider("sizeSlider", 1, 20, 2));
        add(createLabel("delayLabel", "Delay: 25ms"));
        add(createSlider("delaySlider", 1, 50, 5));
        add(createLabel("runTimeLabel", "Path found in: ...ms"));
    }

    private JButton createButton(String buttonName, String buttonText) {
	JButton button = new JButton(buttonText);
	button.addActionListener(optionPanelListener);
	button.setActionCommand(buttonName);
	button.setName(buttonName);
	return button;
    }

    private JCheckBox createCheckBox(String checkBoxName, String checkBoxText, boolean selected) {
	JCheckBox checkBox = new JCheckBox(checkBoxText);
	checkBox.setSelected(selected);
	checkBox.addItemListener(optionPanelListener);
	checkBox.setName(checkBoxName);
	return checkBox;
    }

    private JSlider createSlider(String sliderName, int min, int max, int init) {
	JSlider slider = new JSlider(min, max, init);
	slider.addChangeListener(optionPanelListener);
	slider.setName(sliderName);
	return slider;
    }

    private JLabel createLabel(String labelName, String labelText) {
	JLabel label = new JLabel(labelText);
	label.setName(labelName);
	return label;
    }
}
