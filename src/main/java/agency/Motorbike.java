package agency;

public class Motorbike extends AbstractVehicle{

    private int cylinderCapacity;

    /**
     * Create a new vehicle.
     *
     * @param brand          The brand of the vehicle.
     * @param model          The model of the vehicle.
     * @param productionYear The production year of the vehicle.
     * @throws IllegalArgumentException if the production year is not between 1900 and the current year.
     */
    public Motorbike(String brand, String model, int productionYear, int cylinderCapacity) throws IllegalArgumentException {
        super(brand, model, productionYear);

        if(cylinderCapacity < 50) {
            throw new IllegalArgumentException("Cylinder capacity must be at least 50. " + cylinderCapacity + " is invalid.");
        }

        this.cylinderCapacity = cylinderCapacity;
    }

    /**
     * Get the daily rental price of the motorbike.
     * @return The daily rental price of the motorbike depending on the cylinder capacity.
     */
    @Override
    public double dailyRentalPrice() {
        return this.cylinderCapacity * 0.25;
    }

    /**
     * Get a string representation of the motorbike.
     * @return Motorbike [brand] [model] [production year] ([cylinder capacity] cm3) : [daily rental price]€
     */
    @Override
    public String toString() {
        return "Motorbike " + this.getBrand() + " " +
                this.getModel() + " " +
                this.getProductionYear() + " (" +
                this.cylinderCapacity + "cm3) " +
                dailyRentalPrice() + "€";
    }
}
