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
        Grid grid = new Grid(minX, minY, maxX, maxY, new ManhattanDistanceCalculator());

        insertMockData(minX, minY, maxX, maxY, grid);

        Location coordinates = requestUserCoordinates();

        List<EventDistanceCheapestTicket> closestEvents = grid.getClosestEvents(coordinates, 5);

        printResults(closestEvents);
    }

    private static Location requestUserCoordinates() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please Input Coordinates: ");
        String request = scanner.nextLine();
        Location coordinates = Util.stringToLocation(request);
        System.out.println("Closest events to ("+
                Integer.toString(coordinates.getX())+","+
                Integer.toString(coordinates.getY())+"):");
        return coordinates;
    }

    private static void insertMockData(int minX, int minY, int maxX, int maxY, Grid grid) {
        Random random = new Random();
        MockDataGenerator mockDataGenerator = new MockDataGenerator();
        for (int col=minX; col<maxX; col++) {
            for (int row=minY; row<maxY; row++) {
                if (random.nextInt(5) == 0) {
                    int ticketAmount = random.nextInt(10);
                    List<Ticket> tickets = mockDataGenerator.generateTickets(ticketAmount, 200000);
                    Location location = new Location(col, row);
                    Event event = new Event(location, tickets);
                    grid.placeEvent(event);
                }
            }
        }
    }

    private static void printResults(List<EventDistanceCheapestTicket> closestEvents) {
        for (EventDistanceCheapestTicket eventDistanceCheapestTicket : closestEvents) {
            System.out.println(eventDistanceCheapestTicket.printDetails());
        }
    }
}
