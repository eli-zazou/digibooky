package com.switchfully.www.service.mapper;

import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.dto.BookDto;
import com.switchfully.www.domain.dto.CreateBookDto;
import com.switchfully.www.domain.dto.UpdateBookDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class BookMapper {

    public List<BookDto> mapToDTO(List<Book> books) {
        return books
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public BookDto mapToDTO(Book book) {
        return new BookDto()
                .setId(book.getId())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor())
                .setSummary(book.getSummary());
    }

    public Book mapToEntity(CreateBookDto createBookDto) {
        return new Book(createBookDto.getIsbn(), createBookDto.getTitle(), createBookDto.getSummary(), createBookDto.getAuthor());
    }

    public Book mapToEntity(UpdateBookDto updateBookDto) {
        // TODO check but updateBookDto shouldn't have isbn.
        return new Book(null, updateBookDto.getTitle(), updateBookDto.getSummary(), updateBookDto.getAuthor());
    }
}
