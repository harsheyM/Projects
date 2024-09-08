//harshitha mahesh
//3A

import java.util.*;

public class HorsingAround
{
    public static int totalWidth(ArrayList<Animal> animals)
    {
        int sum = 0;
        for (int i = 0; i < animals.size(); i++)
        {
            sum += animals.get(i).getWidth();
        }
        return sum;
    }

    public static Animal tallestAnimal(ArrayList<Animal> animals)
    {
        int height = 0;
        int index = 0;
        for (int i = 0; i < animals.size(); i++)
        {
            if (animals.get(i).getHeight() > height)
            {
                height = animals.get(i).getHeight();
                index = i;
            }
        }
        return animals.get(index);
    }

    public static int countYourChickens(ArrayList<Animal> animals)
    {
        int count = 0;
        for (int i = 0; i < animals.size(); i++)
        {
            if (animals.get(i).getName().equals("chicken"))
            {
                count++;
            }
        }
        return count;
    }

    public static ArrayList<String> inventory(ArrayList<Animal> animals)
    {
        ArrayList<String> invent = new ArrayList<String>();
        for (int i = 0; i < animals.size(); i++)
        {
            invent.add(i, (animals.get(i).getName()));
        }
        return invent;
    }

    public static void pestControl(ArrayList<Animal> animals)
    {
    	for (int i = 0; i < animals.size(); i++)
        {
            if ((animals.get(i).getName()).equals("mouse"))
            {
                animals.remove(i);
                i--;
            }
        }
    }

    public static void horsingAround(ArrayList<Animal> animals)
    {
    	for (int i = 1; i < animals.size(); i=i+2)
        {
            Animal newHor = new Animal("horse");
            animals.add(i, newHor);
        }
    }

    public static void feelingSheepish(ArrayList<Animal> animals)
    {
    	for (int i = 1; i < animals.size(); i=i+2)
        {
            Animal newShe = new Animal("sheep");
            animals.add(i, newShe);
        }
    }
    
    public static void selectionSort(ArrayList<Animal> animals)
    {
    	for (int i = 0; i < animals.size() - 1; i++) {
            int maxIndex = i;

            for (int j = i + 1; j < animals.size(); j++) {
                if (animals.get(j).getHeight() > animals.get(maxIndex).getHeight()) {
                    maxIndex = j;
                }
            }

            Animal temp = new Animal(animals.get(maxIndex).getName());
            animals.set(maxIndex, animals.get(i));
            animals.set(i, temp);
        }
    }
}