package com.switchfully.www.domain.book;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

public class Book {
    private final String id;
    private Isbn isbn;
    private String title;
    private String summary;
    private Author author;
    private LocalDateTime dateCreated;
    private LocalDateTime dateDeleted;
    private LocalDateTime dateUpdated;
    private LocalDate dateOfReturn;
    private BookStatus bookStatus;
    private String borrowedTo;


    public Book(String isbn, String title, String summary, Author author) {
        this.id = UUID.randomUUID().toString();
        this.dateCreated = LocalDateTime.now();
        this.dateDeleted = null;
        this.dateUpdated = null;
        this.bookStatus = BookStatus.AVAILABLE;
        this.isbn = new Isbn(isbn);
        setTitle(title);
        this.summary = summary;
        setAuthor(author);
    }

    public String getId() {
        return id;
    }

    public Isbn getIsbn() {
        return isbn;
    }

    public String getIsbnIdentifier() {
        return isbn.getIsbn();
    }

    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public Author getAuthor() {
        return author;
    }

    public String getAuthorFullname() {
        return author.getFirstName() + " " + author.getLastName();
    }

    public BookStatus getBookStatus() {
        return bookStatus;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public LocalDateTime getDateDeleted() {
        return dateDeleted;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateDeleted(LocalDateTime dateDeleted) {
        this.dateDeleted = dateDeleted;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Book setIsbn(Isbn isbn) throws IllegalArgumentException {
        if (isbn == null) {
            throw new IllegalArgumentException("ISBN of the book can't be empty");
        }
        this.isbn = isbn;
        return this;
    }

    public void setTitle(String title) {
        if (title == null) {
            throw new IllegalArgumentException("Please provide a title for the book");
        }
        this.title = title;
    }

    public void setAuthor(Author author) {
        if (author == null) {
            throw new IllegalArgumentException("Please provide an author for the book");
        }
        this.author = author;
    }

    public void setBookStatus(BookStatus bookStatus) {
        this.bookStatus = bookStatus;
    }

    public Book changeTitle(String title) {
        if (title != null) {
            this.title = title;
        }
        return this;
    }

    public Book changeSummary(String summary) {
        if (summary != null) {
            this.summary = summary;
        }
        return this;
    }

    public Book changeAuthor(Author author) {
        if (author != null) {
            this.author = author;
        }
        return this;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", isbn=" + isbn +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", author=" + author +
                '}';
    }

    public boolean isDeleted() {
        return dateDeleted != null;
    }

    public boolean isAvailableForRent() {
        return !isDeleted() && getBookStatus() == BookStatus.AVAILABLE;
    }

    public Book setBorrowedTo(String borrowedTo) {
        this.borrowedTo = borrowedTo;
        return this;
    }

    public String getBorrowedTo() {
        return borrowedTo;
    }

    public LocalDate getDateOfReturn() {
        return dateOfReturn;
    }

    public Book setDateOfReturn(LocalDate dateOfReturn) {
        this.dateOfReturn = dateOfReturn;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(isbn, book.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn);
    }
}

