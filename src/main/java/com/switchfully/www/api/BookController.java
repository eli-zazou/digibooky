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
    @ResponseStatus(201)
    public Response createBook(CreateBookDto createBookDto) {
        return null;
    }

    @GET
    @Path("/{id}")
    @ResponseStatus(200)
    public BookDto getBook(@PathParam("id") String id) {
        return null;
    }

    @GET
    @ResponseStatus(200)
    public List<BookDto> getAllBooks() {
        return bookService.getAllBooks();
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