package com.teun.viagogo;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Teun on 10/29/2016.
 */
public class LocationTest {
    @Test
    public void shouldCreateCorrectLocation(){
        Location positiveLocation = new Location(2, 5);
        assertEquals(2, positiveLocation.getX());
        assertEquals(5, positiveLocation.getY());
    }
}
