package com.teun.viagogo;

import com.teun.viagogo.distance_calculators.DistanceCalculator;
import com.teun.viagogo.distance_calculators.ManhattanDistanceCalculator;
import com.teun.viagogo.tickets.RegularTicket;
import com.teun.viagogo.tickets.Ticket;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Teun on 10/29/2016.
 */
public class EventDistanceCheapestTicketTest {
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

}
