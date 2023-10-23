package com.switchfully.www.domain.dto;

import com.switchfully.www.domain.book.Author;
import com.switchfully.www.domain.book.Isbn;

public class CreateBookDto {
    private String isbn;
    private String title;
    private String summary;
    private Author author;

    public CreateBookDto() {
        //JACKSON
    }

    public String getIsbn() {
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

    public CreateBookDto setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public CreateBookDto setTitle(String title) {
        this.title = title;
        return this;
    }

    public CreateBookDto setSummary(String summary) {
        this.summary = summary;
        return this;
    }

    public CreateBookDto setAuthor(Author author) {
        this.author = author;
        return this;
    }
}
