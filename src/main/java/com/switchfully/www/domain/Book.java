package com.switchfully.www.domain;

import java.nio.file.LinkOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Book {
    private final String id;
    private Isbn isbn;
    private String title;
    private String summary;
    private Author author;
    private LocalDateTime dateCreated;
    private LocalDateTime dateDeleted;
    private LocalDateTime dateUpdated;

    public Book(Isbn isbn, String title, String summary, Author author) {
        this.id = UUID.randomUUID().toString();
        this.dateCreated = LocalDateTime.now();
        this.dateDeleted = null;
        this.dateUpdated = null;
        setIsbn(isbn);
        setTitle(title);
        this.summary = summary;
        setAuthor(author);
    }

    public String getId() {
        return id;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public Author getAuthor() {
        return author;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateDeleted() {
        return dateDeleted;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateDeleted(LocalDateTime dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public void setIsbn(Isbn isbn) throws IllegalArgumentException {
        if(isbn==null) {
            throw new IllegalArgumentException("ISBN of the book can't be empty");
        }
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        if(title==null) {
            throw new IllegalArgumentException("Please provide a title for the book");
        }
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setAuthor(Author author) {
        if(author==null) {
            throw new IllegalArgumentException("Please provide an author for the book");
        }
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", author=" + author +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id) && Objects.equals(isbn, book.isbn) && Objects.equals(title, book.title) && Objects.equals(summary, book.summary) && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }
}

