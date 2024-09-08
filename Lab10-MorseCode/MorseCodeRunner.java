//Name: Harshitha Mahesh
//Period: 3A

import java.io.FileNotFoundException;

public class MorseCodeRunner
{
    public static void main(String[] args) throws FileNotFoundException
    {
        try
        {
            HashMapProbs.runProbs();
        }
        catch (FileNotFoundException e)
        {
            throw new FileNotFoundException("Could not find the file");
        }
       
        System.out.println("\n");
        MorseCode m = new MorseCode();
        System.out.println(m.encode("hello world"));
        System.out.println("\n");
        System.out.println(m.decode("--- -- --. | .. - | .-- --- .-. -.- . -.. "));
       
    }
}
