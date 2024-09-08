import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Queue;
import java.util.LinkedList;

public class BinaryMaze
{
    static int[] rowNum = {-1, 0, 0, 1};
    static int[] colNum = {0, -1, 1, 0};
    
    public static int shortestPath(int[][] matrix, Location start, Location end)
    {
        if (matrix[start.x][start.y] != 1 || matrix[end.x][end.y] != 1)
        {
            return -1;
        }
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        
        //visited start
        visited[start.x][start.y] = true;
        
        Queue<queueNode> que = new LinkedList<>();
        
        //distance from start is 0
        queueNode beg = new queueNode(start, 0);
        
        //add the start
        que.add(beg); 
        
        while (!que.isEmpty())
        {
            queueNode temp = que.peek();
            Location point = temp.point;
            
            //if reached end
            if (point.x == end.x && point.y == end.y)
            {
                return temp.dist;
            }
            
            //remove front and add its adjacent values
            que.remove();
            
            for (int i = 0; i < 4; i++)
            {
                int row = point.x + rowNum[i];
                int col = point.y + colNum[i];
                
                //if adj value has path and not visited, add it
                if (isValid(matrix, row, col) && 
                 matrix[row][col] == 1 && !visited[row][col])
                {
                    //visited and add it
                    visited[row][col] = true;
                    queueNode adjCell = new queueNode(new Location(row, col), temp.dist + 1);
                    que.add(adjCell);
                }
            }
        }
        //if end is never found
        return -1;
    }
    public static boolean isValid(int[][] matrix, int row, int col)
    {
        return (row >= 0) && (row < matrix.length) && (col >= 0) 
        && (col < matrix[0].length);
    }
    public static void main(String[] args) throws FileNotFoundException {

        File fileName = new File("maze.dat");
        Scanner console = new Scanner(fileName);

        int rows = console.nextInt();
        int col = console.nextInt();
        int testCases = console.nextInt();
        
        int[][] matrix = new int[rows][col];
        
        for (int i = 0; i < rows; i++)
        {
            for (int k = 0; k < col; k++)
            {
                matrix[i][k] = console.nextInt();
            }
        }
        
        while (testCases != 0)
        {
            Location start = new Location(console.nextInt(), console.nextInt());
            Location end = new Location(console.nextInt(), console.nextInt());
            System.out.println(shortestPath(matrix, start, end));
            testCases--;
        }
    }
    public static class Location
    {
        int x;
        int y;
        public Location(int xCor, int yCor)
        {
            x = xCor;
            y = yCor;
        }
    }
    public static class queueNode
    {
        Location point;
        int dist;
        
        public queueNode(Location pt, int distance)
        {
            point = pt;
            dist = distance;
        }
    }
}
