package kz.epam.entity.action;

import kz.epam.entity.Airport;
import kz.epam.entity.plane.Plane;
import kz.epam.entity.terminal.Terminal;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AirportActions {
    private final Lock lock = new ReentrantLock();
    Condition condition = lock.newCondition();
    private static final Logger LOGGER = LogManager.getLogger(AirportActions.class);

    public void addPlane(Plane plane, Airport airport) {
        if(airport.getPlanesAtAirport().size() < 5) {
            try {
                lock.lock();
                if(!airport.getPlanesAtAirport().contains(plane)) {
                    airport.getPlanesAtAirport().add(plane);
                }
                TimeUnit.MILLISECONDS.sleep(plane.getCurrentCapacity() * 2 / 10);
                for (Terminal terminal : airport.getTerminals()) {
                    if (terminal.isEmpty()) {
                        terminal.setEmpty(false);
                        plane.setTerminalLanded(terminal);
                        LOGGER.info("Plane " + plane.getName() + " has arrived to terminal " + terminal.getName());
                        break;
                    }
                }
                if(plane.getTerminalLanded()==null) {
                    TimeUnit.MILLISECONDS.sleep(plane.getCurrentCapacity() * 2 / 10);
                    addPlane(plane, airport);
                }
            } catch (InterruptedException e){
                LOGGER.error(e);
            }
                finally {
                lock.unlock();
            }
        }
        else{
            while(airport.getPlanesAtAirport().size() >= 5) {
                try {
                    lock.lock();
                    LOGGER.info("...Currently there is no free terminal. Plane " + plane.getName() + " is waiting for plane departure");
                    condition.await(1000, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e){
                    LOGGER.error(e);
                } finally {
                    lock.unlock();
                }
            }
            addPlane(plane, airport);
        }
    }

    public void deletePlane(Plane plane, Airport airport) {
        lock.lock();
        try {
            plane.getTerminalLanded().setEmpty(true);
            airport.getPlanesAtAirport().remove(plane);
            LOGGER.info("Plane " + plane.getName() + " has left from terminal");
            LOGGER.debug("Now number of planes currently at airport is " + airport.getPlanesAtAirport().size() + " after " + plane.getName() + " has left the terminal " + plane.getTerminalLanded().getName());
            LOGGER.debug("List of planes currently at airport: " + airport.getPlanesAtAirport());
            plane.setTerminalLanded(null);
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
