package com.switchfully.www.service;

import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.dto.BookDto;
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
}
