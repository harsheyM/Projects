import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;
import java.util.PriorityQueue;

public class Djikstra
{
    Graph graph;
    public Djikstra()
    {
        Scanner console = null;
        try
        {
            console = new Scanner(new File("input6.txt"));
        }
        catch (FileNotFoundException e) {}
        graph = new Graph(console);
    }
    public Djikstra(Graph graph)
    {
        this.graph = graph;
    }
    
    double distance(int source, int destination)
    {
        dijkstra(source, destination);
        int distance = 0;
        for (int i = 0; i < graph.adjList.length; i++)
        {
            distance += graph.adjList[i].distance;
        }
        return distance;
    }
    private void dijkstra(int source, int destination)
    {
        graph.adjList[source].distance = 0;
        
        PriorityQueue<Vertex> que = new PriorityQueue<>();
        
        for (Vertex vertex : graph.adjList)
        {
            que.offer(vertex);
        }
        
        while (!que.isEmpty())
        {
            Vertex temp = que.poll();
            for(Integer in : temp.edges)
            {
                if (!graph.adjList[in].visited)
                {
                    relax(temp, graph.adjList[in]);
                }
            }
            temp.visited = true;
        }
        System.out.println(graph.adjList[destination].distance);
    }
    public static void relax(Vertex v, Vertex w)
    {
        double dist = v.distance + v.distance(w);
        if (dist < w.distance)
        {
            w.distance = dist;
            w.previous = v;
        }
    }
    public static void printShortestPath(Vertex vertex)
    {
        if (vertex == null)
        {
            return;
        }
        else
        {
            printShortestPath(vertex.previous);
            System.out.print(vertex + "-> ");
        }
    }
}