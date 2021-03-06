package com.teun.viagogo;

import com.teun.viagogo.tickets.Ticket;

/**
 * Created by Teun on 10/29/2016.
 */
public class EventDistanceCheapestTicket implements Comparable<EventDistanceCheapestTicket>  {
    private Event event;
    private int distance;
    private Ticket cheapestTicket;

    public EventDistanceCheapestTicket(Event event, int distance, Ticket cheapestTicket) {
        this.event = event;
        this.distance = distance;
        this.cheapestTicket = cheapestTicket;
    }

    public Event getEvent() {
        return event;
    }

    public String printDetails() {
        String eventID = Integer.toString(getEvent().getId());
        String price = Double.toString(getCheapestTicket().getPrice());
        String distance = Integer.toString(getDistance());

        return "Event " +eventID + " - $" + price + ", Distance " + distance;
    }

    public int getDistance() {
        return distance;
    }

    public Ticket getCheapestTicket() {
        return cheapestTicket;
    }

    public void setCheapestTicket(Ticket cheapestTicket) {
        this.cheapestTicket = cheapestTicket;
    }

    public int compareTo(EventDistanceCheapestTicket o) {
        if (this.distance > o.getDistance()) {
            return 1;
        } else if (this.distance < o.getDistance()) {
            return -1;
        } else {
            return 0;
        }
    }
}
