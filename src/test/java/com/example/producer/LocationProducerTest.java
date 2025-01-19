package com.example.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.junit.Test;
import org.mockito.Mockito;

public class LocationProducerTest {
    @Test
    public void testProducerSendsMessage() {

        KafkaProducer<String, String> producer = Mockito.mock(KafkaProducer.class);
        String topic = "test-topic";
        String message = "40.748817,-73.985428";

        producer.send(new ProducerRecord<>(topic, message));

        Mockito.verify(producer).send(Mockito.any(ProducerRecord.class));
    }
}
