package com.example.application1;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Properties;

@RestController
public class KafkaController {

//    public static final String KAFKA_TOPIC1 = "topic1";
//    public static final String KAFKA_TOPIC2 = "topic2";
    public static final String KAFKA_TOPIC3 = "topic3";
//      public static final String KAFKA_TOPIC4 = "topic4";


//    @Autowired
//    private KafkaTemplate<String, String> kafkaTemplate1;
//
    @Autowired
    private KafkaTemplate<String, Player> kafkaTemplate2;


//    @Autowired
//    private KafkaTemplate<String, Insaan> kafkaTemplate4;


//    @PostMapping("/publishString")
//    public String publishStringToKafka(@RequestParam String data) {
//        try {
//            kafkaTemplate1.send(KAFKA_TOPIC1, data);
//        } catch (Exception e) {
//            return "Error during publishing data to Kafka";
//        }
//        return "The data " + data + " was successfully published to Kafka";
//    }
//
//    @PostMapping("/publishObject")
//    public String publishObjectToKafka() {
//        Player player = new Player("Sachin", "India", 23);
//        try {
//            kafkaTemplate2.send(KAFKA_TOPIC2, player);
//        } catch (Exception e) {
//            return "Error during publishing data to Kafka";
//        }
//        return "The data " + player + " was successfully published to Kafka";
//    }
//
//    @KafkaListener(id = "string-listener", topics = {KAFKA_TOPIC1})
//    public void listenToTopicData(String string) {
//        System.out.println(string);
//    }
//
//    @KafkaListener(id = "object-listener", topics = {KAFKA_TOPIC2})
//    public void listenToTopicData(Player player) {
//        System.out.println(player);
//    }
//
    @PostMapping("/publishPlayer")
    public String publishObjectToKafka(@RequestBody Player player) {
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers", "localhost:9092");
        properties.setProperty("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.setProperty("value.serializer", "org.springframework.kafka.support.serializer.JsonSerializer");
        KafkaProducer<String, Player> kafkaProducer = new KafkaProducer<String, Player>(properties);
        ProducerRecord<String, Player> playerProducerRecord = new ProducerRecord<String, Player>(KAFKA_TOPIC3, player.getName(), player);
        try {
            kafkaProducer.send(playerProducerRecord);
        } catch (Exception e) {
            return "Error during publishing data to Kafka";
        }
        return "The data " + player + " was successfully published to Kafka";
    }

    @KafkaListener(id = "player-listener", topics = {KAFKA_TOPIC3})
    public void listenToTopicPlayer(Player player) {
        System.out.println(player);
    }

//    @PostMapping("/publishInsaan")
//    public String publishObjectToKafka(@RequestBody Insaan insaan) {
//        try {
//            kafkaTemplate4.send(KAFKA_TOPIC4, insaan.getName(), insaan);
//        } catch (Exception e) {
//            return "Error during publishing data to Kafka";
//        }
//        return "The data " + insaan + " was successfully published to Kafka";
//    }

}
