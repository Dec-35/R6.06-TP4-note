package agency.criteria;

import agency.Vehicle;

import java.util.function.Predicate;

public class MaxPriceCriterion implements Predicate<Vehicle> {
    private double maxPrice;

    public MaxPriceCriterion(double maxPrice) {
        this.maxPrice = maxPrice;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle.dailyRentalPrice() <= this.maxPrice;
    }
}
