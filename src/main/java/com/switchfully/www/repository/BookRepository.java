package com.switchfully.www.repository;

import com.switchfully.www.domain.Book;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
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

    public Book addBook(Book book) {
        booksById.put(book.getId(), book);
        return book;
    }

    public boolean delete(Book book) {
        // TODO check if the book is lended by a member
        if (book.getDateDeleted() != null) {
            book.setDateDeleted(LocalDateTime.now());
            return true;
        }
        return false;
    }

    public Collection<Book> getByAuthor(String author) {
        return booksById.values().stream().filter(book -> book.getAuthor().getLastname().equalsIgnoreCase(author)).collect(Collectors.toList());
    }

    public Collection<Book> getByIsbn(String author) {
        return booksById.values().stream().filter(book -> book.getIsbn().getIsbn().equalsIgnoreCase(author)).collect(Collectors.toList());
    }

    public Collection<Book> getByTitle(String author) {
        return booksById.values().stream().filter(book -> book.getTitle().equalsIgnoreCase(author)).collect(Collectors.toList());
    }


    public Collection<Book> getAllBooks() {
        return booksById.values();
    }
}
