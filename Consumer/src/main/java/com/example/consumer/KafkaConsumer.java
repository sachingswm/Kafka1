package com.example.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "topic4",groupId = "group_id")
    public void consume(String message)
    {
        System.out.println(message);
    }


    @KafkaListener(topics = "topic5",groupId = "insaan_group_id",containerFactory = "insaanConcurrentKafkaListenerContainerFactory")
    public void consume(Insaan insaan)
    {
        System.out.println(insaan);
    }

}
