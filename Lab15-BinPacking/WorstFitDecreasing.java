import java.util.*;
import java.io.*;
public class WorstFitDecreasing {
   public static void main(String[] args) {
      Queue<Disk> queue = new PriorityQueue<>();
      queue.offer(new Disk());
      Queue<Integer> files = new PriorityQueue<>(Collections.reverseOrder());
      
      Scanner reader = new Scanner(System.in);
      System.out.print("Please enter file name: ");
      String fileName = reader.next();

      double totalSize = 0;

      Scanner console = null;
      try {
            console = new Scanner(new File(fileName));
      }
      catch (IOException e) { System.out.print("Can't find file!"); }
      
      while (console.hasNextInt())
      {
          int data = console.nextInt();
          totalSize += data;
          
          files.offer(data);
      }
      
      while (!files.isEmpty())
      {
          int data = files.poll();
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