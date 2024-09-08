//Name: Harshitha Mahesh
//Period: 3A
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

public class HashMapProbs 
{
    public static void main(String[] args) throws FileNotFoundException
    {
        runProbs();
    }

    public static void runProbs() throws FileNotFoundException
    {
        Scanner scan = new Scanner(System.in);

        //2
        HashMap<String, String> animalSounds = new HashMap<>();
        animalSounds.put("Cat", "Meow");
        animalSounds.put("Cow", "Moo");
        animalSounds.put("Sheep", "Baa");
        animalSounds.put("Dog", "Woof");
        System.out.println(animalSounds + "\n");

        //3
        System.out.print("Enter an animal name >>> ");
        String str = scan.next();
        System.out.println(animalSounds.get(str) + "\n");

        //4
        System.out.println(animalSounds.size() + "\n");

        //5
        System.out.print("Enter an animal >>> ");
        String newAnimal = scan.next();
        System.out.print("Enter the sound the animal makes >>> ");
        String newSound = scan.next();
        animalSounds.put(newAnimal, newSound);
        System.out.println(animalSounds + "\n");

        //7
        System.out.println(takeBefore("str", "bye") + "\n");

        //8
        System.out.println(multiple("hello") + "\n");

        //9:
        System.out.println(charWord(new String[] {"tea", "salt", "soda", "taco"}) + "\n");

        //10
        HashMap<String, Integer> map = new HashMap<>();
        Scanner file = new Scanner(new File("dream.txt"));
        while (file.hasNext()) 
        {
            String token = file.next();
            map.computeIfPresent(token, (key, val) -> ++val);
            map.putIfAbsent(token, 1);
        }
        int max = Collections.max(map.values());
        for (String key : map.keySet())
        {
            if (map.get(key) == max)
            {
               System.out.println("Highest frequency word: " + key + ", " + max);
            }
        }
    }
    
    //7
    public static HashMap<String, String> takeBefore(String a, String b) {
        HashMap<String, String> map = new HashMap<>();
        for (int i = 0; i < a.length(); i++) 
        {
            char first = a.charAt(i);
            char second = b.charAt(i);
            if (first <= second)
                map.put(Character.toString(first), Character.toString(second));
            else
                map.put(Character.toString(second), Character.toString(first));
        }
        return map;
    }
    
    //8
    public static HashMap<String, Boolean> multiple(String s) {
        HashMap<String, Boolean> map = new HashMap<>();
        for (int a = 0; a < s.length(); a++)
        {
            if (s.lastIndexOf(Character.toString(s.charAt(a))) != a)
            {
                map.put(Character.toString(s.charAt(a)), true);
            }
            else
            {
                map.putIfAbsent(Character.toString(s.charAt(a)), false);
            }
        }
        return map;
    }
    
    //9
    public static HashMap<String, String> charWord(String[] words)
    {
        HashMap<String, String> map = new HashMap<>();
        for (String word : words)
        {
            map.computeIfPresent(word.substring(0, 1), (key, val) -> val + word);
            map.putIfAbsent(word.substring(0, 1), word);
        }
        return map;
    }
}