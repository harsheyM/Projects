public class Fibonnaci
{
    public void fibb(int numDig)
    {
        int forward = 1; int previous = 1;
        for (int i = 0; i < numDig; i++)
        {
            System.out.print(previous + " ");
            int temp = forward + previous;
            previous = forward;
            forward = temp;
        }
    }
}