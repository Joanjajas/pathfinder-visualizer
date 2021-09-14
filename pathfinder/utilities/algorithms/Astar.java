package utilities.algorithms;

import panels.*;
import utilities.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

public class Astar {
    private PriorityQueue<Node> openSet;
    private ArrayList<Node> closedSet;
    private ArrayList<Node> path;
    private Grid grid;
    private boolean showSteps;
    private boolean isRunning;
    private Node startNode;
    private Node finalNode;

    public Astar() {
        openSet = new PriorityQueue<Node>();
        closedSet = new ArrayList<Node>();
        path = new ArrayList<Node>();
        showSteps = true;
        isRunning = true;
    }

    public void run(GridPanel gridPanel, int delay) {
        isRunning = true;
        grid = gridPanel.getGrid();

        startNode = grid.getNode(0, 0);
        finalNode = grid.getNode(grid.getSize() - 1, grid.getSize() - 1);
        openSet.add(startNode);

        while (!openSet.isEmpty() && isRunning) {

            Node currentNode = openSet.poll();
            closedSet.add(currentNode);
            currentNode.setType("closed");

            if (currentNode == finalNode) {
                break;
            }

            for (Node nextNode : currentNode.getNeighbours()) {

                if (closedSet.contains(nextNode) || nextNode.getType() == "wall") {
                    continue;
                }

                int distanceFromStart = currentNode.getDistanceFromStart() + 1;
                int distanceToGoal = heuristic(nextNode, finalNode);
                int nodeWeight = distanceFromStart + distanceToGoal;

                if (!openSet.contains(nextNode) || distanceFromStart < nextNode.getDistanceFromStart()) {
                    nextNode.setDistanceFromStart(distanceFromStart);
                    nextNode.setWeight(nodeWeight);
                    nextNode.setFather(currentNode);
                    openSet.add(nextNode);
                    nextNode.setType("open");
                }
            }
            if (showSteps) {
                gridPanel.repaint();
            }
            sleep(delay);
        }

        Node current = finalNode;
        path.add(current);

        while (current.getFather() != null) {
            Node father = current.getFather();
            path.add(father);
            current = father;
        }

        drawPath(path, gridPanel);
        reset();
        gridPanel.repaint();
    }

    // Stops the algorithm
    public void stop() {
        isRunning = false;
    }

    public void showSteps(boolean bool) {
        showSteps = bool;
    }

    private void drawPath(ArrayList<Node> path, GridPanel gridPanel) {
        for (Node pathNode : path) {
            if (isRunning) {
                pathNode.setType("path");

                if (showSteps) {
                    gridPanel.repaint();
                    sleep(50);
                }

            } else {
                break;
            }
        }
    }

    private void sleep(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {

        }
    }

    // Reset algorithm variables data
    private void reset() {
        for (Node node : path) {
            node.setFather(null);
        }

        openSet = new PriorityQueue<Node>();
        closedSet = new ArrayList<Node>();
        path = new ArrayList<Node>();
    }

    // Heuristic formula to calcute node distance from final node
    private static int heuristic(Node a, Node b) {
        int x = Math.abs(a.getXPos() - b.getXPos());
        int y = Math.abs(a.getYPos() - b.getYPos());
        return x + y;
    }
}
