package com.switchfully.www.domain.dto;

import com.switchfully.www.domain.Author;

public class UpdateBookDto {
    private String title;
    private String summary;
    private Author author;

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public Author getAuthor() {
        return author;
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
