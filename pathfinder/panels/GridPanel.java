package panels;

import utilities.*;
import listeners.*;
import java.awt.*;
import javax.swing.*;

public class GridPanel extends JPanel {
    private Grid grid;
    private int panelWidth;
    private int panelHeight;
    private GridListener listener;

    public GridPanel(int panelWidth, int panelHeight) {
        grid = new Grid(this, 20);

        this.panelWidth = panelWidth;
        this.panelHeight = panelHeight;

        // Add listeners to the panel
        listener = new GridListener(this);
        addMouseMotionListener(listener);
        addMouseListener(listener);

        // Set panel properties
        setPreferredSize(new Dimension(panelWidth, panelHeight));
        setBackground(Color.black);
        setName("gridPanel");
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        int gridSize = grid.getSize();
        int nodeWidth = panelWidth / gridSize;
        int nodeHeigth = panelHeight / gridSize;

        // Paints Grid
        g.setColor(Color.darkGray);
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                g.drawLine(i * nodeWidth, 0, i * nodeWidth, panelHeight);
                g.drawLine(0, j * nodeHeigth, panelWidth, j * nodeHeigth);
            }
        }

        // Paints Nodes
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Node node = grid.getNode(i, j);
                if (node.getType() != "clear") {
                    node.drawNode(g, nodeWidth, nodeHeigth);
                }
            }
        }
    }

    public Grid getGrid() {
        return grid;
    }
}
