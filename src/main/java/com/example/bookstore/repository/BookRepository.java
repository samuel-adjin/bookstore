package com.example.bookstore.repository;

import com.example.bookstore.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BookRepository extends ElasticsearchRepository<Book,String> {

    @Query("""
            {
              "multi_match": {
                "query": "?0",
                "fields": ["title^2", "description^1"],
                "type": "best_fields"
              }
            }
            """)
    List<Book> searchBooks(String searchTerm);

    @Query("""
            {
              "multi_match": {
                     "query": "?0",
                     "fields": ["title^2", "description^1"],
                     "type": "best_fields"
                   }
            }
            """)
    Page<Book> searchBooksWithPagination(String searchTerm, Pageable pageable);


    @Query("""
            {
              "bool": {
                "must": {
                  "term": {
                    "inStock": true
                  }
                },
                "filter": [
                  {
                    "range": {
                      "price": {
                        "gte": ?0,
                        "lte": ?1
                      }
                    }
                  }
                ]
              }
            }
            """)
    List<Book> findAvailableBooksInPriceRange(double minPrice, double maxPrice);

    @Query("""
            {
              "bool": {
                "must": {
                  "term": {
                    "inStock": true
                  }
                },
                "filter": [
                  {
                    "range": {
                      "price": {
                        "gte": ?0,
                        "lte": ?1
                      }
                    }
                  }
                ]
              }
            }
            """)
    Page<Book> findAvailableBooksInPriceRange(double minPrice, double maxPrice, Pageable pageable);


    @Query("""
            {
              "term": {
                "inStock": true
              }
            }
            """)
    List<Book> findInStockBooks();

    @Query("""
            {
              "multi_match": {
                "query": "?0",
                "fields": ["title^3", "description^1"],
                "fuzziness": "AUTO",
                "type": "best_fields"
              }
            }
            """)
    List<Book> smartSearch(String searchTerm);

    @Query("""
            {
              "multi_match": {
                "query": "?0",
                "fields": ["title^3", "description^1"],
                "fuzziness": "AUTO",
                "type": "best_fields"
              }
            }
            """)
    Page<Book> smartSearchWithPagination(String searchTerm, Pageable pageable);

    @Query("""
            {
              "bool": {
                "must": [
                  {
                    "multi_match": {
                      "query": "?0",
                      "fields": ["title^2", "description"],
                      "fuzziness": "AUTO"
                    }
                  }
                ],
                "filter": [
                  {
                    "term": {
                      "inStock": ?1
                    }
                  },
                  {
                    "range": {
                      "rating": {
                        "gte": ?2
                      }
                    }
                  },
                  {
                    "range": {
                      "price": {
                        "gte": ?3,
                        "lte": ?4
                      }
                    }
                  }
                ]
              }
            }
            """)
    List<Book> advancedSearch(String searchTerm, boolean inStock, double minRating,
                              double minPrice, double maxPrice);

    @Query("""
            {
              "match": {
                "author": {
                  "query": "?0",
                  "fuzziness": "AUTO"
                }
              }
            }
            """)
    List<Book> findBooksByAuthorFuzzy(String authorName);


    @Query("""
            {
              "range": {
                "publishedDate": {
                  "gte": "?0",
                  "lte": "?1"
                }
              }
            }
            """)
    List<Book> findBooksPublishedBetween(String startDate, String endDate);

    @Query("""
            {
              "bool": {
                "must": [
                  {
                    "multi_match": {
                      "query": "?0",
                      "fields": ["title", "description"]
                    }
                  },
                  {
                    "term": {
                      "inStock": true
                    }
                  },
                  {
                    "range": {
                      "rating": {
                        "gte": ?1
                      }
                    }
                  }
                ]
              }
            }
            """)
    List<Book> findQualityBooksWithSearch(String searchTerm, double minRating);
    
    @Query("""
            {
              "wildcard": {
                "title.keyword": {
                  "value": "*?0*"
                }
              }
            }
            """)
    List<Book> findBooksByTitleWildcard(String titlePattern);
}
