package com.pack.utils;

import java.io.IOException;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module.Feature;
import com.fasterxml.jackson.datatype.jaxrs.Jaxrs2TypesModule;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr353.JSR353Module;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.pack.exceptions.JsonParserException;


public class JsonUtils {

    private JsonUtils() {
    }

    private static ObjectMapper MAPPER;

    /**
     * Recupera um {@code ObjectMapper} com as configurações definidas para as
     * aplicações. As configurações são:
     * 
     * <ul>
     * <li>FORCE_LAZY_LOADING=false</li>
     * <li>WRITE_DATES_AS_TIMESTAMPS=false</li>
     * <li>INDENT_OUTPUT=true</li>
     * <li>FAIL_ON_EMPTY_BEANS=false</li>
     * <li>FAIL_ON_UNKNOWN_PROPERTIES=false</li>
     * </ul>
     * 
     * <br/>
     * Além disso, estão registrados os módulo do Hibernate, JDK8 para
     * tratamento de datas e tipo {@link Optional}.
     * 
     * @return {@link ObjectMapper}
     */
    public static ObjectMapper getMapperInstance() {
        if (MAPPER == null) {
            MAPPER = new ObjectMapper();
            // Desabilita execução de lazy loading na serialização
            Hibernate5Module hibernate5Module = new Hibernate5Module();
            hibernate5Module.configure(Feature.FORCE_LAZY_LOADING, false);
            MAPPER.registerModule(hibernate5Module);
            MAPPER.registerModule(new Jaxrs2TypesModule());

            // Conversões dos tipos do JDK8 (Optional e LocalTime/Date)
            MAPPER.registerModule(new ParameterNamesModule());
            MAPPER.registerModule(new JavaTimeModule());
            MAPPER.registerModule(new Jdk8Module());

            // Para mapeamento nativo do JSON do PostgreSQL para o
            // JsonObject/JsonArray do javax.json
            MAPPER.registerModule(new JSR353Module());

            MAPPER.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            MAPPER.configure(SerializationFeature.INDENT_OUTPUT, true);
            MAPPER.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            MAPPER.setSerializationInclusion(Include.NON_NULL);
        }
        return MAPPER;
    }

    /**
     * Realiza o parse da string no tipo informado como parâmetro
     * 
     * @param json
     *            String json
     * @param typeReference
     *            tipo para parse
     * @return objeto java
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public static <T> T parseToObject(String json, TypeReference typeReference) {
        try {
            return (T) getMapperInstance().readValue(json, typeReference);
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

    /**
     * Realiza o parse da string no tipo informado como parâmetro
     * 
     * @param json
     *            String json
     * @param typeReference
     *            tipo para parse
     * @return objeto java
     */
    @SuppressWarnings("unchecked")
    public static <T> T parseToObject(String json, Class<?> clazz) {
        try {
            return (T) getMapperInstance().readValue(json, clazz);
        } catch (IOException e) {
            throw new JsonParserException(e);
        }
    }

}