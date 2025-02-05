package agency;

public interface Vehicle {

    /**
     * Get the brand of the vehicle.
     * @return the brand of the vehicle.
     */
    String getBrand();

    /**
     * Get the model of the vehicle.
     * @return the model of the vehicle.
     */
    String getModel();

    /**
     * Get the production year of the vehicle.
     * @return the production year of the vehicle.
     */
    int getProductionYear();

    /**
     * Get the daily rental price of the vehicle.
     * @return the daily rental price of the vehicle.
     */
    double dailyRentalPrice();

    /**
     * Get a string representation of the vehicle.
     * @return [type of vehicle] [brand] [model] [production year] [vehicle specific details] : [rental price]€
     */
    String toString();
}
