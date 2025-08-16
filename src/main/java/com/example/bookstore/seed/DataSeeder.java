package com.example.bookstore.seed;

import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import com.example.bookstore.model.Book;
import com.example.bookstore.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.IndexedObjectInformation;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.IndexQuery;
import org.springframework.data.elasticsearch.core.query.IndexQueryBuilder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class DataSeeder implements CommandLineRunner {



    private final ElasticsearchOperations elasticsearchOperations;
    private final BookRepository bookRepository;
    private final Random random = new Random();

    private final String[] authors = {
            "Alice Johnson", "Bob Smith", "Charlie Brown", "Diana Ross",
            "Evan Davis", "Fiona Green", "George King", "Hannah Lee"
    };

    private final String[] titles = {
            "Mastering Java", "Python Basics", "JavaScript Essentials",
            "Data Science 101", "Cloud Computing Guide", "Web Development Tips",
            "Algorithms Explained", "Mobile Dev Handbook"
    };

    private final String[] descriptions = {
            "A comprehensive guide.", "Learn the fundamentals.", "Step-by-step tutorials.",
            "Best practices and patterns.", "Expert insights.", "Real-world examples."
    };

    @Override
        public void run(String... args) throws Exception {
                if(bookRepository.count() == 0) {
                    List<Book> books = new ArrayList<>();
                    for (int i = 0; i < 100; i++) {
                        Book book = generateRandomBook();
                        System.out.println("book: "+ book);
                        books.add(book);
                    }
                    try {
                        List<IndexQuery>  queries = books.stream().map(book-> new IndexQueryBuilder()
//                                .withId(book.getId())
                                .withObject(book)
                                .build()).toList();

                        List<IndexedObjectInformation> result = elasticsearchOperations.bulkIndex(queries, IndexCoordinates.of("books"));
                        log.info("Successfully indexed {} books", result.size());
                    }catch (Exception e) {
                       log.error(e.getMessage());
                    }
                }else{
                    log.info("Found {} books", bookRepository.count());
                }
        }

    private Book generateRandomBook() {
        String title = titles[random.nextInt(titles.length)];
        String author = authors[random.nextInt(authors.length)];
        LocalDate publishedDate = LocalDate.now().minusDays(random.nextInt(3650));
        String isbn = UUID.randomUUID().toString();
        boolean inStock = random.nextBoolean();
        double price = 10 + (100 - 10) * random.nextDouble();
        double rating = 1 + (5 - 1) * random.nextDouble();
        String description = descriptions[random.nextInt(descriptions.length)];
        System.out.println(title);
        return Book.builder()
                .title(title)
                .author(author)
                .publishedDate(publishedDate)
                .isbn(isbn)
                .inStock(inStock)
                .price(Math.round(price * 100.0) / 100.0)
                .rating(Math.round(rating * 10.0) / 10.0)
                .description(description)
                .createdAt(java.time.Instant.now())
                .build();
    }
}
