package listeners;

import panels.*;
import utilities.*;
import java.awt.event.*;

public class GridListener implements MouseMotionListener, MouseListener {
    GridPanel gridPanel;
    Grid grid;
    int pressedButton;

    public GridListener(GridPanel gridPanel) {
        this.gridPanel = gridPanel;
        grid = gridPanel.getGrid();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int gridSize = grid.getSize();

        int xPos = e.getX() / (gridPanel.getWidth() / gridSize);
        int yPos = e.getY() / (gridPanel.getHeight() / gridSize);

        if (xPos >= 0 && xPos < gridSize && yPos >= 0 && yPos < gridSize) {
            Node node = grid.getNode(xPos, yPos);

            if (pressedButton == MouseEvent.BUTTON1) {
                node.setType("wall");
            }

            if (pressedButton == MouseEvent.BUTTON3) {
                if ("wall".equals(node.getType())) {
                    node.setType("clear");
                }
            }

            gridPanel.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressedButton = e.getButton();
    }

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}
}
