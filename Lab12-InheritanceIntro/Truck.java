//Harshitha Mahesh
//3A

public class Truck extends Vehicle
{
    private int towing;
    
    public Truck(String t, int y, double p, int towCap)
    {
        super(t, y, p);
        towing = towCap;
    }
    boolean canTowBoat()
    {
        if (towing >= 2000)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    @Override
    String getInfo()
    {
        return this.getYear() + " " + this.getType() + ", " + towing + " lbs. towing, $" + this.getPrice();
    }
}