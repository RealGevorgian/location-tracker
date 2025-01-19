package com.example.locationtracker.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LocationController {

    private double totalDistance = 0;

    @GetMapping("/distance")
    public String getTotalDistance() {
        return "Total Distance: " + totalDistance + " km";
    }

    public void updateTotalDistance(double distance) {
        this.totalDistance = distance;
    }
}
