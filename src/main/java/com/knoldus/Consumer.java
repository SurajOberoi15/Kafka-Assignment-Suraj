package com.knoldus;

import com.google.gson.Gson;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Consumer {
    
    public static void main(String[] args) {
       ConsumerListener c = new ConsumerListener();
        Thread thread = new Thread(c);
        thread.start();
    }
      public static void consumer() throws IOException {
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.knoldus.UserDeserializer");
        properties.put("group.id", "Kafka-Assignment");

        FileWriter file = new FileWriter("ReadingFromTheProducer.txt");
        Gson content = new Gson();
        KafkaConsumer<String, User> consumer = new KafkaConsumer(properties);
        List topics = new ArrayList();
        topics.add("user");
        consumer.subscribe(topics);

        try{
            while (true){
                ConsumerRecords<String, User> records = consumer.poll(1000);
                for (ConsumerRecord<String, User> record: records) {
                    System.out.println(record.value());
                    String message = content.toJson(record.value()); // converting to json format
                    file.write(message + "\n"); // writing message to file
                    file.flush();
                }
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            consumer.close();
        }
    }
}

class ConsumerListener implements Runnable {


    @Override
    public void run() {
        try {
            Consumer.consumer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
