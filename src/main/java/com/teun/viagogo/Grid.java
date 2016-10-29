package com.teun.viagogo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Teun on 10/29/2016.
 */
public class Grid {
    private final Map<Location, Event> locationEventMap;
    private final int minX;
    private final int minY;
    private final int maxX;
    private final int maxY;

    public Grid(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        locationEventMap = new HashMap<Location, Event>();
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


}
