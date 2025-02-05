package agency;

import agency.exceptions.UnknownVehicleException;

import java.util.*;
import java.util.function.Predicate;

public class RentalAgency {
    private final List<Vehicle> vehicles;
    private final Map<Client, Vehicle> rentedVehicles;

    /**
     * Create a new rental agency with a list of vehicles.
     * @param vehicles The list of vehicles.
     */
    public RentalAgency(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
        this.rentedVehicles = new HashMap<>();
    }

    /**
     * Create a new rental agency without any vehicle.
     */
    public RentalAgency(){
        this(new ArrayList<>());
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
        List<Vehicle> selectedVehicles = new ArrayList<>();

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

    /**
     * Test if a vehicle is rented by a client.
     * @param client The client to test.
     * @return true if the client has a rented vehicle, false otherwise.
     */
    public boolean aVehicleIsRentedBy(Client client){
        return this.rentedVehicles.containsKey(client);
    }

    /**
     * Test if a vehicle is rented.
     * @param vehicle The vehicle to test.
     * @return true if the vehicle is rented, false otherwise.
     */
    public boolean vehicleIsRented(Vehicle vehicle){
        return this.rentedVehicles.containsValue(vehicle);
    }

    /**
     * Rent a vehicle to a client.
     * @param client The client who wants to rent a vehicle.
     * @param vehicle The vehicle to rent.
     * @return The daily rental price of the vehicle.
     * @throws UnknownVehicleException if the vehicle is not in the list.
     * @throws IllegalStateException if the client already has a rented vehicle or if the vehicle is already rented.
     */
    public double rentVehicle(Client client, Vehicle vehicle) throws UnknownVehicleException, IllegalStateException {
        if(!this.vehicles.contains(vehicle)){
            throw new UnknownVehicleException(vehicle);
        }
        if(this.aVehicleIsRentedBy(client)){
            throw new IllegalStateException("Client " + client + " already has a rented vehicle.");
        }
        if(this.vehicleIsRented(vehicle)){
            throw new IllegalStateException("Vehicle " + vehicle + " is already rented.");
        }

        this.rentedVehicles.put(client, vehicle);
        return vehicle.dailyRentalPrice();
    }

    /**
     * Return a vehicle from a client.
     * @param client The client who wants to return a vehicle.
     */
    public void returnVehicle(Client client){
        this.rentedVehicles.remove(client);
    }

    /**
     * Get the list of rented vehicles.
     * @return The list of rented vehicles.
     */
    public Collection<Vehicle> allRentedVehicles(){
        return this.rentedVehicles.values();
    }
}
