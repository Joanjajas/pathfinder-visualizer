package utilities;

import panels.*;

public class Grid {
    public GridPanel gridPanel;
    private Node[][] nodeGrid;
    public Node startNode;
    public Node finalNode;
    public int gridSize;

    public Grid(GridPanel gridPanel, int gridSize) {
        this.gridPanel = gridPanel;
        this.gridSize = gridSize;

        createNodeGrid(gridSize);
    }

    public Node getNode(int i, int j) {
        return nodeGrid[i][j];
    }

    public int getSize() {
        return gridSize;
    }

    // Associates to each grid cell a node and calculates their neighbours
    public void createNodeGrid(int gridSize) {
        nodeGrid = new Node[gridSize][gridSize];
        this.gridSize = gridSize;

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                nodeGrid[i][j] = new Node(i, j, "clear");
            }
        }

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                nodeGrid[i][j].getNeighbours(nodeGrid);
            }
        }

        setTargetNodes();
        gridPanel.repaint();
    }

    // Create random wall nodes in the grid
    public void createRandomGrid() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {

                double random = Math.random();

                if (random > 0.75) {
                    nodeGrid[i][j].setType("wall");
                } else {
                    nodeGrid[i][j].setType("clear");
                }
            }
        }
        gridPanel.repaint();
    }

    // Reset the grid without removing current walls
    public void reset() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {

                Node node = nodeGrid[i][j];
                String nodeType = node.getType();

                if (!("clear".equals(nodeType) || "wall".equals(nodeType))) {
                    node.setType("clear");
                }
            }
        }
    }

    // Clears the grid
    public void clear() {
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                nodeGrid[i][j].setType("clear");
            }
        }

        gridPanel.repaint();
    }

    private void setTargetNodes() {
        startNode = nodeGrid[0][0];
        finalNode = nodeGrid[gridSize - 1][gridSize - 1];
        startNode.setType("start");
        finalNode.setType("final");
    }
}
