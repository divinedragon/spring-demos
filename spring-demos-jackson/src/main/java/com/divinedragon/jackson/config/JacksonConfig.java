package com.divinedragon.jackson.config;

import static com.divinedragon.jackson.SpringDemosJacksonApplication.DATE_TIME_FORMAT_STRING;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_AS_NULL;
import static com.fasterxml.jackson.databind.PropertyNamingStrategy.SNAKE_CASE;
import static com.fasterxml.jackson.databind.SerializationFeature.WRAP_ROOT_VALUE;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

@Configuration
public class JacksonConfig {

    @Bean("dateTimeFormatter")
    public DateTimeFormatter dateTimeFormatter() {
        return DateTimeFormatter.ofPattern(DATE_TIME_FORMAT_STRING);
    }

    @Bean
    @Primary
    public ObjectMapper objectMapper(final DateTimeFormatter dateTimeFormatter) {
        final ObjectMapper mapper = new ObjectMapper();

        mapper.enable(READ_UNKNOWN_ENUM_VALUES_AS_NULL);

        mapper.disable(FAIL_ON_UNKNOWN_PROPERTIES);
        mapper.disable(WRAP_ROOT_VALUE);
        mapper.disable(WRITE_DATES_AS_TIMESTAMPS);

        mapper.setPropertyNamingStrategy(SNAKE_CASE);

        final JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(OffsetDateTime.class, new CustomOffsetDateTimeSerializer(dateTimeFormatter));
        javaTimeModule.addDeserializer(OffsetDateTime.class, new CustomOffsetDateTimeDeserializer(dateTimeFormatter));

        mapper.registerModule(new Jdk8Module());
        mapper.registerModule(javaTimeModule);
        mapper.registerModule(new ParameterNamesModule());

        return mapper;
    }

    @Bean(name = "jacksonConverter")
    public MappingJackson2HttpMessageConverter jacksonConverter(final ObjectMapper objectMapper) {

        final MappingJackson2HttpMessageConverter httpMessageConverter = new MappingJackson2HttpMessageConverter();

        httpMessageConverter.setObjectMapper(objectMapper);

        return httpMessageConverter;
    }
}

class CustomOffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {

    private final DateTimeFormatter dateTimeFormatter;

    public CustomOffsetDateTimeSerializer(final DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public void serialize(final OffsetDateTime value, final JsonGenerator gen, final SerializerProvider serializers)
            throws IOException {
        gen.writeString(dateTimeFormatter.format(value));
    }

}

@Component
class CustomOffsetDateTimeDeserializer extends JsonDeserializer<OffsetDateTime> {

    private final DateTimeFormatter dateTimeFormatter;

    public CustomOffsetDateTimeDeserializer(final DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }

    @Override
    public OffsetDateTime deserialize(final JsonParser p, final DeserializationContext ctxt)
            throws IOException, JsonProcessingException {
        return OffsetDateTime.parse(p.getValueAsString(), dateTimeFormatter);
    }
}
