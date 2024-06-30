//-----------------------------------------------------
// Title: Main Class
// Author: Erkan Sancak
// ID: 44293566706
// Section: 2
// Assignment: 3
// Description: This class finds the shortest paths from a source vertex to target vertices in a directed graph.
//-----------------------------------------------------
import java.util.*; // Imports utility classes for data structures like List and Arrays

public class HW3_Q2_solution {
	
    //--------------------------------------------------------
    // Summary: Implements Dijkstra's algorithm to find the shortest paths from the source vertex.
    // Precondition: The graph must be represented as a 2D array, and the source vertex must be valid.
    // Postcondition: Updates the dist[] array with shortest path distances and the prev[] array with the path structure.
    //--------------------------------------------------------
    public static void dijkstra(int[][] graph, int src, int[] dist, int[] prev) {
        int V = graph.length;
        boolean[] sptSet = new boolean[V]; // Array to keep track of vertices 
        
        // Initializes distances and sptSet
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE; // Sets all distances to infinity
            sptSet[i] = false; // No vertices are included initially	
            prev[i] = -1; // Initializes previous vertex array 
        }
        
        dist[src] = 0; // Distance from source to itself
        
        // Finds the shortest path for all vertices
        // Picks the minimum distance vertex and marks the picked vertex as processed
        for (int count = 0; count < V - 1; count++) {
            int u = minDistance(dist, sptSet);
            sptSet[u] = true;
            
            // Updates dist value of adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                if (!sptSet[v] && graph[u][v] 
                		!= Integer.MAX_VALUE && dist[u] 
                		!= Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }
    }

    
    //--------------------------------------------------------
    // Summary: Finds the vertex with the minimum distance value that is not yet included in the shortest path tree.
    // Precondition: The dist[] array must be initialized with distances, and sptSet[] must track the included vertices.
    // Postcondition: Returns the index of the vertex with the minimum distance value.
    //--------------------------------------------------------
    public static int minDistance(int[] dist, boolean[] sptSet) {
        int min = Integer.MAX_VALUE, minIndex = -1;
        // Loops through all vertices to find the minimum distance value
        for (int v = 0; v < dist.length; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v]; // Updates min to the distance value of vertex v
                minIndex = v; // Updates minIndex to the index of vertex v
            }
        }
        return minIndex; // Returns the index of the vertex with the minimum distance value
    }

    //--------------------------------------------------------
    // Summary: Prints the graph's adjacency matrix representation.
    // Precondition: The graph must be represented as a 2D array.
    // Postcondition: Outputs the graph's vertices and edges.
    //--------------------------------------------------------
    public static void printGraph(int[][] graph) {
        int V = graph.length; // Number of vertices
        int E = 0; // Initializes edge count
        List<String> edges = new ArrayList<>(); // List to hold the edges
        
        // Loops through the adjacency matrix to count and prints the edges
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (graph[i][j] != Integer.MAX_VALUE) {
                    edges.add(i + " " + j + " " + graph[i][j]); // Adds the edge to the list
                    E++;
                }
            }
        }
        
        System.out.println("V=" + V); // Prints number of vertices
        System.out.println("E=" + E); // Prints number of edges
        for (String edge : edges) {
            System.out.println(edge); // Prints each edge
        }
    }
    
    //--------------------------------------------------------
    // Summary: Recursively builds the path from the source to the given vertex.
    // Precondition: The prev[] array must be filled by Dijkstra's algorithm.
    // Postcondition: Adds the path vertices to the given list.
    //--------------------------------------------------------
    public static void printPath(int[] prev, int j, List<Integer> path) {
        if (prev[j] == -1) // Base case for recursion
            return;
        printPath(prev, prev[j], path); // Recursive call to build the path
        path.add(j); // Adds the vertex to the path list
    }
    
    //--------------------------------------------------------
    // Summary: Main method to read the graph, print it, and find the shortest paths.
    // Precondition: The input file must be correctly formatted.
    // Postcondition: Outputs the graph and the shortest paths from the source vertex.
    //--------------------------------------------------------
    public static void main(String[] args) {
        List<String> lines = FileRead.readFile("HW3_Q2.txt"); // Reads the contents of the input file into a list of strings
        int[][] graph = Valuefinder.createGraph(lines); // Creates the graph as an adjacency matrix from the input data
        
        printGraph(graph); // Prints the graph

        System.out.println("The result");

        int[] targets = {1, 2, 3}; // Target vertices
        for (int target : targets) {
            int[] dist = new int[graph.length]; // Array to store shortest distances
            int[] prev = new int[graph.length]; // Array to store the path structure
            dijkstra(graph, 0, dist, prev); // Calls "Dijkstra's algorithm"
            
            List<Integer> path = new ArrayList<>(); // List to hold the path
            path.add(0); // Adds source vertex to path
            printPath(prev, target, path); // Builds the path to the target
            
            // Prints the path
            for (int i = 0; i < path.size(); i++) {
                System.out.print(path.get(i));
                if (i < path.size() - 1) {
                    System.out.print(" ");
                }
            }
            System.out.println(" " + dist[target]); // Prints the distance to the target
        }
    }
}
