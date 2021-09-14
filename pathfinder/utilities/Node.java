package utilities;

import java.util.ArrayList;
import java.util.HashMap;
import java.awt.*;

public class Node implements Comparable<Node> {
    private int weight;
    private int distanceFromStart;
    private int distanceToGoal;
    private int xPos;
    private int yPos;
    private Node father;
    private String type;
    private ArrayList<Node> neighbours;
    private HashMap<String, Color> colors;

    public Node(int x, int y, String type) {
        xPos = x;
        yPos = y;
        this.type = type;

        colors = new HashMap<String, Color>();
        colors.put("wall", new Color(32, 106, 93));
        colors.put("path", new Color(245, 75, 52));
        colors.put("open", new Color(255, 204, 41));
        colors.put("closed", new Color(129, 178, 20));
        colors.put("start", new Color(62, 125, 190));
        colors.put("final", new Color(175, 82, 0));

        neighbours = new ArrayList<Node>();
    }

    // Calculates node neighbours
    public void getNeighbours(Node[][] grid) {
        if (xPos > 0) {
            neighbours.add(grid[xPos - 1][yPos]);
        }
        // if (xPos > 0 && yPos > 0) {
        // neighbours.add(grid[xPos - 1][yPos - 1]);
        // }
        if (yPos > 0) {
            neighbours.add(grid[xPos][yPos - 1]);
        }
        // if (xPos < grid.length - 1 && yPos > 0) {
        // neighbours.add(grid[xPos + 1][yPos - 1]);
        // }
        if (xPos < grid.length - 1) {
            neighbours.add(grid[xPos + 1][yPos]);
        }
        // if (xPos < grid.length - 1 && yPos < grid[0].length - 1) {
        // neighbours.add(grid[xPos + 1][yPos + 1]);
        // }
        if (yPos < grid[0].length - 1) {
            neighbours.add(grid[xPos][yPos + 1]);
        }
        // if (xPos > 0 && yPos < grid[0].length - 1) {
        // neighbours.add(grid[xPos - 1][yPos + 1]);
        // }
    }

    public void drawNode(Graphics g, int nodeWidth, int nodeHeigth) {
        g.setColor(colors.get(type));
        g.fillRect(xPos * nodeWidth, yPos * nodeHeigth, nodeWidth, nodeHeigth);
    }

    @Override
    public int compareTo(Node node) {
        return Integer.compare(weight, node.weight);
    }

    public void setType(String type) {
        if (this.type != "start" && this.type != "final") {
            this.type = type;
        }
    }

    public void setDistanceFromStart(int distance) {
        distanceFromStart = distance;
    }

    public void setDistanceToGoal(int distance) {
        distanceToGoal = distance;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setFather(Node father) {
        this.father = father;
    }

    public ArrayList<Node> getNeighbours() {
        return neighbours;
    }

    public int getDistanceFromStart() {
        return distanceFromStart;
    }

    public int getDistantToGoal() {
        return distanceToGoal;
    }

    public int getWeight() {
        return weight;
    }

    public Node getFather() {
        return father;
    }

    public String getType() {
        return type;
    }

    public int getXPos() {
        return xPos;
    }

    public int getYPos() {
        return yPos;
    }
}
