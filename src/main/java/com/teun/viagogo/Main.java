package com.teun.viagogo;

import java.util.List;
import java.util.Random;

/**
 * Created by Teun on 10/29/2016.
 */
public class Main
{
    public static void main( String[] args )
    {
        int minX = -10;
        int minY = -10;
        int maxX = 10;
        int maxY = 10;
        Grid map = new Grid(minX, minY, maxX, maxY, new ManhattanDistanceCalculator());

        // insert mock data
        Random random = new Random();
        MockDataGenerator mockDataGenerator = new MockDataGenerator();
        for (int col=minX; col<maxX; col++) {
            for (int row=minY; row<maxY; row++) {
                if (random.nextInt(5) == 0) {
                    int ticketAmount = random.nextInt(10);
                    List<Ticket> tickets = mockDataGenerator.generateTickets(ticketAmount, 200000);
                    Location location = new Location(col, row);
                    Event event = new Event(location, tickets);
                    map.placeEvent(event);
                }
            }
        }


    }
}
