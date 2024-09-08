import java.util.ArrayList;

//Harshitha Mahesh
//3A

public class Inventory
{
    //ArrayList<Car> cars;
    //ArrayList<Truck> trucks;
    ArrayList<Vehicle> inventory;
    public Inventory(ArrayList<Vehicle> typeV)
    {
        this.inventory = typeV;
    }
    //void addCar(Car c)
    //{
      //  cars.add(c);
    //}
    //void addTruck (Truck t)
    //{
      //  trucks.add(t);
    //}
    void addVehicle(Vehicle v)
    {
        inventory.add(v);
    }
    void listInventory()
    {
        for (int i = 0; i < inventory.size(); i++)
        {
            System.out.println(inventory.get(i).getInfo());
        }
    }
}