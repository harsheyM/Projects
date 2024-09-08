import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class ChildsPlay {
    public static int findFallen(ArrayList<Integer>[] adjList, int cur, boolean[] visited)
    {
        if (visited[cur]) {
            return 0;
        }
        else
        {
            visited[cur] = true;
            int sum = 1;
            for (int i : adjList[cur])
            {
               sum += findFallen(adjList, i, visited);
            }
            return sum;
        }
    }
    public static void main(String[] args) throws FileNotFoundException {

        File fileName = new File("play.dat");
        Scanner console = new Scanner(fileName);

        int numOfTests = console.nextInt();
        console.nextLine();
        while (numOfTests != 0) {
            int n = console.nextInt();
            //n = # of tiles
            int m = console.nextInt();
            //m = # of edge pairs
            int l = console.nextInt();
            //l = # knocked dominoes

            ArrayList<Integer>[] adjList = new ArrayList[n + 1];
            for (int i = 0; i < n + 1; i++)
            {
                adjList[i] = new ArrayList<>();
            }
            for (int i = 0; i < m; i++) {
                adjList[console.nextInt()].add(console.nextInt());
            }
            int sum = 0;
            boolean[] visited = new boolean[n + 1];
            for (int i = 0; i < l; i++)
            {
                sum += findFallen(adjList, console.nextInt(), visited);
            }
            System.out.println(sum);
            numOfTests--;
        }
    }

}