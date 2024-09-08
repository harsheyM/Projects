//Harshitha Mahesh
//3A

public class Car extends Vehicle
{
    private double mpg;
    public Car(String t, int y, double p, double mpgFuel)
    {
        super(t, y, p);
        mpg = mpgFuel;
    }
    boolean greatMPG()
    {
        if (mpg >= 36)
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
        return super.getYear() + " " + super.getType() + ", " + this.mpg + " mpg, $" + super.getPrice();
    }
}