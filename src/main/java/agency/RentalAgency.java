package agency;

import java.util.List;

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
}
