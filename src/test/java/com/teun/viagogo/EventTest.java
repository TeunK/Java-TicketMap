package com.teun.viagogo;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

/**
 * Created by Teun on 10/29/2016.
 */
public class EventTest {
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
}
