package com.switchfully.www.service;

import com.switchfully.www.domain.Author;
import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.Isbn;
import com.switchfully.www.domain.dto.BookDto;
import com.switchfully.www.exceptions.NotFoundException;
import com.switchfully.www.repository.BookRepository;
import com.switchfully.www.service.mapper.BookMapper;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

class BookServiceTest {

    private BookRepository bookRepositoryMock;
    private BookMapper bookMapperMock;
    private BookService bookService;
    private Book book1;
    private Book book2;
    private BookDto bookDto1;
    private BookDto bookDto2;

    @BeforeEach
    void setUp() {
        bookRepositoryMock = Mockito.mock(BookRepository.class);
        bookMapperMock = Mockito.mock(BookMapper.class);
        bookService = new BookService(bookRepositoryMock, bookMapperMock);
        book1 = new Book(new Isbn("978-8845292613"),
                "The lord of the Rings",
                "Hobbit goes on a journey",
                new Author("J.R.R", "Tolkien")
        );
        book2 = new Book(new Isbn("978-1338878929"),
                "Harry Potter Plays Chess",
                "Wizzard boy goes to school",
                new Author("J.K.", "Rowling")
        );

        bookDto1 = new BookDto()
                .setId(book1.getId())
                .setIsbn(book1.getIsbn())
                .setTitle(book1.getTitle())
                .setAuthor(book1.getAuthor())
                .setSummary(book1.getSummary());

        bookDto2 = new BookDto()
                .setId(book2.getId())
                .setIsbn(book2.getIsbn())
                .setTitle(book2.getTitle())
                .setAuthor(book2.getAuthor())
                .setSummary(book2.getSummary());

    }


    @Test
    void getAllBooks_givenRepoWithBooks_thenReturnBookDTO() {
        // given
        List<Book> books = List.of(book1, book2);
        Mockito.when(bookRepositoryMock.getAllBooks()).thenReturn(books);
        Mockito.when(bookMapperMock.mapToDTO(books)).thenReturn(List.of(bookDto1,bookDto2));
        // when
        List<BookDto> actualBooks = bookService.getAllBooks();
        // then
        Assertions.assertThat(actualBooks).containsExactlyInAnyOrder(bookDto1,bookDto2);
    }

    @Test
    void getBooksByAuthor_givenRepoWithBooks_thenReturnBookDTO() {
        // given
        Mockito.when(bookRepositoryMock.getByAuthor("Rowling")).thenReturn(List.of(book2));
        Mockito.when(bookMapperMock.mapToDTO(List.of(book2))).thenReturn(List.of(bookDto2));
        // when
        List<BookDto> actualBooks = bookService.getBooksByAuthor("Rowling");
        // then
        Assertions.assertThat(actualBooks).containsExactlyInAnyOrder(bookDto2);
    }

    @Test
    void getBookById_givenRepoWithBooks_thenReturnBookDTO() {
        // given
        Mockito.when(bookRepositoryMock.getById(book1.getId())).thenReturn(Optional.ofNullable(book1));
        Mockito.when(bookMapperMock.mapToDTO(book1)).thenReturn(bookDto1);
        // when
        BookDto actualBookDto = bookService.getBookById(book1.getId());
        // then
        Assertions.assertThat(actualBookDto).isEqualTo(bookDto1);
    }

    @Test
    void getBookById_givenBookNotInRepo_thenThrowNotFoundException() {
        Assertions.assertThatThrownBy(() -> bookService.getBookById(book1.getId()))
                .isInstanceOf(NotFoundException.class)
                .hasMessage("No Book could be found for id " + book1.getId());
    }

    @Test
    void getBooksByIsbn_givenRepoWithBooks_thenReturnBookDTO() {
        // given
        Mockito.when(bookRepositoryMock.getByIsbn("978-1338878929")).thenReturn(List.of(book2));
        Mockito.when(bookMapperMock.mapToDTO(List.of(book2))).thenReturn(List.of(bookDto2));
        // when
        List<BookDto> actualBooks = bookService.getBooksByIsbn("978-1338878929");
        // then
        Assertions.assertThat(actualBooks).containsExactlyInAnyOrder(bookDto2);
    }

    @Test
    void getBooksByTitle_givenRepoWithBooks_thenReturnBookDTO() {
        // given
        Mockito.when(bookRepositoryMock.getByTitle("Harry Potter Plays Chess")).thenReturn(List.of(book2));
        Mockito.when(bookMapperMock.mapToDTO(List.of(book2))).thenReturn(List.of(bookDto2));
        // when
        List<BookDto> actualBooks = bookService.getBooksByTitle("Harry Potter Plays Chess");
        // then
        Assertions.assertThat(actualBooks).containsExactlyInAnyOrder(bookDto2);
    }
}