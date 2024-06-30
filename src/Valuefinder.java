//-----------------------------------------------------
// Title: Value Finder Class
// Author: Erkan Sancak
// ID: 44293566706
// Section: 2
// Assignment: 3
// Description: This class converts file data into an adjacency matrix representation of a directed graph.
//-----------------------------------------------------
import java.util.*;  // Imports utility classes for data structures like List and Arrays

public class Valuefinder {
    
    //--------------------------------------------------------
    // Summary: Creates an adjacency matrix for a directed graph.
    // Precondition: The input must be a list of strings from a file.
    // Postcondition: Returns a 2D array representing the graph.
    //--------------------------------------------------------
    public static int[][] createGraph(List<String> lines) {
        int V = Integer.parseInt(lines.get(0));  // Number of vertices
        int E = Integer.parseInt(lines.get(1));  // Number of edges
        int[][] graph = new int[V][V];  // Initializes the adjacency matrix
        
        for (int i = 0; i < V; i++) {
            Arrays.fill(graph[i], Integer.MAX_VALUE);  // Initializes graph with maximum values
        }
        
        // Loops through each edge entry to populate the adjacency matrix
        for (int i = 2; i < 2 + E; i++) {
            String[] parts = lines.get(i).split(" ");
            int u = Integer.parseInt(parts[0]);  // Starting vertex
            int v = Integer.parseInt(parts[1]);  // Ending vertex
            int w = Integer.parseInt(parts[2]);  // Weight of the edge
            graph[u][v] = w;  // Sets the weight for the directed edge from u to v
        }
        return graph;  // Returns the adjacency matrix
    }
}
