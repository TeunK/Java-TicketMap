package com.teun.viagogo;

import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Created by Teun on 10/29/2016.
 */
public class AppTest
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
}
