//Harshitha Mahesh
//3A
import java.util.ArrayList;

public class Runner
{
    public static void main(String[] args)
    {
        ArrayList<Vehicle> vehicle = new ArrayList<Vehicle>();

        Car carHonda = new Car("Honda Civic", 1998, 6499.0, 32.0);
        Car carTesla = new Car("Tesla", 2022, 6400990.0, 3.0);
        
        Truck truckFord = new Truck("Ford F-150", 2004, 8999.0, 3200);
        Truck truckChevy = new Truck("Chevy C-140", 2012, 9999.0, 2300);
        
        Inventory carsTrucks = new Inventory(vehicle);

        carsTrucks.addVehicle(carHonda);
        carsTrucks.addVehicle(carTesla);
        carsTrucks.addVehicle(truckFord);
        carsTrucks.addVehicle(truckChevy);
        
        Vehicle v = new Car("Honda Civic", 2000, 6500.0, 32.0);
        carsTrucks.addVehicle(v);
        
        Vehicle m = new Motorcycle("Suzuki", 2022, 2000, 3);
        carsTrucks.addVehicle(m);
        
        carsTrucks.listInventory();
    }
}