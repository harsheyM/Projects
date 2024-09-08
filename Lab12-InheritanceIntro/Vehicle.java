//Harshitha M
//3A

public class Vehicle
{
    private String type;
    private int year;
    private double price;
    
    public Vehicle(String t, int y, double p)
    {
        type = t;
        year = y;
        price = p;
    }
    String getType()
    {
        return type;
    }
    int getYear()
    {
        return year;
    }
    double getPrice()
    {
        return price;
    }
    String getInfo()
    {
        return this.year + " " + this.type;
    }
}