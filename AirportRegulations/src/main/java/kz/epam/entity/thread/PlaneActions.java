package kz.epam.entity.thread;

import kz.epam.entity.Airport;
import kz.epam.entity.action.AirportActions;
import kz.epam.entity.action.TerminalAction;
import kz.epam.entity.plane.Plane;


public class PlaneActions implements Runnable {

    private TerminalAction terminalAction = new TerminalAction();
    private AirportActions airportAction = new AirportActions();
    private Plane plane;
    private Airport airport;

    public PlaneActions(Plane plane, Airport airport) {
        this.plane = plane;
        this.airport = airport;
    }

    public void run() {
            arrivalActions();
            departureActions();
    }


    private void arrivalActions() {
        terminalAction.waitPlaneLanding(plane);
        airportAction.addPlane(plane, airport);
        terminalAction.unloadPassengers(plane);
        terminalAction.walkIn(plane);
        terminalAction.passportControlForInternationalFlights(plane);
        terminalAction.luggagePickUp(plane, airport);
    }

    private void departureActions() {
        terminalAction.preparingThePlaneForDeparture(plane);
        terminalAction.passportControlForInternationalFlights(plane);
        terminalAction.walkIn(plane);
        terminalAction.loadPassengers(plane);
        terminalAction.waitPlaneSending(plane);
        airportAction.deletePlane(plane, airport);
    }
}
