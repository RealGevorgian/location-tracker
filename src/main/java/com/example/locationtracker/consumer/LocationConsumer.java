package com.example.locationtracker.consumer;

import com.example.locationtracker.model.Location;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class LocationConsumer {
    private static final Logger logger = LoggerFactory.getLogger(LocationConsumer.class);
    private static double totalDistance = 0;

    public static void main(String[] args) {
        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("group.id", "location-tracker-group");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");

        KafkaConsumer<String, String> consumer = new KafkaConsumer<>(props);
        consumer.subscribe(List.of("location-topic"));

        List<Location> locations = new ArrayList<>();

        try {
            while (true) {
                ConsumerRecords<String, String> records = consumer.poll(100);
                for (ConsumerRecord<String, String> record : records) {
                    logger.info("Received: {}", record.value());

                    Location location = parseLocation(record.value());
                    if (location == null) continue;

                    if (!locations.isEmpty()) {
                        Location lastLocation = locations.get(locations.size() - 1);
                        totalDistance += haversine(lastLocation, location);
                    }

                    locations.add(location);
                    logger.info("Total Distance: {} km", totalDistance);
                }
            }
        } catch (Exception e) {
            logger.error("Error while consuming messages", e);
        } finally {
            consumer.close();
        }
    }

    private static Location parseLocation(String value) {
        try {
            String[] parts = value.replace("Location{", "").replace("}", "").split(", ");
            double latitude = Double.parseDouble(parts[0].split("=")[1]);
            double longitude = Double.parseDouble(parts[1].split("=")[1]);
            return new Location(latitude, longitude);
        } catch (Exception e) {
            logger.error("Failed to parse location: {}", value, e);
            return null;
        }
    }

    private static double haversine(Location loc1, Location loc2) {
        final int R = 6371; // Radius of Earth in kilometers
        double dLat = Math.toRadians(loc2.getLatitude() - loc1.getLatitude());
        double dLon = Math.toRadians(loc2.getLongitude() - loc1.getLongitude());

        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(Math.toRadians(loc1.getLatitude())) * Math.cos(Math.toRadians(loc2.getLatitude())) *
                        Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}
