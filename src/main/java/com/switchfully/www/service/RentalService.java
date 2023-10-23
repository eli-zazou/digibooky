package com.switchfully.www.service;

import com.switchfully.www.domain.book.Book;
import com.switchfully.www.domain.book.BookStatus;
import com.switchfully.www.domain.member.Member;
import com.switchfully.www.domain.rental.Rental;
import com.switchfully.www.domain.dto.BookDto;
import com.switchfully.www.domain.dto.CreateRentalDto;
import com.switchfully.www.domain.dto.RentalDto;
import com.switchfully.www.exceptions.NotFoundException;
import com.switchfully.www.repository.BookRepository;
import com.switchfully.www.repository.MemberRepository;
import com.switchfully.www.repository.RentalRepository;
import com.switchfully.www.service.mapper.BookMapper;
import com.switchfully.www.service.mapper.RentalMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import java.util.List;

@ApplicationScoped
public class RentalService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;
    private final BookMapper bookMapper;

    public RentalService(BookRepository bookRepository, MemberRepository memberRepository,
                         RentalRepository rentalRepository, RentalMapper rentalMapper, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
        this.bookMapper = bookMapper;
    }

    public RentalDto rentABook(CreateRentalDto createRentalDto) {
        Book bookToRent = bookRepository.getByIsbn(createRentalDto.getIsbn().getIsbn())
                .orElseThrow(() -> new NotFoundException("No book could be found for this ISBN."));
        if (!bookToRent.isAvailableForRent()) {
            throw new IllegalArgumentException("This book is not available for rent.");
        }
        Member member = memberRepository.getMemberById(createRentalDto.getMemberId())
                .orElseThrow(() -> new NotFoundException("No member could be found for this id."));
        Rental rental = new Rental(member, bookToRent);
        bookToRent.setBookStatus(BookStatus.BORROWED);
        bookToRent.setBorrowedTo(member.getFirstName());
        bookToRent.setDateOfReturn(rental.getDueDate());
        return rentalMapper.mapToDto(rentalRepository.addRental(rental));
    }

    public Response returnBook(String id) {
        Rental rentalToDelete = rentalRepository.getById(id)
                .orElseThrow(() -> new NotFoundException("No Rental could be found for id " + id));
        rentalToDelete.getBook().setBookStatus(BookStatus.AVAILABLE);
        rentalToDelete.getBook().setBorrowedTo(null);
        rentalToDelete.getBook().setDateOfReturn(null);
        if (rentalToDelete.isOverDue()) {
            return Response.status(402).build();
        }
        if (!rentalRepository.removeRental(rentalToDelete)) {
            throw new NotFoundException("Couldn't delete unexisting rental " + id);
        }
        return Response.status(200).build();
    }

    public List<RentalDto> getRentalByMember(String id) {
        return rentalMapper.mapToDTO(rentalRepository.getRentalByMember(id).stream().toList());
    }

    public List<BookDto> getOverdueBooks() {
        return bookMapper.mapToDTO(rentalRepository.getOverdueBooks());
    }
}
