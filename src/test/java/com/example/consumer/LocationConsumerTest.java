package com.example.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.junit.Test;
import org.mockito.Mockito;

public class LocationConsumerTest {
    @Test
    public void testConsumerReceivesMessage() {

        KafkaConsumer<String, String> consumer = Mockito.mock(KafkaConsumer.class);

        ConsumerRecords<String, String> records = Mockito.mock(ConsumerRecords.class);
        Mockito.when(consumer.poll(Mockito.anyLong())).thenReturn(records);

        consumer.poll(100);

        Mockito.verify(consumer).poll(Mockito.anyLong());
    }
}
