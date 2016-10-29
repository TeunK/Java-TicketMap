package com.teun.viagogo;

/**
 * Created by Teun on 10/29/2016.
 */
public class Util {
    public static Location stringToLocation(String request) {
        //todo: assert sensible input
        if (isCoordinateString(request)) {
            int x = Integer.parseInt(request.substring(0, request.indexOf(',')));
            int y = Integer.parseInt(request.substring(request.indexOf(',')+1, request.length()));

            return new Location(x, y);
        } else {
            throw new IllegalArgumentException("Invalid coordinate received, must be of format: x,y");
        }
    }

    private static boolean isCoordinateString(String str) {
        if (str.contains(",")) {
            String cord1 = str.substring(0, str.indexOf(','));
            String cord2 = str.substring(0, str.indexOf(','));

            if (cord1.length()>0 && cord2.length()>0) {
                return true;
            }
        }
        return false;
    }
}
