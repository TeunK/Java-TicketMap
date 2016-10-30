package com.teun.viagogo;

import com.teun.viagogo.distance_calculators.DistanceCalculator;
import com.teun.viagogo.tickets.Ticket;

import java.util.*;

/**
 * Created by Teun on 10/29/2016.
 */
public class Grid {
    private final Map<Location, Event> locationEventMap;
    private final int minX;
    private final int minY;
    private final int maxX;
    private final int maxY;
    private final DistanceCalculator distanceCalculator;

    public Grid(int minX, int minY, int maxX, int maxY, DistanceCalculator distanceCalculator) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        locationEventMap = new HashMap<Location, Event>();
        this.distanceCalculator = distanceCalculator;
    }

    public void placeEvent(Event event) {
        if (isLocationWithinGrid(event.getLocation())) {
            if (!locationEventMap.containsKey(event.getLocation())) {
                locationEventMap.put(event.getLocation(), event);
            } else {
                throw new IllegalArgumentException("An event already exists at this location");
            }
        } else {
            throw new IllegalArgumentException("Event location out of bounds");
        }
    }

    public void removeEvent(Event event) {
        if (locationEventMap.containsKey(event.getLocation())) {
            locationEventMap.remove(event.getLocation());
        } else {
            throw new IllegalArgumentException("No events were found at the specified location");
        }
    }

    private boolean isLocationWithinGrid(Location location) {
        return (location.getX() >= minX && location.getY() >= minY && location.getX() <= maxX && location.getY() <= maxY);
    }

    public Event getEventAtLocation(Location location) {
        return locationEventMap.get(location);
    }

    public List<EventDistanceCheapestTicket> getClosestEvents(Location location, int limit) {
        List<EventDistanceCheapestTicket> eventDistanceCheapestTickets = combineEventDetails(locationEventMap, location);

        return filterCheapestNearbyEventTickets(eventDistanceCheapestTickets, limit);
    }

    private List<EventDistanceCheapestTicket> combineEventDetails(Map<Location, Event> locationEventMap, Location location) {
        List<EventDistanceCheapestTicket> eventDistanceCheapestTickets = new ArrayList<EventDistanceCheapestTicket>();
        for (Map.Entry<Location, Event> entry : locationEventMap.entrySet()) {
            eventDistanceCheapestTickets.add(new EventDistanceCheapestTicket(entry.getValue(),
                    distanceCalculator.getDistanceBetweenTwoLocations(entry.getKey(), location), null));
        }
        Collections.sort(eventDistanceCheapestTickets);

        return eventDistanceCheapestTickets;
    }

    private List<EventDistanceCheapestTicket> filterCheapestNearbyEventTickets(List<EventDistanceCheapestTicket> eventDistanceCheapestTickets, int limit) {
        List<EventDistanceCheapestTicket> cheapestNearbyEventTickets = new ArrayList<EventDistanceCheapestTicket>();
        for (EventDistanceCheapestTicket eventDistanceCheapestTicket : eventDistanceCheapestTickets) {
            Event event = eventDistanceCheapestTicket.getEvent();
            List<Ticket> tickets = event.getTickets();
            Collections.sort(tickets);

            if (tickets.size() > 0) {
                eventDistanceCheapestTicket.setCheapestTicket(tickets.get(0));
                cheapestNearbyEventTickets.add(eventDistanceCheapestTicket);
            }

            if (cheapestNearbyEventTickets.size() == limit) {
                break;
            }
        }
        return cheapestNearbyEventTickets;
    }
}
