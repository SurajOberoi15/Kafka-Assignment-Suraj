package com.knoldus;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class Producer {
    public static void main(String[] args){
        Properties properties = new Properties();
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.knoldus.UserSerializer");
        
        KafkaProducer kafkaProducer = new KafkaProducer<String, User>(properties);
        Random rand = new Random();
        List<User> users = new ArrayList<>();
        for(int i = 1;i<20;i++){
            users.add(new User(i, "Suraj", rand.nextInt(10)+12, "MCA"));
        }

        try{
            for(User user : users){
                System.out.println(user);
                kafkaProducer.send(new ProducerRecord<String, User>("user",user.toString(),user ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            kafkaProducer.close();
        }
    }
}