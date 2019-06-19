package kz.epam.entity.plane;

public enum PlaneFlightRange {
    HIGH(1200), MEDIUM(800), LOW(500);
    private final int velocity;

    PlaneFlightRange(int velocity) {
        this.velocity = velocity;
    }

    public int getVelocity() {
        return velocity;
    }
}
