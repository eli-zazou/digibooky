package com.switchfully.www.api;

import com.switchfully.www.domain.book.Author;
import com.switchfully.www.domain.book.Book;
import com.switchfully.www.domain.book.Isbn;
import com.switchfully.www.domain.dto.BookDto;
import com.switchfully.www.repository.BookRepository;
import com.switchfully.www.service.BookService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.OK;

@TestHTTPEndpoint(BookController.class)
@QuarkusTest //Rest Assured
class BookControllerTest {

    @Inject
    BookRepository bookRepository;

    @Inject
    BookService bookService;

    private BookDto bookDto1;
    private BookDto bookDto2;
    private Book book1;
    private Book book2;

    @BeforeEach
    void setUp() {
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
    void getAllBooks_givingBookService_getRestCallWithAllBooks() {
        // given
        addAllBooksInRepo(List.of(book1, book2));
        // when
        List<BookDto> actualBooks = bookService.getAllBooks();
        // then
        Assertions.assertThat(actualBooks).containsExactlyInAnyOrder(bookDto1, bookDto2);

        List<BookDto> bookDTOList = given()
                .when()
                .get()
                .then()
                .statusCode(OK)
                .contentType(ContentType.JSON)
                .extract()
                .body()
                .jsonPath()
                .getList(".", BookDto.class);

        Assertions.assertThat(bookDTOList).containsExactly(bookDto1, bookDto2);
    }

    private void addAllBooksInRepo(List<Book> books) {
        books.forEach(book -> bookRepository.addBook(book));
    }

}