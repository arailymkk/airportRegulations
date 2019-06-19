package kz.epam.entity.dataHandling;

public class DataValidation {

    public void validateFlightType(String name) {
        if (!checkFlightType(name)) {
            throw new IllegalArgumentException("Invalid flight type");
        }
    }

    private boolean checkFlightType(String flightType) {
        if(flightType.equals("DOMESTIC") || flightType.equals("INTERNATIONAL")) {
            return true;
        }
        else {
            return false;
        }
    }

    public void validateFlightRange(String name) {
        if (!checkFlightRange(name)) {
            throw new IllegalArgumentException("Invalid flight range");
        }
    }

    private boolean checkFlightRange(String flightRange) {
        if(flightRange.equals("HIGH") || flightRange.equals("MEDIUM") || flightRange.equals("LOW")) {
            return true;
        }
        else {
            return false;
        }
    }

    public void validateCurentCapacity(int max, int current) {
        if (!checkCurrentCapacity(max, current)) {
            throw new IllegalArgumentException("Capacity of plane cannot exceed maximum capacity");
        }
    }

    private boolean checkCurrentCapacity(int max, int current) {
        return (current <= max);
    }
}