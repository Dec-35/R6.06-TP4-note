package agency;

import java.util.List;
import java.util.function.Predicate;

public class RentalAgency {
    private List<Vehicle> vehicles;

    /**
     * Create a new rental agency with a list of vehicles.
     * @param vehicles The list of vehicles.
     */
    public RentalAgency(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }

    /**
     * Create a new rental agency without any vehicle.
     */
    public RentalAgency(){
        this.vehicles = List.of();
    }

    /**
     * Add a vehicle to the rental agency if it is not already in the list.
     * @param vehicle The vehicle to add.
     * @return true if the vehicle was added, false otherwise.
     */
    public boolean add(Vehicle vehicle){
        if(this.vehicles.contains(vehicle)){
            return false;
        }
        this.vehicles.add(vehicle);
        return true;
    }

    /**
     * Remove a vehicle from the rental agency.
     * @param vehicle The vehicle to remove.
     * @throws agency.exceptions.UnknownVehicleException if the vehicle is not in the list.
     */
    public void remove(Vehicle vehicle){
        if(!this.vehicles.contains(vehicle)){
            throw new agency.exceptions.UnknownVehicleException(vehicle);
        }
        this.vehicles.remove(vehicle);
    }

    /**
     * Get the list of vehicles of the rental agency.
     * @return The list of vehicles of the rental agency.
     */
    public List<Vehicle> getVehicles() {
        return this.vehicles;
    }

    /**
    * Returns the list of vehicles of this agency that satisfy the specified criterion
    * The returned vehicles are then « filtered » by the criterion.
    *
    * @param criterion the criterion that the selected cars must satisfy
    * @return the list of cars of this agency that satisfy the given criterion
    */
    public List<Vehicle > select (Predicate<Vehicle> criterion) {
        List<Vehicle> selectedVehicles = List.of();

        for(Vehicle vehicle : this.vehicles){
            if(criterion.test(vehicle)){
                selectedVehicles.add(vehicle);
            }
        }
        return selectedVehicles;
    }


    /**
    * Prints the vehicles (one by line) of this agency that satisfy the specified criterion
    * @param criterion the criterion that the selected cars must satisfy
    */
    public void printSelectedVehicles (Predicate<Vehicle> criterion) {
        for(Vehicle vehicle : this.select(criterion)){
            System.out.println(vehicle);
        }
    }
}
