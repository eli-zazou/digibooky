package com.switchfully.www.repository;

import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.Member;
import com.switchfully.www.domain.Rental;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class RentalRepository {
    private Map<String, Rental> rentalById;

    private RentalRepository () {
        this.rentalById = new HashMap<>();
    }

    public Rental addRental(Rental rental) {

        rentalById.put(rental.getId(),rental);
        return rental;
    }

    public Boolean removeRental(Rental rental) {
        rentalById.remove(rental.getId(),rental);
        return true;
    }

    public Collection<Rental> getRentalByMember(String id){
        return rentalById.values()
                .stream()
                .filter(rental -> rental.getMember().getId().equals(id))
                .toList();
    }

    public Optional<Rental> getById(String Id) {
        return Optional.ofNullable(rentalById.get(Id));
    }

}