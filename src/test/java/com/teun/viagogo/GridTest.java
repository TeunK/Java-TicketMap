package com.teun.viagogo;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Teun on 10/29/2016.
 */
public class GridTest
{
    @Test
    public void addsLegalEventToGrid() {
        Grid grid = new Grid(-10, -10, 10, 10, new ManhattanDistanceCalculator());
        Location validLocation = new Location(5,5);
        Event validEvent = new Event(validLocation, new ArrayList<Ticket>());
        grid.placeEvent(validEvent);
        assertEquals(validEvent, grid.getEventAtLocation(validLocation));
    }

    @Test(expected=IllegalArgumentException.class)
    public void addsOutOfBoundsEventToGrid() {
        Grid grid = new Grid(-10, -10, 10, 10, new ManhattanDistanceCalculator());
        Location invalidLocation = new Location(-20,5);
        Event invalidEvent = new Event(invalidLocation, new ArrayList<Ticket>());
        grid.placeEvent(invalidEvent);
    }

    @Test(expected=IllegalArgumentException.class)
    public void multipleEventsAtSamePointOnGrid() {
        Grid grid = new Grid(-10, -10, 10, 10, new ManhattanDistanceCalculator());
        Location validLocation = new Location(1,4);
        Event validEvent1 = new Event(validLocation, new ArrayList<Ticket>());
        Event validEvent2 = new Event(validLocation, new ArrayList<Ticket>());
        grid.placeEvent(validEvent1);
        grid.placeEvent(validEvent2);
    }

    @Test
    public void removingEventFromGrid() {
        Grid grid = new Grid(-10, -10, 10, 10, new ManhattanDistanceCalculator());
        Location validLocation = new Location(1,4);
        Event validEvent1 = new Event(validLocation, new ArrayList<Ticket>());
        Event validEvent2 = new Event(validLocation, new ArrayList<Ticket>());
        grid.placeEvent(validEvent1);
        grid.removeEvent(validEvent1);
        grid.placeEvent(validEvent2);
        assertEquals(validEvent2.getId(), grid.getEventAtLocation(validEvent2.getLocation()).getId());
    }

    @Test
    public void find1ClosestEvent() {
        Grid grid = new Grid(-10, -10, 10, 10, new ManhattanDistanceCalculator());
        List<Ticket> ticketList = new ArrayList<Ticket>();
        ticketList.add(new RegularTicket(26.00));
        ticketList.add(new RegularTicket(22.00));
        ticketList.add(new RegularTicket(30.00));
        ticketList.add(new RegularTicket(31.00));

        Event event1 = new Event(new Location(1,0), ticketList);
        Event event2 = new Event(new Location(2,3), ticketList);
        Event event3 = new Event(new Location(1,6), ticketList);
        Event event4 = new Event(new Location(-4,5), ticketList);
        Event event5 = new Event(new Location(-10,-8), ticketList);

        grid.placeEvent(event1);
        grid.placeEvent(event2);
        grid.placeEvent(event3);
        grid.placeEvent(event4);
        grid.placeEvent(event5);

        List<EventDistanceCheapestTicket> closestEvent = grid.getClosestEvents(new Location(0,0), 1);
        assertEquals(event1.getId(), closestEvent.get(0).getEvent().getId());

        closestEvent = grid.getClosestEvents(new Location(-10,-10), 1);
        assertEquals(event5.getId(), closestEvent.get(0).getEvent().getId());
    }

    @Test
    public void find5ClosestEvent() {
        Grid grid = new Grid(-10, -10, 10, 10, new ManhattanDistanceCalculator());
        List<Ticket> ticketList = new ArrayList<Ticket>();
        ticketList.add(new RegularTicket(26.00));
        ticketList.add(new RegularTicket(22.00));
        ticketList.add(new RegularTicket(30.00));
        ticketList.add(new RegularTicket(31.00));

        Event event1 = new Event(new Location(-5,0), ticketList);
        Event event5 = new Event(new Location(4,0), ticketList);
        Event event4 = new Event(new Location(-6,1), ticketList);
        Event event8 = new Event(new Location(6,1), ticketList);
        Event event2 = new Event(new Location(-5,1), ticketList);
        Event event6 = new Event(new Location(5,0), ticketList);
        Event event3 = new Event(new Location(-6,0), ticketList);
        Event event7 = new Event(new Location(6,0), ticketList);

        grid.placeEvent(event2);
        grid.placeEvent(event8);
        grid.placeEvent(event3);
        grid.placeEvent(event1);
        grid.placeEvent(event4);
        grid.placeEvent(event6);
        grid.placeEvent(event5);
        grid.placeEvent(event7);

        List<EventDistanceCheapestTicket> closestEvents = grid.getClosestEvents(new Location(-6,0), 5);
        assertEquals(event3.getId(), closestEvents.get(0).getEvent().getId());
        assertEquals(event5.getId(), closestEvents.get(4).getEvent().getId());

        assertEquals(22.00, closestEvents.get(4).getCheapestTicket().getPrice(), 0);
        assertEquals(22.00, closestEvents.get(3).getCheapestTicket().getPrice(), 0);
        assertEquals(22.00, closestEvents.get(2).getCheapestTicket().getPrice(), 0);
        assertEquals(22.00, closestEvents.get(1).getCheapestTicket().getPrice(), 0);
        assertEquals(22.00, closestEvents.get(0).getCheapestTicket().getPrice(), 0);

        assertEquals(0, closestEvents.get(0).getDistance());
        assertEquals(2, closestEvents.get(3).getDistance());
    }
}
