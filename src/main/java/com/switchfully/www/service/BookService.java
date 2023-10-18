package com.switchfully.www.service;

import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.dto.BookDto;
import com.switchfully.www.domain.dto.CreateBookDto;
import com.switchfully.www.repository.BookRepository;
import com.switchfully.www.service.mapper.BookMapper;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;


    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }


    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.getAllBooks().stream().toList();
        return bookMapper.mapToDTO(books);

    }

    public List<BookDto> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.getByAuthor(author).stream().toList();
        return bookMapper.mapToDTO(books);
    }

    public List<BookDto> getBooksByIsbn(String isbn) {
        List<Book> books = bookRepository.getByAuthor(isbn).stream().toList();
        return bookMapper.mapToDTO(books);
    }

    public List<BookDto> getBooksByTitle(String title) {
        List<Book> books = bookRepository.getByAuthor(title).stream().toList();
        return bookMapper.mapToDTO(books);
    }

    public BookDto createBook(CreateBookDto createBookDto) {
        Book bookToSave = new Book(createBookDto.getIsbn(), createBookDto.getTitle(), createBookDto.getSummary(), createBookDto.getAuthor());

        return bookMapper.mapToDTO(bookRepository.save(bookToSave));
    }

    public BookDto getBookById(String id) {
        return bookMapper.mapToDTO(
                bookRepository
                        .getById(id)
                        .orElseThrow(() -> new IllegalArgumentException("No Book could be found for id " + id)));
    }
}
