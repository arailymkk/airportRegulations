package kz.epam.entity;

import kz.epam.entity.plane.Plane;
import kz.epam.entity.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Airport {
    private List<Plane> planesAtAirport;
    private List<Terminal> terminals;
    private int activeLuggagePickUpLines;

    public Airport() {
        planesAtAirport = new ArrayList<Plane>();
        activeLuggagePickUpLines = 0;
    }

    private static Airport ourInstance = new Airport();

    public static Airport getInstance() {
        return ourInstance;
    }


    public List<Plane> getPlanesAtAirport() {
        return planesAtAirport;
    }

    public void setPlanesAtAirport(List<Plane> planesAtAirport) {
        this.planesAtAirport = planesAtAirport;
    }

    public List<Terminal> getTerminals() {
        return terminals;
    }

    public void setTerminals(List<Terminal> terminals) {
        this.terminals = terminals;
    }

    public int getActiveLuggagePickUpLines() {
        return activeLuggagePickUpLines;
    }

    public void setActiveLuggagePickUpLines(int activeLuggagePickUpLines) {
        this.activeLuggagePickUpLines = activeLuggagePickUpLines;
    }
}
