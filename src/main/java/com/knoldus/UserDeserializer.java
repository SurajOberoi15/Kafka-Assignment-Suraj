package com.knoldus;

import java.util.Map;
import org.apache.kafka.common.serialization.Deserializer;

import com.fasterxml.jackson.databind.ObjectMapper;

public class UserDeserializer implements Deserializer<User> {


    public void close() {

    }


    public void configure(Map<String, ?> arg0, boolean arg1) {

    }

    public User deserialize(String arg0, byte[] arg1) {
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        try {
            user = mapper.readValue(arg1, User.class);
        } catch (Exception e) {

            e.printStackTrace();
        }
        return user;
    }

}
