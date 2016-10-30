package com.teun.viagogo;

import com.teun.viagogo.tickets.Ticket;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Teun on 10/29/2016.
 */
public class Event {
    private static AtomicInteger ID_COUNTER = new AtomicInteger(0);
    private final int id;
    private final List<Ticket> tickets;
    private Location location;

    public Event(Location location, List<Ticket> tickets) {
        this.id = ID_COUNTER.getAndAdd(1);
        this.tickets = tickets;
        setLocation(location);
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getId() {
        return id;
    }
}
