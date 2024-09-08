import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Graph
{
    int V;
    int E;
    int start;
    int end;
    
    Vertex[] adjList;
    
    public Graph(Scanner console)
    {
        try
        {
            console = new Scanner(new File("input6.txt"));
        }
        catch (FileNotFoundException e) {}
        
        this.V = console.nextInt();
        this.E = console.nextInt();
        adjList = new Vertex[V];
        
        for (int i = 0; i < V; i++)
        {
            Vertex temp = new Vertex(console.nextInt(),console.nextInt(), console.nextInt());
            adjList[i] = temp;
        }
        for (int i = 0; i < E; i++)
        {
            adjList[console.nextInt()].addEdge(console.nextInt());
        }
        
        this.start = start;
        this.end = end;
    }
    double distance(int from, int to)
    {
        int toX = adjList[to].x;
        int toY = adjList[to].y;
        return adjList[from].distance(adjList[to]);
    }
}