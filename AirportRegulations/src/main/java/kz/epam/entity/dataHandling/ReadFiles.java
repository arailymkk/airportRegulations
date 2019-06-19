package kz.epam.entity.dataHandling;

import kz.epam.entity.plane.FlightType;
import kz.epam.entity.plane.Plane;
import kz.epam.entity.plane.PlaneFlightRange;
import kz.epam.entity.terminal.LoadingType;
import kz.epam.entity.terminal.Terminal;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadFiles {
    DataValidation dataValidation = new DataValidation();

    public List<Plane> readPlanes(String fileName) {
        List<Plane> planes = new ArrayList<Plane>();
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(", ");
                Plane plane = createPlanes(attributes);
                planes.add(plane);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return planes;
    }

    private Plane createPlanes(String[] metadata) {
        dataValidation.validateFlightType(metadata[4]);
        dataValidation.validateFlightRange(metadata[3]);
        String name = metadata[0];
        int maxCapacity = Integer.parseInt(metadata[1]);
        int currentCapacity = Integer.parseInt(metadata[2]);
        dataValidation.validateCurentCapacity(maxCapacity, currentCapacity);
        PlaneFlightRange flightRange;
        if(metadata[3].equals("HIGH")) {
            flightRange = PlaneFlightRange.HIGH;
        }
        else if(metadata[3].equals("MEDIUM")) {
            flightRange = PlaneFlightRange.MEDIUM;
        }
        else {
            flightRange = PlaneFlightRange.LOW;
        }
        FlightType flightType;
        if(metadata[4].equals("INTERNATIONAL")) {
            flightType = FlightType.INTERNATIONAL;
        }
        else {
            flightType = FlightType.DOMESTIC;
        }
        return new Plane(name, maxCapacity, currentCapacity, flightRange, flightType);
    }

    public List<Terminal> readTerminals(String fileName, int size) {
        List<Terminal> terminals = new ArrayList<Terminal>();
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
            String line = br.readLine();
            while (line != null) {
                String[] attributes = line.split(", ");
                Terminal terminal = createTerminals(attributes);
                terminals.add(terminal);
                line = br.readLine();
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return terminals;
    }

    private Terminal createTerminals(String[] metadata) {
        char name = metadata[0].charAt(0);
        boolean isEmpty = Boolean.parseBoolean(metadata[1]);
        LoadingType type;
        if(metadata[2].equals("GROUND")) {
            type = LoadingType.GROUND;
        }
        else {
            type = LoadingType.BRIDGE;
        }

        return new Terminal(name, isEmpty, type);
    }
}
