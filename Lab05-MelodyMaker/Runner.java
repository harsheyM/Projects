import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Runner
{
     public static void main(String[] args)
     {
         //3
         QueueProbs example = new QueueProbs();

         Queue<Integer> evenFirst = new LinkedList(Arrays.asList(3, 5, 4, 17, 6, 83, 1, 84, 16, 37));
         System.out.println(example.evenFirst(evenFirst));

         //4
         Queue<Integer> numPali1 = new LinkedList(Arrays.asList(3, 8, 17, 9, 17, 8, 3));
         Queue<Integer> numPali2 = new LinkedList(Arrays.asList(3, 10, 17, 9, 17, 8, 3));
         System.out.println(example.numPalindrome(numPali1));
         System.out.println(example.numPalindrome(numPali2));
         
         //5
         System.out.println(example.sieveOfErato(10));
     }
}