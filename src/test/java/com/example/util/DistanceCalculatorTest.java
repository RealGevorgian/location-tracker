package com.example.util;

import com.example.locationtracker.util.DistanceCalculator;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DistanceCalculatorTest {
    @Test
    public void testHaversine() {
        double distance = DistanceCalculator.haversine(40.748817, -73.985428, 34.052235, -118.243683);
        assertEquals(3940, distance, 1); // NYC to LA ~3940 km
    }

    @Test
    public void testHaversineSamePoint() {
        double distance = DistanceCalculator.haversine(40.748817, -73.985428, 40.748817, -73.985428);
        assertEquals(0, distance, 0.001);
    }
}
