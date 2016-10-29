package com.teun.viagogo;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.ArrayList;
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
        Grid grid = new Grid(-10, -10, 10, 10);
        Location validLocation = new Location(5,5);
        Event validEvent = new Event(validLocation, new ArrayList<Ticket>());
        grid.placeEvent(validEvent);
        assertEquals(validEvent, grid.getEventAtLocation(validLocation));
    }

    @Test(expected=IllegalArgumentException.class)
    public void addsOutOfBoundsEventToGrid() {
        Grid grid = new Grid(-10, -10, 10, 10);
        Location invalidLocation = new Location(-20,5);
        Event invalidEvent = new Event(invalidLocation, new ArrayList<Ticket>());
        grid.placeEvent(invalidEvent);
    }

    @Test(expected=IllegalArgumentException.class)
    public void multipleEventsAtSamePointOnGrid() {
        Grid grid = new Grid(-10, -10, 10, 10);
        Location validLocation = new Location(1,4);
        Event validEvent1 = new Event(validLocation, new ArrayList<Ticket>());
        Event validEvent2 = new Event(validLocation, new ArrayList<Ticket>());
        grid.placeEvent(validEvent1);
        grid.placeEvent(validEvent2);
    }

    @Test
    public void removingEventFromGrid() {
        Grid grid = new Grid(-10, -10, 10, 10);
        Location validLocation = new Location(1,4);
        Event validEvent1 = new Event(validLocation, new ArrayList<Ticket>());
        Event validEvent2 = new Event(validLocation, new ArrayList<Ticket>());
        grid.placeEvent(validEvent1);
        grid.removeEvent(validEvent1);
        grid.placeEvent(validEvent2);
        assertEquals(validEvent2.getId(), grid.getEventAtLocation(validEvent2.getLocation()).getId());
    }

}
