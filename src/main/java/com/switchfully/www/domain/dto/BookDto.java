package com.switchfully.www.domain.dto;

import com.switchfully.www.domain.Author;
import com.switchfully.www.domain.Isbn;

public class BookDto {
    private String id;
    private Isbn isbn;
    private String title;
    private String summary;
    private Author author;

    public BookDto() {
    }

    public BookDto setId(String id) {
        this.id = id;
        return this;
    }

    public BookDto setIsbn(Isbn isbn) {
        this.isbn = isbn;
        return this;
    }

    public BookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookDto setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public BookDto setAuthor(Author author) {
        this.author = author;
        return this;
    }

    public String getId() {
        return id;
    }

    public String getIsbn() {
        return isbn.getIsbn();
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
}
