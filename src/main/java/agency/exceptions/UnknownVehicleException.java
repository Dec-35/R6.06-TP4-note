package agency.exceptions;

import agency.Vehicle;

public class UnknownVehicleException extends RuntimeException {

    /**
     * Create a new UnknownVehicleException when an unknown vehicle is being removed from an agency.
     * @param vehicle The vehicle that is unknown.
     */
    public UnknownVehicleException(Vehicle vehicle) {
        super("Unknown vehicle: " + vehicle.toString());
    }
}
