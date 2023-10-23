package com.switchfully.www.domain.book;

import java.util.Objects;

public class Isbn {
    private String isbnNumber;

    private Isbn() {
    }

    public Isbn(String isbn) {
        this.isbnNumber = isbn;
        if (!isValidIsbn(isbn)) throw new IllegalArgumentException("This ISBN is not valid.");
    }

    public String getIsbn() {
        return isbnNumber;
    }

    public void setIsbn(String isbn) {
        this.isbnNumber = isbn;
    }

    public static boolean isValidIsbn(String isbn) {
        String isbnC = isbn.replaceAll("-", "");
        if (isbnC == null) return false;
        if (isbnC.length() != 13) return false;
        if (!isbnC.matches("[0-9]+")) return false;
        int sum = 0;
        for (int count = 0; count < 12; count++) {
            int digit = isbnC.charAt(count) - '0';
            sum += (count % 2 == 0) ? digit : digit * 3;
        }

        int checkDigit = isbnC.charAt(12) - '0';
        int calculatedCheckDigit = (10 - (sum % 10)) % 10;

        return checkDigit == calculatedCheckDigit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Isbn isbn1)) return false;
        return Objects.equals(isbnNumber, isbn1.isbnNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbnNumber);
    }
}



