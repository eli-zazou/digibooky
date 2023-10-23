package com.switchfully.www.domain.book;

import java.util.Objects;

public class Isbn {
    private String isbn;

    private Isbn() {
    }


    public Isbn(String isbn) {
//        if (!isValidIsbn()) throw new IllegalArgumentException("This ISBN is not valid.");

        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public boolean isValidIsbn(String isbn) {
        if (isbn == null) return false;
        if (isbn.length() != 13) return false;
        if (!this.isbn.matches("[0-9]+")) return false;
        return true;
//        int sum = 0;
//        for (int count = 0; count < 12; count++) {
//            int digit = this.isbn.charAt(count);
//            //int digit = Character.getNumericValue(this.isbn.charAt(i));
//            sum += (count % 2 == 0) ? digit : digit * 3;
//        }
//
//        int checkDigit = this.isbn.charAt(12);
//        int calculatedCheckDigit = (10 - (sum % 10)) % 10;
//
//        return checkDigit == calculatedCheckDigit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Isbn isbn1)) return false;
        return Objects.equals(isbn, isbn1.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}



