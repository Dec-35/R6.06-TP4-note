package agency;

import util.TimeProvider;

public class Car implements Vehicle{

    private String brand;
    private String model;
    private int productionYear;
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
        if(productionYear < 1900 || productionYear > TimeProvider.currentYearValue()) {
            throw new IllegalArgumentException("Production year must be between 1900 and the current year. " + productionYear + " is invalid.");
        }

        if(numberOfSeats < 1) {
            throw new IllegalArgumentException("Number of seats must be at least 1. " + numberOfSeats + " is invalid.");
        }

        this.brand = brand;
        this.model = model;
        this.productionYear = productionYear;
        this.numberOfSeats = numberOfSeats;
    }

    /**
     * Test if two cars are equal.
     * @param obj The object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Object obj) {
        if(obj instanceof Car) {
            Car other = (Car) obj;
            return this.brand.equals(other.brand) && this.model.equals(other.model) && this.productionYear == other.productionYear;
        }
        return false;
    }

    /**
     * Test if the car is new.
     * @return true if the car is new (5 years old or less), false otherwise.
     */
    public boolean isNew(){
        return this.productionYear - TimeProvider.currentYearValue() >= 5;
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
        return "Car " + this.brand + " " +
                this.model + " " +
                this.productionYear + " (" +
                this.numberOfSeats +
                " seat" + (this.numberOfSeats > 1 ? "s" : "") +  ") : " +
                dailyRentalPrice() + "€";
    }
}
