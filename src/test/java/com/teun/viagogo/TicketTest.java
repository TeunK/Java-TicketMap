package com.teun.viagogo;

import com.teun.viagogo.tickets.RegularTicket;
import com.teun.viagogo.tickets.Ticket;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Teun on 10/29/2016.
 */
public class TicketTest {
    @Test
    public void shouldCreateNormalTicket(){
        Ticket ticket = new RegularTicket(19.20);
        assertEquals(19.20, ticket.getPrice(), 0);
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
