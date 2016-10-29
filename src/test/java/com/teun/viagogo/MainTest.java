package com.teun.viagogo;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Teun on 10/29/2016.
 */
public class MainTest
{
    @Test
    public void shouldReturnHello() {
        assertEquals("hello", "hello");
    }

    @Test
    public void shouldReturnPositiveLocation(){
        Location positiveLocation = new Location(2, 5);
        assertEquals(2, positiveLocation.getX());
        assertEquals(5, positiveLocation.getY());
    }

    @Test
    public void shouldReturnNegativeLocation(){
        Location negativeLocation = new Location(-10, -9);
        assertEquals(-10, negativeLocation.getX());
        assertEquals(-9, negativeLocation.getY());
    }

    @Test
    public void shouldCreateTicket(){
        Ticket ticket = new RegularTicket(19.20);
        assertEquals(19.20, ticket.getPrice(), 0);
    }

    @Test
    public void shouldUpdateTicketPrice(){
        Ticket ticket = new RegularTicket(10.00);
        ticket.setPrice(15.00);
        assertEquals(15.00, ticket.getPrice(), 0);
    }

    @Test
    public void assignsCorrectEventId() {
        Event event1 = new Event(new Location(0,1), new ArrayList<Ticket>());
        int initialID = event1.getId();

        Event event2 = new Event(new Location(0,2), new ArrayList<Ticket>());
        assertEquals(initialID+1, event2.getId());
        Event event3 = new Event(new Location(0,3), new ArrayList<Ticket>());
        assertEquals(initialID+2, event3.getId());
    }

    @Test
    public void addsTickets() {
        Event event = new Event(new Location(0,0), new ArrayList<Ticket>());
        event.addTicket(new RegularTicket(10.00));
        event.addTicket(new RegularTicket(12.50));
        assertEquals(2, event.getTickets().size());
    }

    @Test(expected=IllegalArgumentException.class)
    public void zeroTicketPrice() {
        Ticket ticket = new RegularTicket(0);
    }

    @Test(expected=IllegalArgumentException.class)
    public void negativeTicketPrice() {
        Ticket ticket = new RegularTicket(-5);
    }

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
    public void manhattanBaseCase() {
        DistanceCalculator manhattanCalculator = new ManhattanDistanceCalculator();
        Location a = new Location(5, 2);
        Location b = new Location(2, 3);
        int distance = manhattanCalculator.getDistanceBetweenTwoLocations(a, b);
        assertEquals(4, distance);
    }

    @Test
    public void manhattanNegativeLocations() {
        DistanceCalculator manhattanCalculator = new ManhattanDistanceCalculator();
        Location a = new Location(-10, 10);
        Location b = new Location(10, -10);
        int distance = manhattanCalculator.getDistanceBetweenTwoLocations(a, b);
        assertEquals(40, distance);
    }

    @Test
    public void sortEventDistances() {
        DistanceCalculator calculator = new ManhattanDistanceCalculator();
        Location target = new Location(0,0);

        Event event1 = new Event(new Location(1, 0), new ArrayList<Ticket>());
        int distance1 = calculator.getDistanceBetweenTwoLocations(target, event1.getLocation());

        Event event2 = new Event(new Location(2, 0), new ArrayList<Ticket>());
        int distance2 = calculator.getDistanceBetweenTwoLocations(target, event2.getLocation());

        Event event3 = new Event(new Location(0, 3), new ArrayList<Ticket>());
        int distance3 = calculator.getDistanceBetweenTwoLocations(target, event3.getLocation());

        EventDistanceCheapestTicket eventDistanceCheapestTicket1 = new EventDistanceCheapestTicket(event1, distance1, new RegularTicket(5.00));
        EventDistanceCheapestTicket eventDistanceCheapestTicket2 = new EventDistanceCheapestTicket(event2, distance2, new RegularTicket(5.00));
        EventDistanceCheapestTicket eventDistanceCheapestTicket3 = new EventDistanceCheapestTicket(event3, distance3, new RegularTicket(5.00));

        List<EventDistanceCheapestTicket> eventDistanceCheapestTickets = new ArrayList<EventDistanceCheapestTicket>();
        //note: inserted in shuffled order in terms of their distance from target
        eventDistanceCheapestTickets.add(eventDistanceCheapestTicket3);
        eventDistanceCheapestTickets.add(eventDistanceCheapestTicket1);
        eventDistanceCheapestTickets.add(eventDistanceCheapestTicket2);

        Collections.sort(eventDistanceCheapestTickets);

        assertEquals(event1.getId(), eventDistanceCheapestTickets.get(0).getEvent().getId());
        assertEquals(event2.getId(), eventDistanceCheapestTickets.get(1).getEvent().getId());
        assertEquals(event3.getId(), eventDistanceCheapestTickets.get(2).getEvent().getId());
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
    public void validPositiveCoordinateInput() {
        String input = "1,6";
        assertEquals(1, Util.stringToLocation(input).getX());
        assertEquals(6, Util.stringToLocation(input).getY());
    }

    @Test
    public void validNegativeCoordinateInput() {
        String input = "-3,-10";
        assertEquals(-3, Util.stringToLocation(input).getX());
        assertEquals(-10, Util.stringToLocation(input).getY());
    }

    @Test(expected=IllegalArgumentException.class)
    public void invalidCoordinateInputMissingComma() {
        String input = "6236";
        Util.stringToLocation(input);
    }

    @Test(expected=IllegalArgumentException.class)
    public void invalidCoordinateInputMissingX() {
        String input = ",1";
        Util.stringToLocation(input);
    }

    @Test(expected=IllegalArgumentException.class)
    public void invalidCoordinateInputMissingY() {
        String input = "0,";
        Util.stringToLocation(input);
    }

}
