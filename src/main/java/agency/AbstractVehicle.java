package agency;

import util.TimeProvider;

public abstract class AbstractVehicle implements Vehicle{

    private String brand;
    private String model;
    private int productionYear;

    /**
     * Create a new vehicle.
     * @param brand The brand of the vehicle.
     * @param model The model of the vehicle.
     * @param productionYear The production year of the vehicle.
     * @throws IllegalArgumentException if the production year is not between 1900 and the current year.
     */
    public AbstractVehicle(String brand, String model, int productionYear) throws IllegalArgumentException {
        if(productionYear < 1900 || productionYear > TimeProvider.currentYearValue()) {
            throw new IllegalArgumentException("Production year must be between 1900 and the current year. " + productionYear + " is invalid.");
        }
        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
    }

    /**
     * Get the brand of the car.
     * @return The brand of the car.
     */
    @Override
    public String getBrand() {
        return this.brand;
    }

    /**
     * Get the model of the car.
     * @return The model of the car.
     */
    @Override
    public String getModel() {
        return this.model;
    }

    /**
     * Get the production year of the car.
     * @return The production year of the car.
     */
    @Override
    public int getProductionYear() {
        return this.productionYear;
    }

    /**
     * Test if two cars are equal.
     * @param obj The object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if(obj.getClass() == this.getClass()) {
            AbstractVehicle other = (AbstractVehicle) obj;
            return this.brand.equals(other.brand) && this.model.equals(other.model) && this.productionYear == other.productionYear;
        }
        return false;
    }
}
