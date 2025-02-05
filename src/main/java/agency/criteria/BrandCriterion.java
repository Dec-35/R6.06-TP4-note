package agency.criteria;

import agency.Vehicle;

import java.util.function.Predicate;

public class BrandCriterion implements Predicate<Vehicle> {
    private String brand;

    public BrandCriterion(String brand) {
        this.brand = brand;
    }

    @Override
    public boolean test(Vehicle vehicle) {
        return vehicle.getBrand().equals(this.brand);
    }
}
