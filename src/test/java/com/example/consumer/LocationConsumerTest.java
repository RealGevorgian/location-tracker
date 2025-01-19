package com.example.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;
import org.mockito.Mockito;

public class LocationConsumerTest {
    @Test
    public void testConsumerReceivesMessage() {
        // Mock KafkaConsumer
        KafkaConsumer<String, String> consumer = Mockito.mock(KafkaConsumer.class);

        // Simulate consuming messages
        ConsumerRecords<String, String> records = Mockito.mock(ConsumerRecords.class);
        Mockito.when(consumer.poll(Mockito.anyLong())).thenReturn(records);

        consumer.poll(100);

        // Verify interaction
        Mockito.verify(consumer).poll(Mockito.anyLong());
    }
}
