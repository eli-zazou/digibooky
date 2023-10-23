package com.switchfully.www.domain.dto;

import com.switchfully.www.domain.book.Book;
import com.switchfully.www.domain.member.Member;

import java.time.LocalDate;

public class RentalDto {

    private String id;
    private Member member;
    private Book book;
    private LocalDate dueDate;

    public String getId() {
        return id;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public RentalDto setId(String id) {
        this.id = id;
        return this;
    }

    public RentalDto setMember(Member member) {
        this.member = member;
        return this;
    }

    public RentalDto setBook(Book book) {
        this.book = book;
        return this;
    }

    public RentalDto setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }
}
