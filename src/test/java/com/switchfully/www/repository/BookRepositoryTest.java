package com.switchfully.www.repository;

import com.switchfully.www.domain.book.Author;
import com.switchfully.www.domain.book.Book;
import com.switchfully.www.domain.book.Isbn;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.Optional;

class BookRepositoryTest {
    private BookRepository bookRepository;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setup() {
        bookRepository = new BookRepository();
        book1 = new Book("978-8845292613",
                "The lord of the Rings",
                "Hobbit goes on a journey",
                new Author("J.R.R", "Tolkien")
        );
        book2 = new Book("978-1338878929",
                "Harry Potter Plays Chess",
                "Wizzard boy goes to school",
                new Author("J.K.", "Rowling")
        );
    }

    @Test
    void save_givenABook_thenBookIsInRepository() {
        Book bookToSave = new Book("9781484278680", "Title", "Summary", new Author("firstname", "lastname"));

        Assertions.assertThat(bookToSave).isEqualTo(bookRepository.addBook(bookToSave).orElseThrow());
    }

    @Test
    void getAllBooks_givenBooksInDb_thenGetCollectionOfBooks() {
        // given
        bookRepository.addBook(book1);
        bookRepository.addBook(book2);
        // when
        Collection<Book> actualBooks = bookRepository.getAllBooks();
        // then
        Assertions.assertThat(actualBooks).containsExactlyInAnyOrder(book1, book2);
    }

    @Test
    void getById_givingABookId_thenGetDetailBook() {
        // given
        bookRepository.addBook(book1);
        // when
        Optional<Book> actualBook = bookRepository.getById(book1.getId());
        // then
        Assertions.assertThat(actualBook).isEqualTo(Optional.ofNullable(book1));
    }

//    @Test
//    void getByIsbn_givingAnIsbn_thenGetDetailBookByIsbn() {
//        // given
//        bookRepository.addBook(book1);
//        // when
////        Collection<Book> actualBooks = bookRepository.getByIsbn("978-8845292613");
//        // then
//        Assertions.assertThat(actualBooks).containsExactlyInAnyOrder(book1);
//    }

    @Test
    void getByTitle_givingATitle_thenGetDetailBookByTitle() {
        // given
        bookRepository.addBook(book1);
        // when
        Collection<Book> actualBooks = bookRepository.getByTitle("The lord of the Rings");
        // then
        Assertions.assertThat(actualBooks).containsExactlyInAnyOrder(book1);
    }

    @Test
    void getByAuthor_givingAnAuthor_thenGetDetailBookByAuthor() {
        // given
        bookRepository.addBook(book1);
        // when
        Collection<Book> actualBooks = bookRepository.getByAuthor("Tolkien");
        // then
        Assertions.assertThat(actualBooks).containsExactlyInAnyOrder(book1);
    }
}