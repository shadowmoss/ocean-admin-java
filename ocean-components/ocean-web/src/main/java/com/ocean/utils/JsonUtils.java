package com.ocean.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author ltx
 */
public class JsonUtils {
    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String writeToString(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
