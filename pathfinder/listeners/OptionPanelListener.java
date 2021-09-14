package listeners;

import utilities.algorithms.*;
import utilities.Grid;
import panels.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class OptionPanelListener implements ActionListener, ChangeListener, ItemListener {
    private static JCheckBox allowDiagonalCheckBox;
    private static JCheckBox showStepsCheckBox;
    private static JButton startButton;
    private static JLabel delayLabel;
    private static JLabel sizeLabel;
    private static JLabel runTimeLabel;
    private static JSlider delaySlider;

    private OptionPanel optionPanel;
    private GridPanel gridPanel;
    private Grid grid;
    private Astar astar;
    private int delay;

    public OptionPanelListener(OptionPanel optionPanel) {
        this.optionPanel = optionPanel;
        astar = new Astar();
        delay = 25;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

	setComponents();

        switch (e.getActionCommand()) {
            case "startButton":
                // Run algorithm in a worker so EDT don't get blocked
                SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                    protected Void doInBackground() {
                        runAlgorithm();
                        return null;
                    }
                };
                worker.execute();
                break;

            case "randomButton":
                astar.stop();
                grid.createRandomGrid();
                break;

            case "clearButton":
                astar.stop();
                grid.clear();
                break;
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {

	setComponents();
        JSlider source = ((JSlider) e.getSource());

        switch (source.getName()) {
            case "delaySlider":
                // Set the new delay and update delay label text
                delay = source.getValue() * 5;
                delayLabel.setText("Delay: " + delay + "ms");
                break;

            case "sizeSlider":
                // set size label text
                int newGridSize = source.getValue() * 10;
                sizeLabel.setText("Grid size: " + newGridSize + "x" + newGridSize);

                // Set the new grid size
                astar.stop();
                grid.createNodeGrid(newGridSize);
                break;
        }

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
	setComponents();
        if (e.getItemSelectable() == showStepsCheckBox) {
            if (e.getStateChange() == 2) {
                astar.showSteps(false);
                delayLabel.setEnabled(false);
                delaySlider.setEnabled(false);
                delay = 0;
            } else {
                astar.showSteps(true);
                delayLabel.setEnabled(true);
                delaySlider.setEnabled(true);
                delay = delaySlider.getValue() * 5;
            }
        }

	if (e.getItemSelectable() == allowDiagonalCheckBox) {
	    ;
	}
    }

    // Run the selected algorithm
    private void runAlgorithm() {
        startButton.setEnabled(false);
        showStepsCheckBox.setEnabled(false);

        grid.reset();
        long startTime = System.nanoTime();

        astar.run(gridPanel, delay);

        long endTime = System.nanoTime();
        long runTime = (endTime - startTime) / 1000000;
        runTimeLabel.setText("Path found in: " + runTime + "ms");

        startButton.setEnabled(true);
        showStepsCheckBox.setEnabled(true);
    }

    private void setComponents() {
        gridPanel = (GridPanel) getComponent(optionPanel.getParent(), "gridPanel");
        grid = gridPanel.getGrid();

        allowDiagonalCheckBox = (JCheckBox) getComponent(optionPanel, "diagonalCheckBox");
        showStepsCheckBox = (JCheckBox) getComponent(optionPanel, "showStepsCheckBox");
        delaySlider = (JSlider) getComponent(optionPanel, "delaySlider");
        delayLabel = (JLabel) getComponent(optionPanel, "delayLabel");
        sizeLabel = (JLabel) getComponent(optionPanel, "sizeLabel");
        runTimeLabel = (JLabel) getComponent(optionPanel, "runTimeLabel");
        startButton = (JButton) getComponent(optionPanel, "startButton");
    }

    // Method that search component by name in the specified panel
    private Component getComponent(Container panel, String name) {
        for (Component component : panel.getComponents()) {
            if (name.equals(component.getName())) {
                return component;
            }
        }
        return null;
    }
}
