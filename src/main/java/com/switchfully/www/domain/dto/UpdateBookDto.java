package com.switchfully.www.domain.dto;

import com.switchfully.www.domain.Author;
import com.switchfully.www.domain.Isbn;

public class UpdateBookDto {
    private Isbn isbn;

    private String title;
    private String summary;
    private Author author;

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

    public UpdateBookDto setIsbn(Isbn isbn) {
        this.isbn = isbn;
        return this;
    }

    public UpdateBookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public UpdateBookDto setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public UpdateBookDto setAuthor(Author author) {
        this.author = author;
        return this;
    }
}
