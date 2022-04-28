package com.amy.londonapi.utilities;

import org.apache.lucene.util.SloppyMath;

public class Geolocation {

    private static final double METRES_IN_MILE = 1609.344;

    /***
     * Check if a given user location is within the given number of miles
     * of a starting point location.  (Uses the Haversine formula.)
     * @param userLatitude
     * @param userLongitude
     * @param miles
     * @param startPointLatitude
     * @param startPointLongitude
     * @return
     */
    public static boolean isUserWithinXMilesOfCoordinates(
            double userLatitude,
            double userLongitude,
            double miles,
            double startPointLatitude,
            double startPointLongitude) {

        // Miles needs to be converted to metres - the Haversine
        // formula returns distance in metres.
        double milesInMetres = milesToMetres(miles);

        double distanceFromStartPoint = SloppyMath.haversinMeters(
                startPointLatitude,
                startPointLongitude,
                userLatitude,
                userLongitude);

        return distanceFromStartPoint <= milesInMetres;
    }

    private static double metresToMiles(double metres) {
        return metres / METRES_IN_MILE;
    }

    private static double milesToMetres(double miles) {
        return miles * METRES_IN_MILE;
    }
}
