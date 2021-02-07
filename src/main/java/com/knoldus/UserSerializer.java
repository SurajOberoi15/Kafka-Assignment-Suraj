package com.knoldus;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserSerializer implements Serializer<User> {

    public void close() {

    }


    public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    public byte[] serialize(String arg0, User arg1) {
        byte[] returnValue = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            returnValue = objectMapper.writeValueAsString(arg1).getBytes();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnValue;
    }



}