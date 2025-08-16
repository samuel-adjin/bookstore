package com.example.bookstore.mapper;

import com.example.bookstore.dto.BookDto;
import com.example.bookstore.model.Book;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

    public BookDto toBookDto(Book book) {
        if (book == null ) return null;
        return new BookDto(
                book.getId(),
                book.getTitle(),
                book.getDescription(),
                book.getAuthor(),
                book.getPrice(),
                book.isInStock(),
                book.getRating(),
                book.getPublishedDate()
        );

    }
}
