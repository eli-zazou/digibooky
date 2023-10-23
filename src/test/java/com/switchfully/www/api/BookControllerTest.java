package com.switchfully.www.api;

import com.switchfully.www.domain.book.Author;
import com.switchfully.www.domain.book.Isbn;
import com.switchfully.www.domain.dto.BookDto;
import com.switchfully.www.service.BookService;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.UUID;

import static io.restassured.RestAssured.given;
import static org.jboss.resteasy.reactive.RestResponse.StatusCode.OK;

@TestHTTPEndpoint(BookController.class)
@QuarkusTest //Rest Assured
class BookControllerTest {

    /*
    @Inject
    BookService service;

     */

    private BookService bookServiceMock;
    //    private SecurityService securityServiceMock;
    private BookDto bookDto1;
    private BookDto bookDto2;

    @BeforeEach
    void setUp() {
        bookServiceMock = Mockito.mock(BookService.class);

        bookDto1 = new BookDto()
                .setId(UUID.randomUUID().toString())
                .setIsbn(new Isbn("978-8845292613"))
                .setTitle("The lord of the Rings")
                .setAuthor(new Author("J.R.R", "Tolkien"))
                .setSummary("Hobbit goes on a journey");

        bookDto2 = new BookDto()
                .setId(UUID.randomUUID().toString())
                .setIsbn(new Isbn("985-1234567485"))
                .setTitle("Harry goes bananas")
                .setAuthor(new Author("J.K.", "Rowling"))
                .setSummary("Wizzard boy goes to school");
    }

    @Test
    void getAllBooks_givingBookService_getRestCallWithAllBooks() {
        // given
        List<BookDto> booksDto = List.of(bookDto1, bookDto2);
        Mockito.when(bookServiceMock.getAllBooks()).thenReturn(booksDto);
        // when
        // List<BookDto> actualBooks = bookService.getAllBooks();
        // then
        // Assertions.assertThat(actualBooks).containsExactlyInAnyOrder(bookDto1,bookDto2);

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

}