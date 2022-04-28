package com.amy.londonapi.utilities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GeolocationTests {

    /**
     * Constant for the latitude of London.
     */
    final private double LONDON_LATITUDE = 51.507222;

    /**
     * Constant for the longitude of London.
     */
    final private double LONDON_LONGITUDE = -0.1275;

    @Test
    public void isUserWithin50MilesOfLondon() {

        double userLatitude = 51.6553959;
        double userLongitude = 0.0572553;

        boolean result = Geolocation.isUserWithinXMilesOfCoordinates(
                userLatitude,
                userLongitude,
                50,
                LONDON_LATITUDE,
                LONDON_LONGITUDE);

        assertTrue(result);
    }

    @Test
    public void isUserOutside50MilesOfLondon() {

        double userLatitude = 34.003135;
        double userLongitude = -117.7228641;

        boolean result = Geolocation.isUserWithinXMilesOfCoordinates(
                userLatitude,
                userLongitude,
                50,
                LONDON_LATITUDE,
                LONDON_LONGITUDE);

        assertFalse(result);
    }

    @Test
    public void isUserExactly50MilesOfLondon() {

        double userLatitude = 52.09427;
        double userLongitude = 0.420771;

        boolean result = Geolocation.isUserWithinXMilesOfCoordinates(
                userLatitude,
                userLongitude,
                50,
                LONDON_LATITUDE,
                LONDON_LONGITUDE);

        assertTrue(result);
    }

}
