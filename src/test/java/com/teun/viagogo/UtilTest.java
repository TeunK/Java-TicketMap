package com.teun.viagogo;

import com.teun.viagogo.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Teun on 10/29/2016.
 */
public class UtilTest {

    @Test
    public void validPositiveCoordinateInput() {
        String input = "1,6";
        assertEquals(1, Util.stringToLocation(input).getX());
        assertEquals(6, Util.stringToLocation(input).getY());
    }

    @Test
    public void validNegativeCoordinateInput() {
        String input = "-3,-10";
        assertEquals(-3, Util.stringToLocation(input).getX());
        assertEquals(-10, Util.stringToLocation(input).getY());
    }

    @Test(expected=IllegalArgumentException.class)
    public void invalidCoordinateInputMissingComma() {
        String input = "6236";
        Util.stringToLocation(input);
    }

    @Test(expected=IllegalArgumentException.class)
    public void invalidCoordinateInputMissingX() {
        String input = ",1";
        Util.stringToLocation(input);
    }

    @Test(expected=IllegalArgumentException.class)
    public void invalidCoordinateInputMissingY() {
        String input = "0,";
        Util.stringToLocation(input);
    }
}
