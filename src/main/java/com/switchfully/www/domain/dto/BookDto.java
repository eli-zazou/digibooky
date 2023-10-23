package com.switchfully.www.domain.dto;

import com.switchfully.www.domain.book.Author;
import com.switchfully.www.domain.book.BookStatus;
import com.switchfully.www.domain.book.Isbn;

import java.time.LocalDate;
import java.util.Objects;

public class BookDto {
    private String id;
    private String isbn;
    private String title;
    private String summary;
    private Author author;
    private BookStatus bookStatus;
    private String rentedPerson;
    private LocalDate dateOfReturn;

    public BookDto() {
    }

    public BookDto setId(String id) {
        this.id = id;
        return this;
    }

    public BookDto setIsbn(String isbn) {
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

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public BookDto setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
        if (bookStatus.equals(BookStatus.BORROWED)) {

        }
        return this;
    }

    public String getId() {
        return id;
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

    public String getRentedPerson() {
        return rentedPerson;
    }

    public BookDto setRentedPerson(String rentedPerson) {
        this.rentedPerson = rentedPerson;
        return this;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public BookDto setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookDto bookDto = (BookDto) o;
        return Objects.equals(id, bookDto.id) && Objects.equals(isbn, bookDto.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isbn);
    }
}
