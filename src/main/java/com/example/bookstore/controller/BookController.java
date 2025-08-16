package com.example.bookstore.controller;


import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookFilterRequest;
import com.example.bookstore.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/search")
    public List<BookDto> searchBooks(@RequestParam String query){
        return bookService.searchBooks(query);
    }


    @GetMapping("/book-stock")
    public List<BookDto> topRatedBooks(){
        return bookService.findBookInStock();
    }

    @GetMapping("/available")
    public List<BookDto> availableBooks(@RequestParam double minPrice, @RequestParam double maxPrice){
        return bookService.findAvailableBooksInPriceRange(minPrice, maxPrice);
    }

    @PostMapping("/advanced-search")
    public List<BookDto> advancedSearch(@RequestBody BookFilterRequest filterRequest){
        return bookService.advancedSearch(filterRequest);
    }

    @GetMapping("/author")
    public List<BookDto> booksByAuthor(@RequestParam String author){
        return bookService.findBooksByAuthorFuzzy(author);
    }
}
