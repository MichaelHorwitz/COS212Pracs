import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.Scanner;

public class Graph {
    private String[] vertices;
    private Integer[][] adjacencyMatrix;
    private int numVertices;
    private int numEdges;

    public Graph(String fileName) {
        if (fileName.equals("")) {
            vertices = new String[0];
            adjacencyMatrix = new Integer[0][0];
            numVertices = 0;
            numEdges = 0;
            return;
        }
        Scanner sc;
        sc = new Scanner(fileName);
        numVertices = sc.nextInt();
        String currLine = sc.nextLine();
        vertices = currLine.split(" ");
        adjacencyMatrix = new Integer[numVertices][numVertices];
        int lineNum = 0;
        while (sc.hasNextLine()) {
            currLine = sc.nextLine();
            String [] currLineArr = currLine.split(" ");
            for (int i = 0; i < currLineArr.length; i++) {
                adjacencyMatrix[lineNum][i] = Integer.valueOf(currLineArr[i]);
            }
            lineNum++;
        }

    }

    public void insertVertex(String name) {
        String[] newVertices = new String[numVertices + 1];
        newVertices[numVertices] = name;
        for (int i = 0; i < numVertices; i++) {
            newVertices[i] = vertices[i];
        }
        vertices[numVertices] = name;
        
        numVertices++;
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