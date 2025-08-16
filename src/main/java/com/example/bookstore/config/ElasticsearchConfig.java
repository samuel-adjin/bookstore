package com.example.bookstore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.core.convert.ElasticsearchCustomConversions;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;

@Configuration
public class ElasticsearchConfig extends ElasticsearchConfigurationSupport {

    @Bean
    @Override
    public ElasticsearchCustomConversions elasticsearchCustomConversions() {
        return new ElasticsearchCustomConversions(
                Arrays.asList(
                        new LongToLocalDateConverter(),
                        new LocalDateToLongConverter(),
                        new LongToInstantConverter(),
                        new InstantToLongConverter()
                )
        );
    }

    // Converter from Long (epoch millis) to LocalDate
    static class LongToLocalDateConverter implements Converter<Long, LocalDate> {
        @Override
        public LocalDate convert(Long source) {
            if (source == null) {
                return null;
            }
            return Instant.ofEpochMilli(source)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();
        }
    }

    // Converter from LocalDate to Long (epoch millis)
    static class LocalDateToLongConverter implements Converter<LocalDate, Long> {
        @Override
        public Long convert(LocalDate source) {
            if (source == null) {
                return null;
            }
            return source.atStartOfDay(ZoneId.systemDefault())
                    .toInstant()
                    .toEpochMilli();
        }
    }

    // Converter from Long (epoch millis) to Instant
    static class LongToInstantConverter implements Converter<Long, Instant> {
        @Override
        public Instant convert(Long source) {
            if (source == null) {
                return null;
            }
            return Instant.ofEpochMilli(source);
        }
    }

    // Converter from Instant to Long (epoch millis)
    static class InstantToLongConverter implements Converter<Instant, Long> {
        @Override
        public Long convert(Instant source) {
            if (source == null) {
                return null;
            }
            return source.toEpochMilli();
        }
    }
}