import java.util.Stack;

public class RecursionProbs {

    double sumReciprocals(int n)
    {
        if (n == 1)
        {
            return 1;
        }
        else
        {
            return (((double) 1/n) + sumReciprocals(n - 1));
        }
    }

    public int productOfEvens(int n)
    {
        if (n == 1)
        {
            return 2;
        }
        else
        {
            return (n * 2) * (productOfEvens(n - 1));
        }
    }

    String conversion(int num, int base)
    {
        if (num == 0)
        {
            return "";
        }
        else
        {
            String s = "" + (num % base);
            return conversion(num/base, base) + s;
        }
    }

    int matchingDigits(int a, int b)
    {
        if (a == 0 || b == 0)
        {
            return 0;
        }
        else
        {
            if ((a % 10) == (b % 10))
            {
                a = a / 10;
                b = b / 10;
                return 1 + matchingDigits(a, b);
            }
            a = a / 10;
            b = b / 10;
            return 0 + matchingDigits(a, b);

        }
    }

    void doubleUp(Stack<Integer> nums)
    {
          if (nums.size() == 0)
          {
              return;
          }
          else
          {
              int num = nums.pop();
              doubleUp(nums);
              nums.push(num);
              nums.push(num);
          }
    }

    void printThis(int n)
    {
        if (n == 1 || n == 2)
        {
            if (n == 2) {
                System.out.print("**");
            }
            else
            {
                System.out.print("*");
            }
        }
        else
        {
            System.out.print("<");
            printThis(n - 2);
            System.out.print(">");

        }
    }

    void printNums2(int n)
    {
        if (n == 1 || n == 2)
        {
            if (n == 1)
            {
                System.out.print("1");
            }
            else
            {
                System.out.print("1 1");
            }
        }
        else
        {
            if (n % 2 == 0) {
                System.out.print((n / 2) + " ");
                printNums2(n - 2);
                System.out.print(" " + (n / 2));
            }
            else
            {
                System.out.print(((n / 2) + 1) + " ");
                printNums2(n - 2);
                System.out.print(" " + ((n / 2) + 1));
            }
        }
    }

    public static void main(String[] args) {
        RecursionProbs probs = new RecursionProbs();
        System.out.println(probs.sumReciprocals(10));
        System.out.println(probs.productOfEvens(4));
        System.out.println(probs.conversion(10,2));
        System.out.println(probs.conversion(1453,8));
        System.out.println(probs.matchingDigits(1000,0));
        System.out.println(probs.matchingDigits(298892,7892));

        Stack<Integer> nums = new Stack<>();
        nums.push(3);
        nums.push(7);
        nums.push(12);
        nums.push(9);
        probs.doubleUp(nums);
        System.out.println(nums);

        System.out.println(" ");

        probs.printThis(1);
        System.out.println(" ");
        probs.printThis(2);
        System.out.println(" ");
        probs.printThis(3);
        System.out.println(" ");
        probs.printThis(4);
        System.out.println(" ");
        probs.printThis(5);
        System.out.println(" ");
        probs.printThis(6);
        System.out.println(" ");
        probs.printThis(7);

        System.out.println();
        System.out.println();

        probs.printNums2(1);
        System.out.println(" ");
        probs.printNums2(2);
        System.out.println(" ");
        probs.printNums2(3);
        System.out.println(" ");
        probs.printNums2(4);
        System.out.println(" ");
        probs.printNums2(5);
        System.out.println(" ");
        probs.printNums2(6);
        System.out.println(" ");
        probs.printNums2(7);
        System.out.println(" ");
        probs.printNums2(8);
        System.out.println(" ");
        probs.printNums2(9);
        System.out.println(" ");
        probs.printNums2(10);
    }
}