package com.teun.viagogo;

import java.util.Map;

/**
 * Created by Teun on 10/29/2016.
 */
public class Grid {
    Map<Location, Event> locationEventMap;
    private final int minX;
    private final int minY;
    private final int maxX;
    private final int maxY;

    public Grid(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

}
