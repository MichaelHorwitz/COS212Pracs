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
        File file;
        try {
            file = new File(fileName);
            System.out.println(file.getCanonicalPath());
            sc = new Scanner(file);
        } catch (Exception e) {
            sc = null;
            e.printStackTrace();
            return;
        }
        String line = sc.next();
        System.out.println(line);
        //numVertices = sc.nextInt();
        numVertices = Integer.valueOf(line);
        sc.nextLine();
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
        Integer[][] newAdjMatrix = new Integer[numVertices + 1][numVertices + 1];
        newVertices[numVertices] = name;
        for (int i = 0; i < numVertices; i++) {
            newVertices[i] = vertices[i];
            for (int j = 0; j < numVertices; j++) {
                newAdjMatrix[i][j] = adjacencyMatrix[i][j];
            }
            newAdjMatrix[i][numVertices] = 0;
        }
        for (int i = 0; i < numVertices; i++) {
            newAdjMatrix[numVertices][i] = 0;
        }
        newAdjMatrix[numVertices][numVertices] = 0;
        newVertices[numVertices] = name;
        numVertices++;
        vertices = newVertices;
        adjacencyMatrix = newAdjMatrix;
    }

    public void insertEdge(String start, String end, int weight) {
        if (weight == 0) {
            return;
        }
        int startIndex = -1, endIndex = -1;
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(start)) {
                startIndex = i;
            } if(vertices[i].equals(end)){
                endIndex = i;
            }
        }
        if (startIndex == -1 || endIndex == -1) {
            return;
        }
        adjacencyMatrix[startIndex][endIndex] = weight;
    }

    public void removeVertex(String name) {
        int index = -1;
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(name)) {
                index = i;
            }
        }
        if (index == -1) {
            return;
        }
        for (int i = index; i < numVertices - 1; i++) {
            vertices[i] = vertices[i+1];
        }
        while (index < numVertices-1) {
            for (int i = 0; i < numVertices; i++) {
                //System.out.println("i: " + i);
                adjacencyMatrix[i][index] = adjacencyMatrix[i][index + 1];
            }
 
            for (int i = 0; i < numVertices; i++) {
                adjacencyMatrix[index][i] = adjacencyMatrix[index + 1][i];
            }
            index++;
        }
        numVertices--;
        String[] newVertices = new String[numVertices];
        Integer[][] newAdjMatrix = new Integer[numVertices][numVertices];
        for (int i = 0; i < numVertices; i++) {
            newVertices[i] = vertices[i];
            for (int j = 0; j < numVertices; j++) {
                newAdjMatrix[i][j] = adjacencyMatrix[i][j];
            }
        }
        vertices = newVertices;
        adjacencyMatrix = newAdjMatrix;
    }

    public void removeEdge(String start, String end) {
        int startIndex = findIndex(start);
        int endIndex = findIndex(end);
        if (startIndex == -1 || endIndex == -1) {
            return;
        }
        adjacencyMatrix[startIndex][endIndex] = 0;

    }

    @Override
    public String toString() {
        if (numVertices == 0) {
            return "(0,0)";
        }
        String ret = "(";
        ret += numVertices;
        ret += ",";
        ret += numEdges;
        ret += ")";
        for (String currVer : vertices) {
            ret += "\t";
            ret += currVer; 
        }
        for (int i = 0; i < numVertices; i++) {
            ret += "\n";
            ret += vertices[i];
            for (int j = 0; j < numVertices; j++) {
                ret += "\t" + adjacencyMatrix[i][j];
            }
        }

        return ret;
    }

    private int findIndex(String vertex){
        int index = -1;
        for (int i = 0; i < numVertices; i++) {
            if (vertices[i].equals(vertex)) {
                index = i;
                return index;
            }
        }
        if (index == -1) {
            return index;
        }
        return -2;
    }
    private int countVisited;
    private int[] visited;
    private int [][] edgesVisited;
    private int countEdgesVisited;
    public String depthFirstTraversal() {
        visited = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = 0;
        }
        edgesVisited = new int[numVertices*numVertices][2];
        countVisited = 1;
        boolean stillRun = true;
        countEdgesVisited = 0;
        while (stillRun) {
            int vertix = arrContains(visited, 0);
            if (vertix == -1) {
                stillRun = false;
            } else {
               DFS(vertix);
            }
        }
        String ret = "";

        for (int i = countEdgesVisited - 1; i >= 0; i--) {
            ret += "\n[" + vertices[edgesVisited[i][0]] + "][" + vertices[edgesVisited[i][1]] + "]";
        }
        return ret;
    }
    private void DFS(int index){
        visited[index] = countVisited++;
        for (int i = 0; i < numVertices; i++) {
            if (adjacencyMatrix[index][i] != 0) {
                if (visited[i] == 0) {
                    edgesVisited[countEdgesVisited][0] = index;
                    edgesVisited[countEdgesVisited][1] = i;
                    countEdgesVisited++;
                    DFS(i);
                }
            }
        }
        return;
    }
    private int arrContains(int[] arr, int val){
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==val) {
                return i;
            }
        }
        return -1;
    }
    public String breadthFirstTraversal() {
        visited = new int[numVertices];
        for (int i = 0; i < numVertices; i++) {
            visited[i] = 0;
        }
        int startQ = 0, endQ = 0;
        countVisited = 1;
        int[] queue = new int[numVertices]; 
        boolean stillRun = true;
        while (stillRun) {
            
            int vertix = arrContains(visited, 0);
            if (vertix == -1) {
                stillRun = false;
            } else {
                visited[vertix] = countVisited++;
                queue[startQ] = vertix;
                startQ++;
                while (startQ != endQ) {
                    vertix = queue[startQ];
                    for (int i = startQ; i < endQ - 1; i++) {
                        queue[i] = queue[i+1];
                    }
                    endQ--;
                    for (int i = 0; i < numVertices; i++) {
                        if (adjacencyMatrix[vertix][i] != 0) {
                            if (visited[i] == 0) {
                                visited[i] = countVisited++;
                                queue[endQ] = i;
                                endQ++;
                                edgesVisited[countEdgesVisited][0] = vertix;
                                edgesVisited[countEdgesVisited][1] = i;
                            }
                        }
                    }
                }

            }
        }
        
        /*
        for all vertices u
            num(u) = 0; // visited #
        edges = null; // path
        i = 1;
        while there is a vertex v such that num(v) is 0
            num(v) = i++; 
            enqueue(v); // add to queue
            while queue is not empty
                v = dequeue(); // visit
                for all vertices u adjacent to v
                    if num(u) is 0 // unvisited
                        num(u) = i++; 
                        enqueue(u);
                        attach edge(vu) to edges;
        output edges;
*/
    String ret = "";

    for (int i = 0; i < countEdgesVisited; i++) {
        ret += "\n[" + vertices[edgesVisited[i][0]] + "][" + vertices[edgesVisited[i][1]] + "]";
    }
    return ret;
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