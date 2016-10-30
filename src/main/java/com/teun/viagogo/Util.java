package com.teun.viagogo;

/**
 * Created by Teun on 10/29/2016.
 */
public class Util {
    public static Location stringToLocation(String request) {
        if (isCoordinateString(request)) {
            try {
                int x = Integer.parseInt(request.substring(0, request.indexOf(',')));
                int y = Integer.parseInt(request.substring(request.indexOf(',')+1, request.length()));

                return new Location(x, y);
            } catch (NumberFormatException e){
                throw new IllegalArgumentException("invalid coordinate received, axis must represent integer values");
            }
        } else {
            throw new IllegalArgumentException("Invalid coordinate received, must be of format: x,y");
        }
    }

    private static boolean isCoordinateString(String str) {
        if (str.contains(",")) {
            String cord1 = str.substring(0, str.indexOf(','));
            String cord2 = str.substring(str.indexOf(',')+1, str.length());

            if (cord1.length()>0 && cord2.length()>0) {
                return true;
            }
        }
        return false;
    }
}
