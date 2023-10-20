package com.switchfully.www.repository;

import com.switchfully.www.domain.Book;
import jakarta.enterprise.context.ApplicationScoped;

import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookRepository {

    private final Map<String, Book> booksById;

    public BookRepository() {
        this.booksById = new HashMap<>();

    }



    public Book addBook(Book book) {
        booksById.put(book.getId(), book);
        return book;
    }


    public Optional<Book> getById(String Id) {
        return Optional.ofNullable(booksById.get(Id));
    }

    public Optional<Book> getByIsbn(String isbn) {
        isbn = isbn.replaceAll("\\*", ".*");
        isbn = isbn.replaceAll("-", "");
        Pattern pattern = Pattern.compile(isbn);

        return booksById.values()
                .stream()
                .filter(book -> pattern.matcher(book.getIsbnIdentifier()).find())
                .findFirst();
    }

    public Collection<Book> getByTitle(String title) {
        title = title.replaceAll("\\*", ".*");
        title = title.replaceAll(" ", "\\\\s+");
        Pattern pattern = Pattern.compile("\\b" + title + "\\b", Pattern.CASE_INSENSITIVE);

        return booksById
                .values()
                .stream()
                .filter(book -> pattern.matcher(book.getTitle()).find())
                .toList();
    }

    public Collection<Book> getByAuthor(String author) {
        author = author.replaceAll("\\*", ".*");
        author = author.replaceAll(" ", "\\\\s+");
        Pattern pattern = Pattern.compile("\\b" + author + "\\b", Pattern.CASE_INSENSITIVE);

        return booksById.values()
                .stream()
                .filter(book -> pattern.matcher(book.getAuthorFullname()).find())
                .collect(Collectors.toList());
    }

    public boolean deleteById(Book book) {
        // TODO check if the book is lent by a member
        if (book.getDateDeleted() == null) {
            book.setDateDeleted(LocalDateTime.now());
            return true;
        }
        return false;
    }
    
    public Optional<Book> updateBookById(Book modifiedBook, String id) {
        Book bookToUpdate = booksById.get(id);
        if (bookToUpdate == null) {
            return Optional.empty();
        }
        return Optional.of(bookToUpdate.update(modifiedBook));
    }

    public Collection<Book> getAllBooks() {
        return booksById.values().stream()
                .filter(book -> !(book.isDeleted()))
                .collect(Collectors.toList());
    }
}
