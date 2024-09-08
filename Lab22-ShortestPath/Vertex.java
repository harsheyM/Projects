import java.util.List;

public class Vertex implements Comparable<Vertex>
{
    int x, y, ID;
    List<Integer> edges;
    boolean visited;
    double distance;
    Vertex previous;
    
    public Vertex(int ID, int x, int y)
    {
        this.x = x;
        this.y = y;
        this.ID = ID;
        visited = false;
        distance = Double.POSITIVE_INFINITY;
    }
    public double distance(Vertex other)
    {
        double xDist = Math.pow(other.x - this.x, 2);
        double yDist = Math.pow(other.y - this.y, 2);
        
        return Math.sqrt(xDist + yDist);
    }
    public void addEdge(int neighbor)
    {
        edges.add(neighbor);
    }
    @Override
    public int compareTo(Vertex other)
    {
        return (int)(this.distance - other.distance);
    }
    
    @Override
    public String toString()
    {
        return this.ID + "";
    }
}