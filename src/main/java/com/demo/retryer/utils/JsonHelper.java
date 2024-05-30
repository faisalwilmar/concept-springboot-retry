package com.demo.retryer.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class JsonHelper {

    private final ObjectMapper objectMapper;

    public <T> T toClass(String json, Class<T> clazz) throws JsonProcessingException {
        return objectMapper.readValue(json, clazz);
    }

    public <T> String toString(T obj) throws JsonProcessingException {
        return objectMapper.writeValueAsString(obj);
    }

    public JsonNode toJsonNode(String jsonResponse) throws JsonProcessingException {
        return objectMapper.readTree(jsonResponse);
    }

}
