package kz.epam;

import kz.epam.entity.Airport;
import kz.epam.entity.dataHandling.ReadFiles;
import kz.epam.entity.plane.Plane;
import kz.epam.entity.thread.PlaneActions;
import kz.epam.entity.terminal.Terminal;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {
        ReadFiles rf = new ReadFiles();

        List<Plane> planes = rf.readPlanes("data/planes.txt");
        List<Terminal> terminals = rf.readTerminals("data/terminals.txt", 5);
        List<Thread> threads = new ArrayList<Thread>();

        Airport airport = Airport.getInstance();
        airport.setTerminals(terminals);
        System.out.println(terminals);

        for (Plane p : planes) {
            PlaneActions acceptingPlane = new PlaneActions(p, airport);
            threads.add(new Thread(acceptingPlane));
        }

        for(Thread thread: threads) {
            thread.start();
        }
    }
}
