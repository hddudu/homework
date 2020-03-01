package com.hongdu.gupao.prototype;

import org.codehaus.jackson.*;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;

import java.io.IOException;

;

/**
 * @ClassName JsonUtils
 * @Description 参考 ： https://my.oschina.net/fengshuzi/blog/418976
 * @Author dudu
 * @Date 2020/3/1 15:38
 * @Version 1.0
 */
public class JsonUtils {
    public static final String MESSAGE = "message";

    @SuppressWarnings("unused")
    private static JsonGenerator jsonGenerator;

    private static ObjectMapper objectMapper;

    @SuppressWarnings("deprecation")
    private static void init() {
        objectMapper = new ObjectMapper();
        objectMapper.getSerializationConfig().setSerializationInclusion(Inclusion.ALWAYS);
        objectMapper.getDeserializationConfig().set(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ObjectMapper getObjectMapper() {
        if (objectMapper == null) {
            init();
        }
        return objectMapper;
    }

    public static String toJson(Object obj) throws JsonGenerationException, JsonMappingException, IOException {
        return getObjectMapper().writeValueAsString(obj);
    }

    public static <T> T formJson(JsonNode json, Class<T> clazz) throws JsonParseException, JsonMappingException, IOException {
        return getObjectMapper().readValue(json, clazz);
    }

}
