package com.example.bookstore.service;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.dto.BookFilterRequest;
import com.example.bookstore.mapper.BookMapper;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    @Override
    public List<BookDto> searchBooks(String searchTerm) {
       return bookRepository.searchBooks(searchTerm).stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    @Override
    public List<BookDto> findBookInStock() {

       return bookRepository.findInStockBooks().stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    @Override
    public List<BookDto> findAvailableBooksInPriceRange(double minPrice, double maxPrice) {

        List<Book> books = bookRepository.findAvailableBooksInPriceRange(minPrice, maxPrice);
        return books.stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    @Override
    public List<BookDto> advancedSearch(BookFilterRequest filterRequest) {
        List<Book> books = bookRepository.advancedSearch(
                filterRequest.getSearchTerm(),
                filterRequest.isInStock(),
                filterRequest.getMinRating(),
                filterRequest.getMinPrice(),
                filterRequest.getMaxPrice()
        );
        return books.stream()
                .map(bookMapper::toBookDto)
                .toList();
    }

    @Override
    public List<BookDto> findBooksByAuthorFuzzy(String authorName) {
        List<Book> books = bookRepository.findBooksByAuthorFuzzy(authorName);
        return books.stream()
                .map(bookMapper::toBookDto).toList();
    }
}
