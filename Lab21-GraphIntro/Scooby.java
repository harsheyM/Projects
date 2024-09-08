import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Scooby
{
    public static boolean repeat(int[][] rooms, int currRoom, int endRoom, Set<Integer> visited)
    {
        if (visited.contains(currRoom))
        {
            return false;
        }
        if (currRoom == endRoom)
        {
            return true;
        }
        
        //update the rooms checked
        visited.add(currRoom);
        for (int i = 0; i < rooms[currRoom].length; i++) //look through each row
        {
            if (rooms[currRoom][i] == 1)
            {
                if (repeat(rooms, i, endRoom, visited))
                {
                       return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws FileNotFoundException {

        File fileName = new File("scooby.dat");
        int[][] rooms = new int[26][26];
        Scanner console = new Scanner(fileName);

        int numOfIterations = console.nextInt();
        console.nextLine();

        while (numOfIterations != 0) {
            String[] edges = console.nextLine().split(" ");
            String startEnd = console.nextLine();
            
            for (String edge: edges)
            {
                int a = edge.charAt(0) - 'A';
                int b = edge.charAt(1) - 'A';
                
                rooms[a][b] = 1;
                rooms[b][a] = 1;
            }
            //finished updating matrix

            Set<Integer> visited = new HashSet<>();
            int firstRoom = startEnd.charAt(0) - 'A';
            int endRoom = startEnd.charAt(1) - 'A' ;
            //set up all variables

            boolean answer = repeat(rooms, firstRoom, endRoom, visited);
            if (answer)
            {
                System.out.println("yes");
            }
            else
            {
                System.out.println("no");
            }
            numOfIterations--;
        }
    }
}
