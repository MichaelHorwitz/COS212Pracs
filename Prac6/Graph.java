import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Graph {
    private String[] vertices;
    private Integer[][] adjacencyMatrix;
    private int numVertices;
    private int numEdges;

    public Graph(String fileName) {
    }

    public void insertVertex(String name) {
    }

    public void insertEdge(String start, String end, int weight) {
    }

    public void removeVertex(String name) {
    }

    public void removeEdge(String start, String end) {
    }

    @Override
    public String toString() {
        return "";
    }

    public String depthFirstTraversal() {
        return "";
    }

    public String breadthFirstTraversal() {
        return "";
    }

    public Double[][] shortestPaths() {
        return null;
    }

    public Double shortestPath(String start, String end) {
        return null;

    }

    public boolean cycleDetection() {
        return false;
    }

    public String stronglyConnectedComponents() {
        return "";
    }
}