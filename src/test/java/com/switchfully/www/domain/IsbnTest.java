package com.switchfully.www.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class IsbnTest {
//    private Isbn isbn;
//    @BeforeEach
//    void setup(){
//        isbn = new Isbn("0123456789123");
//    }
    @Test
    void isValidIsbn_givenAnISBN_thenVerifyLengthEquals13(){
        Isbn isbn = new Isbn("0123456789123");

        Boolean result = isbn.isValidIsbn();

        Assertions.assertThat(result).isTrue();
    }
}