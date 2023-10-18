package com.switchfully.www.repository;

import com.switchfully.www.domain.Book;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookRepository {

    private final Map<String, Book> booksById;

    public BookRepository(){
        this.booksById = new HashMap<>();
    }

    public Optional<Book> getById(String Id){
        return null;
    }

    


}
