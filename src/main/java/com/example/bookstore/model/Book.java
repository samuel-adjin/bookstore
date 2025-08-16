package com.example.bookstore.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.data.elasticsearch.annotations.Setting;

import java.time.Instant;
import java.time.LocalDate;

@Document(indexName = "books")
@Setting(settingPath = "elasticsearch/settings/booking-settings.json")
@Mapping(mappingPath = "elasticsearch/mappings/book-mapping.json")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Book {

    @Id
    private String id;
    private String title;
    private String author;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publishedDate;
    private String isbn;
    private boolean inStock;
    private double price;
    private double rating;
    private String description;
    private Instant createdAt;

}
