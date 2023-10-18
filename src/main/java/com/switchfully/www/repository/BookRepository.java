package com.switchfully.www.repository;

import com.switchfully.www.domain.Author;
import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.ISBN;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookRepository {

    private final Map<String, Book> booksById;

    public BookRepository() {
        this.booksById = new HashMap<>();

    }

    public Optional<Book> getById(String Id) {
        return Optional.ofNullable(booksById.get(Id));
    }

    public Book save(Book book) {
        booksById.put(book.getId(), book);
        return book;
    }


    public Collection<Book> getByAuthor(String author) {
        return booksById.values().stream().filter(book -> book.getAuthor().getLastname().equalsIgnoreCase(author)).collect(Collectors.toList());
    }


    public Collection<Book> getAllBooks() {

        return booksById.values();
    }
}
