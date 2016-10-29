package com.teun.viagogo;

import java.util.List;

/**
 * Created by Teun on 10/29/2016.
 */
public class Event {
    private final int id;
    private final List<Ticket> tickets;
    private Location location;

    public Event(Location location, List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
