import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class WorstFit {

    public static void main(String[] args) {
        Queue<Disk> queue = new PriorityQueue<>();

        Scanner reader = new Scanner(System.in);
        System.out.print("Please enter file name: ");
        String fileName = reader.next();

        double totalSize = 0;

        Scanner console = null;
        try {
            console = new Scanner(new File(fileName));
        }
        catch (IOException e) { System.out.print("Can't find file!"); }

        queue.offer(new Disk());

        while (console.hasNextInt())
        {
                int data = console.nextInt();
                totalSize += data;

                Disk temp = queue.poll();

                if (temp.canStore(data)) {
                    temp.addFile(data);
                }
                else
                {
                    Disk newDisk = new Disk();
                    newDisk.addFile(data);
                    queue.offer(newDisk);
                }
                queue.offer(temp);
        }

        
        System.out.println("Total size = " + (totalSize / 1_000_000) + " GB");
        System.out.println("Disks req'd = " + queue.size());

        String total = "";
        while (!queue.isEmpty())
        {
            total += queue.peek().spaceLeft();
            System.out.println(queue.poll());

        }

    }
}