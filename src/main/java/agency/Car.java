package agency;

import util.TimeProvider;

public class Car extends AbstractVehicle{

    private int numberOfSeats;

    /**
     * Create a new car.
     * @param brand The brand of the car.
     * @param model The model of the car.
     * @param productionYear The production year of the car.
     * @param numberOfSeats The number of seats in the car.
     * @throws IllegalArgumentException if the production year is not between 1900 and the current year or if the number of seats is less than 1.
     */
    public Car(String brand, String model, int productionYear, int numberOfSeats) throws IllegalArgumentException {
        super(brand, model, productionYear);

        if(numberOfSeats < 1) {
            throw new IllegalArgumentException("Number of seats must be at least 1. " + numberOfSeats + " is invalid.");
        }

        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Test if the car is new.
     * @return true if the car is new (5 years old or less), false otherwise.
     */
    public boolean isNew(){
        return TimeProvider.currentYearValue() - this.getProductionYear() <= 5;
    }

    /**
     * Get the daily rental price of the car.
     * @return The daily rental price of the car depending on the number of seats and the age of the car.
     */
    @Override
    public double dailyRentalPrice() {
        if(this.isNew()) {
            return this.numberOfSeats * 40;
        } else {
            return this.numberOfSeats * 20;
        }
    }

    /**
     * Get a string representation of the car.
     * @return Car [brand] [model] [production year] ([number of seats] seat(s)) : [daily rental price]€
     */
    @Override
    public String toString() {
        return "Car " + this.getBrand() + " " +
                this.getModel() + " " +
                this.getProductionYear() + " (" +
                this.numberOfSeats +
                " seat" + (this.numberOfSeats > 1 ? "s" : "") +  ") : " +
                dailyRentalPrice() + "€";
    }
}
