package kz.epam.entity.action;

import kz.epam.entity.Airport;
import kz.epam.entity.plane.FlightType;
import kz.epam.entity.plane.Plane;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


import kz.epam.entity.terminal.LoadingType;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class TerminalAction {

    private final Lock queueLock = new ReentrantLock();
    Condition condition = queueLock.newCondition();
    private static final Logger LOGGER = LogManager.getLogger(TerminalAction.class);

    public void loadPassengers(Plane plane) {
        queueLock.lock();
        try {
            LOGGER.info("Loading passengers to " + plane.getName()  + " has started");
            TimeUnit.MILLISECONDS.sleep(12 * (plane.getCurrentCapacity() / 10));
            LOGGER.info("Loading passengers to " + plane.getName()  + " has finished");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            //e.printStackTrace();
            LOGGER.error(e);
        } finally {
            queueLock.unlock();
        }
    }

    public void unloadPassengers (Plane plane) {
        queueLock.lock();
        try {
            queueLock.lock();
            LOGGER.info("Unloading passengers from " + plane.getName()  + " has started");
            TimeUnit.MILLISECONDS.sleep(7 * (plane.getCurrentCapacity() / 10));
            LOGGER.info("Unloading passengers from " + plane.getName()  + " has finished");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error(e);
        } finally {
            queueLock.unlock();
        }
    }

    public void waitPlaneLanding (Plane plane) {
        queueLock.lock();
        try {
            LOGGER.info("Plane " + plane.getName()  + " has started landing");
            TimeUnit.MILLISECONDS.sleep(7 * 10000/(plane.getFlightRange().getVelocity()));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            e.printStackTrace();
        } finally {
            queueLock.unlock();
        }
    }

    public void waitPlaneSending (Plane plane) {
        queueLock.lock();
        try {
            LOGGER.info("Plane " + plane.getName()  + " has started preparing for departure");
            TimeUnit.MILLISECONDS.sleep(7 * 10000/(plane.getFlightRange().getVelocity()));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error(e);
        } finally {
            queueLock.unlock();
        }
    }

    public void luggagePickUp (Plane plane, Airport airport) {
        if(airport.getActiveLuggagePickUpLines() < 4) {
            airport.setActiveLuggagePickUpLines(airport.getActiveLuggagePickUpLines()+1);
            try {
                LOGGER.info("Passengers from plane " + plane.getName() + " are waiting for their luggage");
                TimeUnit.MILLISECONDS.sleep(2* (plane.getCurrentCapacity()));
                LOGGER.info("Luggage pick up of passengers from plane " + plane.getName() + " has finished");
                airport.setActiveLuggagePickUpLines(airport.getActiveLuggagePickUpLines()-1);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                LOGGER.error(e);
            }
        }

        else {
            try {
                while(airport.getActiveLuggagePickUpLines() >= 4) {
                    try {
                        queueLock.lock();
                        LOGGER.info("...Currently all luggage pick lines are occupied. Plane " + plane.getName() + " is waiting for other passengers to pick up their luggage");
                        condition.await(100, TimeUnit.MILLISECONDS);
                    } finally {
                        queueLock.unlock();
                    }
                }
                luggagePickUp(plane, airport);
            } catch (InterruptedException e){
                LOGGER.error(e);
            }
        }
    }

    public void preparingThePlaneForDeparture (Plane plane) {
        queueLock.lock();
        try {
            LOGGER.info("Plane " + plane.getName()  + " is cleaning by airport cleaning service");
            TimeUnit.MILLISECONDS.sleep(30 * (plane.getCurrentCapacity() / 10));
            LOGGER.info("Plane is ready to accept passengers");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            LOGGER.error(e);
        } finally {
            queueLock.unlock();
        }
    }

    public void walkIn(Plane plane) {
        queueLock.lock();
        try {
            LOGGER.info("Passengers walking through " + plane.getTerminalLanded().getType());
            if(plane.getTerminalLanded().getType() == LoadingType.GROUND){
                TimeUnit.MILLISECONDS.sleep(200);
            }
            else {
                TimeUnit.MILLISECONDS.sleep(100);
            }
        } catch (InterruptedException e){
            LOGGER.error(e);
        } finally {
            queueLock.unlock();
        }
    }

    public void passportControlForInternationalFlights(Plane plane) {
        queueLock.lock();
        try{
            if(plane.getFlightType() == FlightType.INTERNATIONAL){
                LOGGER.info("Passengers from plane " + plane.getName() + " having passport control for border pass");
                TimeUnit.MILLISECONDS.sleep(100);
            }

        } catch (InterruptedException e) {
            LOGGER.error(e);
        } finally {
            queueLock.lock();
        }
    }
}
