package com.spearhead.springsec.bookws.book.controller;

import com.spearhead.springsec.bookws.book.model.Book;
import com.spearhead.springsec.bookws.book.model.BookBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/v1/books")
public class BookController {

    private Map<String, Book> booksMap = new ConcurrentHashMap<>();

    public BookController() {
        Book book = new BookBuilder().setBookId("sample").setIsbn("sample").setPublisher("sample")
                .setDatePublished("sample").setTitle("sample").createBook();
        booksMap.put(book.getBookId(), book);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<Collection<Book>> getBooks() {
        Collection<Book> books = booksMap.values();
        return books != null && books.size() > 0 ? new ResponseEntity<>(books, HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{book-id}", produces = "application/json")
    public ResponseEntity<Book> getBook(@PathVariable("book-id") String bookId) {
        Book book = booksMap.get(bookId);
        return book != null ? new ResponseEntity<>(book, HttpStatus.FOUND) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        book.setBookId(UUID.randomUUID().toString());
        booksMap.put(book.getBookId(), book);

        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
}
