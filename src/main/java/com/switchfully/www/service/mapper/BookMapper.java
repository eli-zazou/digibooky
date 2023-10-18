package com.switchfully.www.service.mapper;

import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.dto.BookDto;
import com.switchfully.www.domain.dto.CreateBookDto;
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

    public BookDto mapToDTO(Book book){
        return new BookDto()
                .setId(book.getId())
                .setIsbn(book.getIsbn())
                .setTitle(book.getTitle())
                .setAuthor(book.getAuthor())
                .setSummary(book.getSummary());
    }

    public Book mapToEntity (CreateBookDto createBookDto){
        return new Book(createBookDto.getIsbn(),createBookDto.getTitle(),createBookDto.getSummary(), createBookDto.getAuthor());
    }
}
