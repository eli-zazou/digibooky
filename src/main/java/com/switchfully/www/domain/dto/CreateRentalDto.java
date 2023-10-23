package com.switchfully.www.domain.dto;

import com.switchfully.www.domain.book.Isbn;

public class CreateRentalDto {

    private String memberId;
    private Isbn isbn;

    private CreateRentalDto() {
        //JACKSON
    }

    public String getMemberId() {
        return memberId;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public CreateRentalDto setMemberId(String id) {
        this.memberId = id;
        return this;
    }

    public CreateRentalDto setIsbn(Isbn isbn) {
        this.isbn = isbn;
        return this;
    }
}
