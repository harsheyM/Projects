//Name: Harshitha Mahesh
//Period: 3A

import java.util.*;

public class ArrayListProbs
{
    public void makeListAndPrint(int num, int limit)
    {
        ArrayList<Integer> makeList = new ArrayList<>();
        Random random = new Random();
        
        for (int i = 0; i < num; i++)
        {
            makeList.add(random.nextInt(limit) + 1);
        }
        System.out.println(makeList);
    }
    public ArrayList<Integer> addOne(ArrayList<Integer> list)
    {
        for (int i = 0; i < list.size(); i++)
        {
            list.set(i, (list.get(i) + 1));
        }
        return list;
    }
    public ArrayList<Integer> minToFront(ArrayList<Integer> list)
    {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < list.size(); i++)
        {
            min = Math.min(list.get(i), min);
        }
        list.add(0, min);
        return list;
    }
    public ArrayList<String> removeDupes(ArrayList<String> list)
    {
        for (int i = list.size() - 2; i >= 0; i--)
        {
            if (list.get(i).equals(list.get(i + 1)))
            {
                list.remove(i + 1);
            }
        }
        return list;
    }
    public ArrayList<Integer> swapPairs(ArrayList<Integer> list)
    {
        for (int i = 0; i < list.size(); i += 2)
        {
            int left = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, left);
        }
        return list;
    }
    public ArrayList<String> removeLenN(ArrayList<String> list, int n)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).length() == n)
            {
                list.remove(i);
            }
        }
        return list;
    }
    public int dumbestPerson(ArrayList<Person> list)
    {
        int dumbest = 0;
        for (int i = 0; i < list.size(); i++)
        {
            if (list.get(i).getIQ() < list.get(dumbest).getIQ())
            {
                dumbest = i;
            }
        }
        return dumbest;
    }
    public Book highestPricedBook(ArrayList<Book> list)
    {
        return (Book) list.stream().sorted(Comparator.comparingDouble(Book::getPrice).reversed()).toArray()[0];
    }
    public ArrayList<Book> banBook(ArrayList<Book> list, Book book)
    {
        list.removeIf(ban -> ban.getTitle().equals(book.getTitle()));
        return list;
    }
    public double bookstoreValue(Bookstore bookstore)
    {
        double[] prices = new double[bookstore.numBooks()];
        for (int i = 0; i < bookstore.numBooks(); i++)
        {
            prices[i] = bookstore.getBook(i).getPrice();
        }
        return Arrays.stream(prices).sum();
    }
}