package com.teun.viagogo.distance_calculators;

import com.teun.viagogo.Location;

/**
 * Created by Teun on 10/29/2016.
 */
public interface DistanceCalculator {
    int getDistanceBetweenTwoLocations(Location a, Location b);
}