package kz.epam.entity.plane;

import kz.epam.entity.terminal.Terminal;

public class Plane {
    private String name;
    private int maxCapacity;
    private int currentCapacity;
    private PlaneFlightRange flightRange;
    private FlightType flightType;
    private Terminal terminalLanded;

    public Plane(String name, int maxCapacity, int currentCapacity, PlaneFlightRange flightRange, FlightType flightType) {
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.currentCapacity = currentCapacity;
        this.flightRange = flightRange;
        this.flightType = flightType;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public PlaneFlightRange getFlightRange() {
        return flightRange;
    }

    public FlightType getFlightType() {
        return flightType;
    }

    public Terminal getTerminalLanded() {
        return terminalLanded;
    }

    public void setTerminalLanded(Terminal terminalLanded) {
        this.terminalLanded = terminalLanded;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Plane{");
        sb.append("name='").append(name).append('\'');
        sb.append(", maxCapacity=").append(maxCapacity);
        sb.append(", currentCapacity=").append(currentCapacity);
        sb.append(", flightRange=").append(flightRange);
        sb.append(", flightType=").append(flightType);
        sb.append(", terminalLanded=").append(terminalLanded);
        sb.append('}');
        return sb.toString();
    }
}
