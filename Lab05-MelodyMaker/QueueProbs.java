import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.Arrays;

public class QueueProbs
{
    //3
    Queue<Integer> evenFirst(Queue<Integer> nums)
    {
        Queue<Integer> next = new LinkedList<>();
        Queue<Integer> odd = new LinkedList<>();

        while (!nums.isEmpty())
        {
            if (nums.peek() % 2 == 0)
            {
                next.offer(nums.poll());
            }
            else
            {
                odd.offer(nums.poll());
            }
        }
        while (!odd.isEmpty())
        {
            next.offer(odd.poll());
        }
        return next;
    }

    //4
    boolean numPalindrome(Queue<Integer> nums)
    {
        Stack<Integer> newStack = new Stack<>();
        Queue<Integer> repeat = new LinkedList<>();

        boolean setPal = true;

        while (!nums.isEmpty())
        {
            newStack.push(nums.peek());
            repeat.offer(nums.poll());
        }

        while (!repeat.isEmpty() && !newStack.isEmpty())
        {
            if (newStack.pop() != repeat.poll())
            {
                setPal = false;
            }

        }
        return setPal;
    }
    
    //6
    Stack<Integer> sieveOfErato(int n)
    {
       Queue<Integer> cons = new LinkedList<Integer>();
       for (int i = 1; i < n; i++)
       {
           cons.offer(i + 1); 
       }
       
       Stack<Integer> primes = new Stack<>();
       
       while (cons.peek() != null)
       {
           int p = (cons.poll());
           int size = cons.size();
           for (int i = 0; i < size; i++)
           {
               int test = cons.poll();
               if (test % p != 0)
               {
                   cons.offer(test);
               }
           }
           primes.push(p);
       }
       return primes;
    }
}