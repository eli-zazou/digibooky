package com.switchfully.www.api;

import com.switchfully.www.domain.Feature;
import com.switchfully.www.domain.dto.BookDto;
import com.switchfully.www.domain.dto.CreateBookDto;
import com.switchfully.www.domain.dto.UpdateBookDto;
import com.switchfully.www.service.BookService;
import com.switchfully.www.service.SecurityService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.List;

@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookController {

    private final BookService bookService;
    private final SecurityService securityService;

    public BookController(BookService bookService, SecurityService securityService) {
        this.bookService = bookService;
        this.securityService = securityService;
    }

    @POST
    @ResponseStatus(201)
    public Response createBook(@RestHeader String authorization, CreateBookDto createBookDto) {
        securityService.validateAuthorization(authorization, Feature.MANAGE_BOOKS);
        return Response.status(201).entity(bookService.createBook(createBookDto)).build();
    }

    @GET
    @Path("/{id}")
    @ResponseStatus(200)
    public BookDto getBook(@PathParam("id") String id) {
        return bookService.getBookById(id);
    }

    @GET
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GET
    @Path("/author/{author}")
    public List<BookDto> getBooksByAuthor(String author) {
        return bookService.getBooksByAuthor(author);
    }

    @GET
    @Path("/isbn/{isbn}")
    public List<BookDto> getBooksByIsbn(String isbn) {
        return bookService.getBooksByIsbn(isbn);
    }

    @GET
    @Path("/title/{title}")
    public List<BookDto> getBooksByTitle(String title) {
        return bookService.getBooksByTitle(title);
    }

    @PUT
    @Path("/{id}")
    public Response updateBook(@RestHeader String authorization, UpdateBookDto updateBookDto, @PathParam("id") String id) {
        securityService.validateAuthorization(authorization, Feature.MANAGE_BOOKS);
        return Response.status(200).entity(bookService.updateBook(updateBookDto, id)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBook(@RestHeader String authorization, @PathParam("id") String id) {
        securityService.validateAuthorization(authorization, Feature.MANAGE_BOOKS);
        return Response.status(202).entity(bookService.deleteBookById(id)).build();
    }
}