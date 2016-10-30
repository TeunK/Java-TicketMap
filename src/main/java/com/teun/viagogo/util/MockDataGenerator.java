package com.teun.viagogo.util;

import com.teun.viagogo.tickets.RegularTicket;
import com.teun.viagogo.tickets.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Teun on 10/29/2016.
 */
public class MockDataGenerator {
    public List<Ticket> generateTickets(int amount, int priceRange) {
        List<Ticket> tickets = new ArrayList<Ticket>();
        for (int i=0; i<amount; i++) {
            tickets.add(new RegularTicket(generateRandomPrice(priceRange)));
        }

        return tickets;
    }

    private double generateRandomPrice(int maxCents) {
        Random random = new Random();
        double cents = (1 + random.nextInt(maxCents));
        return (cents / 100.0);
    }
}
