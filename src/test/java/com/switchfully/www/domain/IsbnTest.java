package com.switchfully.www.domain;

import com.switchfully.www.domain.book.Isbn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class IsbnTest {
    private Isbn isbn;
    boolean result;

    @Test
    @DisplayName("ISBN is 13 digits long")
    void isValidIsbn_givenAnISBN_thenVerifyLengthEquals13() {
        String isbn = "9781484278680";

        Assertions.assertThat(Isbn.isValidIsbn(isbn)).isTrue();
    }

    @Test
    @DisplayName("ISB invalid, then throw error")
    void isbnConstructor_givenAnIncorrectISBN_thenThrowsError() {

        Assertions.assertThatThrownBy(() -> new Isbn("123")).isInstanceOf(IllegalArgumentException.class);
    }


}