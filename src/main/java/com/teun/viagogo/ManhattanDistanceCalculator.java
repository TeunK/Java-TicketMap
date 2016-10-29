package com.teun.viagogo;

/**
 * Created by Teun on 10/29/2016.
 */
public class ManhattanDistanceCalculator implements DistanceCalculator{
    public int getDistanceBetweenTwoLocations(Location a, Location b) {
        int xOffset = Math.abs(a.getX() - b.getX());
        int yOffset = Math.abs(a.getY() - b.getY());
        return (xOffset + yOffset);
    }
}
