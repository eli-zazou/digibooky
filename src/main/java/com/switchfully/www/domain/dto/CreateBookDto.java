package com.switchfully.www.domain.dto;

import com.switchfully.www.domain.Author;
import com.switchfully.www.domain.ISBN;

public class CreateBookDto {
    private ISBN isbn;
    private String title;
    private String summary;
    private AuthorDTO author;

    public CreateBookDto(){}
    public CreateBookDto(ISBN isbn, String title, String summary, AuthorDTO author) {
        this.isbn = isbn;
        this.title = title;
        this.summary = summary;
        this.author = author;
    }

    public ISBN getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public AuthorDTO getAuthor() {
        return author;
    }
}
