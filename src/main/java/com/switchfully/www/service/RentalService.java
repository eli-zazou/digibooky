package com.switchfully.www.service;

import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.BookStatus;
import com.switchfully.www.domain.Member;
import com.switchfully.www.domain.Rental;
import com.switchfully.www.domain.dto.CreateRentalDto;
import com.switchfully.www.domain.dto.RentalDto;
import com.switchfully.www.exceptions.NotFoundException;
import com.switchfully.www.repository.BookRepository;
import com.switchfully.www.repository.MemberRepository;
import com.switchfully.www.repository.RentalRepository;
import com.switchfully.www.service.mapper.RentalMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.core.Response;

import java.util.Optional;

@ApplicationScoped
public class RentalService {

    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;


    public RentalService(BookRepository bookRepository, MemberRepository memberRepository, RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.bookRepository = bookRepository;
        this.memberRepository = memberRepository;
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }

    public RentalDto rentABook(CreateRentalDto createRentalDto){
        Book bookToRent = bookRepository.getByIsbn(createRentalDto.getIsbn().getIsbn())
                .orElseThrow(()-> new NotFoundException("No book could be found for this ISBN."));
        if (!bookToRent.isAvailableForRent()){
            throw new IllegalArgumentException("This book is not available for rent.");
        }
        Member member = memberRepository.getMemberById(createRentalDto.getMemberId())
                .orElseThrow(()-> new NotFoundException("No member could be found for this id."));
        Rental rental = new Rental(member, bookToRent);
        bookToRent.setBookStatus(BookStatus.BORROWED);
        bookToRent.setBorrowedTo(member.getFirstName());
        bookToRent.setDateOfReturn(rental.getDueDate());
        return rentalMapper.mapToDto(rentalRepository.addRental(rental));
    }

    public Response returnBook(String id){
        Rental rentalToDelete = rentalRepository.getById(id)
                .orElseThrow(() -> new NotFoundException("No Rental could be found for id " + id));
        rentalToDelete.getBook().setBookStatus(BookStatus.AVAILABLE);
        rentalToDelete.getBook().setBorrowedTo(null);
        rentalToDelete.getBook().setDateOfReturn(null);
        if(rentalToDelete.isOverDue()){
            return Response.status(402).build();
        }
         rentalRepository.removeRental(rentalToDelete);
        return Response.status(200).build();
    }
}
