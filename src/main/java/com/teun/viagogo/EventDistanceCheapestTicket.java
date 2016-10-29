package com.teun.viagogo;

/**
 * Created by Teun on 10/29/2016.
 */
public class EventDistanceCheapestTicket {
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

    public int getDistance() {
        return distance;
    }

    public Ticket getCheapestTicket() {
        return cheapestTicket;
    }

    public void setCheapestTicket(Ticket cheapestTicket) {
        this.cheapestTicket = cheapestTicket;
    }
}
