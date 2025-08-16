package com.example.bookstore.dto;

import lombok.Data;

@Data
public class BookFilterRequest {
    private String searchTerm;
    private boolean inStock;
    private double minRating;
    private double minPrice;
    private double maxPrice;
}
