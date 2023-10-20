package com.switchfully.www.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Rental {
    private static final int RENTAL_DURATION_IN_WEEKS = 3;
    private String id;
    private Member member;
    private Book book;
    private LocalDate dueDate;

    public Rental(Member member, Book book) {
        this.id = UUID.randomUUID().toString();
        this.member = member;
        this.book = book;
        this.dueDate = LocalDate.now().plusWeeks(RENTAL_DURATION_IN_WEEKS);
    }

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

    public boolean isOverDue(){
        return LocalDate.now().isAfter(dueDate);
    }
}
