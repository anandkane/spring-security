package com.spearhead.springsec.bookws.book.model;

public class BookBuilder {
    private String bookId;
    private String isbn;
    private String title;
    private String publisher;
    private String datePublished;

    public BookBuilder setBookId(String bookId) {
        this.bookId = bookId;
        return this;
    }

    public BookBuilder setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookBuilder setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookBuilder setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public BookBuilder setDatePublished(String datePublished) {
        this.datePublished = datePublished;
        return this;
    }

    public Book createBook() {
        return new Book(bookId, isbn, title, publisher, datePublished);
    }
}