package com.switchfully.www.repository;

import com.switchfully.www.domain.Author;
import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.ISBN;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BookRepositoryTest {
    private BookRepository bookRepository;

    @BeforeEach
    void setup() {
        bookRepository = new BookRepository();
    }

    @Test
    void save_givenABook_thenBookIsInRepository(){
        Book bookToSave = new Book(new ISBN("0123456789123"),"Title","Summary",new Author("firstname", "lastname"));

        Assertions.assertThat(bookToSave).isEqualTo(bookRepository.save(bookToSave));
    }
}