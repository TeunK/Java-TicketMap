package com.teun.viagogo;

import java.util.List;
import java.util.Random;
import java.util.Scanner;

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

        // take input from user
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Input Coordinates: ");
        String request = scanner.nextLine();
        Location coordinates = Util.stringToLocation(request);
        System.out.println("Closest events to ("+
                Integer.toString(coordinates.getX())+","+
                Integer.toString(coordinates.getY())+"):");

        // find the closest 5 neighbors to this location
        List<EventDistanceCheapestTicket> closestEvents = map.getClosestEvents(coordinates, 5);

        // print results
        for (EventDistanceCheapestTicket eventDistanceCheapestTicket : closestEvents) {
            String eventID = Integer.toString(eventDistanceCheapestTicket.getEvent().getId());
            String price = Double.toString(eventDistanceCheapestTicket.getCheapestTicket().getPrice());
            String distance = Integer.toString(eventDistanceCheapestTicket.getDistance());
            System.out.println("Event " +eventID + " - $" + price + ", Distance " + distance);
        }
    }
}
