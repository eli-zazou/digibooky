package com.switchfully.www.api;

import com.switchfully.www.domain.dto.BookDto;
import com.switchfully.www.domain.dto.CreateBookDto;
import com.switchfully.www.domain.dto.UpdateBookDto;
import com.switchfully.www.service.BookService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;

import java.util.List;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON) //do we need this, since it is already at lines 15-16 ?
    @ResponseStatus(201)
    public Response createBook(CreateBookDto createBookDto) {
        return Response.status(201).entity(bookService.createBook(createBookDto)).build();
    }

    @GET
    @Path("/{id}")
    @ResponseStatus(200)
    public BookDto getBook(@PathParam("id") String id) {
        return bookService.getBookById(id);
    }

    @GET
    @ResponseStatus(200)
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GET
    @Path("/author/{author}")
    @ResponseStatus(200)
    public List<BookDto> getBooksByAuthor(String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GET
    @Path("/isbn/{isbn}")
    @ResponseStatus(200)
    public List<BookDto> getBooksByIsbn(String isbn) {
        return bookService.getBooksByIsbn(isbn);
    }

    @GET
    @Path("/title/{title}")
    @ResponseStatus(200)
    public List<BookDto> getBooksByTitle(String title) {
        return bookService.getBooksByTitle(title);
    }

    @PUT
    @Path("/{id}")
    @ResponseStatus(200)
    public Response updateBook(UpdateBookDto updateProfessorDto, @PathParam("id") String id) {
        return null;
    }

    @DELETE
    @Path("/{id}")
    @ResponseStatus(202)
    public Response deleteBook(@PathParam("id") String id) {
        return null;
    }
}