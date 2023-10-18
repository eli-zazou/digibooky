package com.switchfully.www.domain.dto;

import com.switchfully.www.domain.Author;
import com.switchfully.www.domain.ISBN;

public class BookDto {
    private String id;
    private ISBN isbn;
    private String title;
    private String summary;
    private Author author;

    public BookDto() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setIsbn(ISBN isbn) {
        this.isbn = isbn;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public void setAuthor(Author author) {
        this.author = author;
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
