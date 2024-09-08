import java.util.Arrays;
import java.util.List;
import java.util.HashMap;

public class BacktrackingProblems {

    
    void printBinary(int digits)
    {
        printBinary(digits, "");
        printBinary(digits, "");
    }
    private void printBinary(int digits, String soFar)
    {
        if (digits == soFar.length())
        {
            System.out.print(soFar + " ");
        }
        else
        {

            printBinary(digits, "0" + soFar);
            printBinary(digits, "1" + soFar);
        }
    }

    void climbingStairs(int steps)
    {
        climbingStairs(steps, "");
    }
    private void climbingStairs(int steps, String add)
    {
        if (steps < 0)
        {
            return;
        }
        else if (steps == 0) {
            System.out.println(add);
        }
        else
        {
            climbingStairs(steps - 1 , add + "1 ");
            climbingStairs(steps - 2 , add + "2 ");;
        }

    }

    void campsite(int x, int y)
    {
        campsite(x, y, 0, 0,"");
    }
    private void campsite(int x, int y, int curX, int curY, String route)
    {
        if (curX == x && curY == y)
        {
            System.out.println(route);
        }
        else if (curX > x || curY > y) {
            return;
        }
        else
        {
            campsite(x, y, curX + 1, curY, route + "E ");
            campsite(x, y, curX, curY + 1, route + "N " );
            campsite(x, y, curX + 1, curY + 1, route + "NE ");
        }
    }

    int getMax(List<Integer> nums, int limit)
    {
        return getMax(nums, limit, 0, 0);
    }
    private int getMax(List<Integer> nums, int limit, int sum, int p)
    {
        if (p == nums.size())
        {
            if (sum > limit) 
            {
                return Integer.MIN_VALUE;
            }
            return sum;
        }
        return Math.max(getMax(nums, limit, sum + nums.get(p), p + 1), getMax(nums, limit, sum, p + 1));
    }
   
    int makeChange(int amount)
    {
        HashMap<String, Boolean> hash = new HashMap<String, Boolean>();
        return makeChange(amount, 0, 0, 0, 0, 0, hash);
    }
    int makeChange(int amount, int curAm,int pen, int nic, int dim, int qua, HashMap<String,Boolean> hash)
    {
        if (amount == curAm)
        {
            String num = pen + " " + nic + " " + dim + " " + qua;
            if (hash.get(num) != null)
            {
                return 0;
            }
            hash.put(num, true);
            return 1;
        }
        else if (amount < curAm)
        {
            return 0;
        }
        int rand = 0;
        if (amount - curAm >= 1)
         rand += makeChange(amount,curAm + 1, pen + 1, nic, dim, qua, hash);
        if (amount - curAm >= 5)
         rand += makeChange(amount,curAm + 5, pen, nic + 1, dim, qua, hash);
        if (amount - curAm >= 10)
         rand += makeChange(amount,curAm + 10, pen, nic, dim + 1, qua, hash);
        if (amount - curAm >= 25)
         rand += makeChange(amount,curAm + 25, pen, nic, dim,qua + 1, hash);
        return rand;
    }
    void printChange (int amount) {
      HashMap<String,Boolean> hash = new HashMap<String,Boolean>();
      System.out.println(" P  N  D  Q");
      System.out.println("------------");
      makeChange(amount,0,0,0,0,0, hash);
      hash.entrySet().forEach(entry -> { System.out.println("[" + entry.getKey() + "]");});
      System.out.println();
   }
   
   String longestCommonSub(String a, String b)
   {
       return longestCommonSubHelper(a, b);
   }
   private String longestCommonSubHelper(String a, String b)
   {
       if (a.length() == 0 || b.length() == 0)
       {
           return "";
       }
       else if (a.charAt(0) == b.charAt(0))
       {
           return a.charAt(0) + longestCommonSubHelper(a.substring(1), b.substring(1));
       }
       return longerString(longestCommonSubHelper(a.substring(1), b), longestCommonSubHelper(a, b.substring(1)));
   }
   private String longerString(String a, String b)
   {
       if (a.length() > b.length())
       {
           return a;
       }
       return b;
   }
   
   
    public static void main(String[] args) {
        BacktrackingProblems problems = new BacktrackingProblems();
        problems.printBinary(3);
        System.out.println();
        System.out.println();
        problems.climbingStairs(4);
        System.out.println();
        System.out.println();
        problems.campsite(2,1);
        System.out.println();
        System.out.println(problems.getMax(Arrays.asList(7, 30, 8, 22, 6, 1, 14), 19));
        System.out.println();
        System.out.println();
        System.out.println(problems.makeChange(25));
        System.out.println();
        problems.printChange(11);
        System.out.println();
        System.out.println();
        System.out.println(problems.longestCommonSub("ABCDEFG", "BGCEHAF"));
    }
}