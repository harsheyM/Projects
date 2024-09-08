import java.util.Scanner;

//Harshitha Mahesh
//3A

public class CombinationLock extends Lock
{
    private String combination;
    
    public CombinationLock()
    {
        super();
        combination = "";
    }
    public CombinationLock(String combo)
    {
        super();
        combination = combo;
    }
    public void setCombination(String combo)
    {
        this.combination = combo;
    }
    public String getCombination()
    {
        return combination; 
    }
    
    @Override
    void open()
    {
       System.out.print("Enter combination -->");
       Scanner console = new Scanner(System.in);
       String combo = console.nextLine();
       if (this.combination.equals(combo))
       {
           super.open();
       }
    }
    @Override
    public String toString()
    {
        return super.toString() + "  " + "Combination - " + combination + "\n";
    }
}