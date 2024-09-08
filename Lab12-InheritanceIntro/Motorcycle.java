//Harshitha Mahesh
//3A

public class Motorcycle extends Vehicle
{
    private int seating;
    public Motorcycle(String t, int y, double p, int seat)
    {
        super(t, y, p);
        seating = seat;
    }
    @Override
    String getInfo()
    {
        return super.getYear() + " " + super.getType() + ", " + this.seating + " seats, $" + super.getPrice();
    }
}