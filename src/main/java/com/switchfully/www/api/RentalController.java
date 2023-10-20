package com.switchfully.www.api;

import com.switchfully.www.domain.Feature;
import com.switchfully.www.domain.dto.CreateRentalDto;
import com.switchfully.www.domain.dto.RentalDto;
import com.switchfully.www.service.RentalService;
import com.switchfully.www.service.SecurityService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

@Path("/rental")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RentalController {

    private final RentalService rentalService;
    private final SecurityService securityService;

    public RentalController(RentalService rentalService, SecurityService securityService ){
        this.rentalService = rentalService;
        this.securityService = securityService;
    }

    @POST
    @ResponseStatus(201)
    public RentalDto lendBookByIsbn(@RestHeader String authorization, CreateRentalDto createRentalDto){
        securityService.validateAuthorization(authorization, Feature.BORROW_BOOK);
        return rentalService.rentABook(createRentalDto);
    }

    @DELETE
    @Path("{id}")
    public Response returnBook(@RestHeader String authorization,@PathParam("id") String id){
        securityService.validateAuthorization(authorization, Feature.RETURN_BOOK);
        return rentalService.returnBook(id);
    }

}
