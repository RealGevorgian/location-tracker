package com.example.locationtracker.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Random;

public class LocationProducer {
    private static final Logger logger = LoggerFactory.getLogger(LocationProducer.class);

    public static void main(String[] args) {
        Properties props = new Properties();

        // Kafka producer configurations
        props.put("bootstrap.servers", "localhost:9092");
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        KafkaProducer<String, String> producer = new KafkaProducer<>(props);
        Random random = new Random();

        try {
            for (int i = 0; i < 100; i++) {
                double latitude = -90 + (90 - (-90)) * random.nextDouble();
                double longitude = -180 + (180 - (-180)) * random.nextDouble();

                String value = latitude + "," + longitude;
                producer.send(new ProducerRecord<>("location-topic", value));
                logger.info("Sent: {}", value);

                Thread.sleep(1000); // Pause for 1 second
            }
        } catch (Exception e) {
            logger.error("Error while producing message", e);
        } finally {
            producer.close();
        }
    }
}
