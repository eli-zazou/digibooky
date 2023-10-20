package com.switchfully.www.service.mapper;

import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.Rental;
import com.switchfully.www.domain.dto.BookDto;
import com.switchfully.www.domain.dto.RentalDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class RentalMapper {

    public RentalDto mapToDto(Rental rental) {
        return new RentalDto()
                .setId(rental.getId())
                .setBook(rental.getBook())
                .setDueDate(rental.getDueDate())
                .setMember(rental.getMember());

    }

    public List<RentalDto> mapToDTO(List<Rental> rentals) {
        return rentals
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public RentalDto mapToDTO(Rental rental) {
        return new RentalDto()
                .setBook(rental.getBook())
                .setDueDate(rental.getDueDate())
                .setId(rental.getId())
                .setMember(rental.getMember());
    }

    public Rental mapToEntity(RentalDto rentalDto) {
        return new Rental(rentalDto.getMember(), rentalDto.getBook());
    }

}
