package com.example.bookstore.service;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookFilterRequest;

import java.util.List;

public interface BookService {
    List<BookDto> searchBooks(String searchTerm);

    List<BookDto> findBookInStock();

    List<BookDto> findAvailableBooksInPriceRange(double minPrice, double maxPrice);

    List<BookDto> advancedSearch(BookFilterRequest filterRequest);

    List<BookDto> findBooksByAuthorFuzzy(String authorName);
}
