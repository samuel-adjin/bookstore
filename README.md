## 📚 Bookstore Elasticsearch Project

This project demonstrates how to integrate Spring Boot with Elasticsearch to build a searchable Bookstore API.
It showcases full-text search, fuzzy queries, range queries, and advanced filtering using Spring Data Elasticsearch.

## 🚀 Features

Search Books
Perform full-text search across book titles and descriptions with relevance scoring.

Stock-based Queries
Find books that are currently in stock.

Price Range Filtering
Search for available books within a specified price range.

Advanced Search
Combine multiple filters:

Search term

Stock availability

Minimum rating

Price range

Fuzzy Author Search
Search books by author name with fuzzy matching for typos and variations.

Wildcard & Date Queries

Find books by title pattern (wildcard search).

Find books published between given dates.

Data Seeder
Automatically seeds 100 random books into Elasticsearch for testing.

## 📂 Project Structure

```text
com.example.bookstore
│
├── controller
│   └── BookController.java      # REST API endpoints
│
├── dto
│   ├── BookDto.java             # Book data transfer object
│   └── BookFilterRequest.java   # Request payload for advanced search
│
├── mapper
│   └── BookMapper.java          # Converts Book -> BookDto
│
├── model
│   ├── Book.java                # Elasticsearch document model
│   └── Tag.java                 # Enum for book tags (Java, Python, etc.)
│
├── repository
│   └── BookRepository.java      # Elasticsearch queries
│
├── seed
│   └── DataSeeder.java          # Populates Elasticsearch with sample books
│
├── service
│   ├── BookService.java         # Service interface
│   └── BookServiceImpl.java     # Business logic implementation
```
## ⚡ REST API Endpoints
🔎 Search Books
GET /api/books/search?query=java

📦 Books In Stock
GET /api/books/book-stock

💲 Books in Price Range
GET /api/books/available?minPrice=20&maxPrice=50

🛠 Advanced Search
POST /api/books/advanced-search
Content-Type: application/json

{
  "searchTerm": "cloud",
  "inStock": true,
  "minRating": 3.5,
  "minPrice": 20,
  "maxPrice": 100
}

✍️ Search by Author (Fuzzy)
GET /api/books/author?author=smth

🛠 Tech Stack

Spring Boot – REST API framework

Spring Data Elasticsearch – Elasticsearch integration

Elasticsearch – Full-text search engine

Lombok – Boilerplate code reduction

Docker (optional) – Run Elasticsearch locally
