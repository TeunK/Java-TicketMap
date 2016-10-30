package com.teun.viagogo;

import com.teun.viagogo.distance_calculators.DistanceCalculator;
import com.teun.viagogo.distance_calculators.ManhattanDistanceCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Teun on 10/29/2016.
 */
public class ManhattanDistanceCalculatorTest {

    @Test
    public void manhattanPositiveLocations() {
        DistanceCalculator manhattanCalculator = new ManhattanDistanceCalculator();
        Location a = new Location(5, 2);
        Location b = new Location(2, 3);
        int distance = manhattanCalculator.getDistanceBetweenTwoLocations(a, b);
        assertEquals(4, distance);
    }

    @Test
    public void manhattanNegativeLocations() {
        DistanceCalculator manhattanCalculator = new ManhattanDistanceCalculator();
        Location a = new Location(-10, 10);
        Location b = new Location(10, -10);
        int distance = manhattanCalculator.getDistanceBetweenTwoLocations(a, b);
        assertEquals(40, distance);
    }


    @Test
    public void manhattanSwappedLocationsEqualDistance() {
        DistanceCalculator manhattanCalculator = new ManhattanDistanceCalculator();
        Location a = new Location(5, 2);
        Location b = new Location(2, 3);
        int distance1 = manhattanCalculator.getDistanceBetweenTwoLocations(a, b);
        int distance2 = manhattanCalculator.getDistanceBetweenTwoLocations(b, a);
        assertTrue(distance1 == distance2);
    }
}
