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
}
