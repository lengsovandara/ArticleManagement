package com.tomadev.converter;

import com.gluonhq.connect.converter.InputStreamInputConverter;
import com.gluonhq.connect.converter.JsonConverter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SingleInputConverter<T> extends InputStreamInputConverter<T> {
    private static final Logger LOGGER = Logger.getLogger(SingleInputConverter.class.getName());

    private final JsonConverter<T> jsonConverter;

    public SingleInputConverter(Class<T> targetClass) {
        this.jsonConverter = new JsonConverter<>(targetClass);
    }

    @Override
    public T read() {
        try (JsonReader reader = Json.createReader(getInputStream())) {
            JsonObject jsonObject = reader.readObject();
            return jsonConverter.readFromJson(jsonObject);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, "Error SingleInputConverter:  " + e);
        }
        return null;
    }

}
