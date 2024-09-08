//Name: Harshitha Mahesh
//Period: 3A

import java.util.*;

public class Book
{
    private String title;
    private String author;
    private double price;
    public Book(String newTitle, String newAuthor, double newPrice)
    {
        this.title = newTitle;
        this.author = newAuthor;
        this.price = newPrice;
    }
    public String getTitle()
    {
        return title;
    }
    public String getAuthor()
    {
        return author;
    }
    public double getPrice()
    {
        return price;
    }
    public String toString()
    {
        return title + ", by " + author + ". Cost: $" + price;
    }
}