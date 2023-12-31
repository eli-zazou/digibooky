package com.switchfully.www.api;

import com.switchfully.www.domain.security.Feature;
import com.switchfully.www.domain.dto.CreateMemberDTO;
import com.switchfully.www.domain.dto.MemberDto;
import com.switchfully.www.service.MemberService;
import com.switchfully.www.service.SecurityService;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.jboss.resteasy.reactive.ResponseStatus;
import org.jboss.resteasy.reactive.RestHeader;

import java.util.List;

@Path("/member")
public class MemberController {

    private MemberService memberService;
    private final SecurityService securityService;

    public MemberController(MemberService memberService, SecurityService securityService) {
        this.memberService = memberService;
        this.securityService = securityService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @ResponseStatus(201)
    public Response createMember(CreateMemberDTO createMemberDTO) {
        return Response.status(201).entity(memberService.createMember(createMemberDTO)).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<MemberDto> getAllMembers(@RestHeader String authorization) {
        securityService.validateAuthorization(authorization, Feature.VIEW_MEMBERS);
        return memberService.getAllMembers();
    }

    @POST
    @Path("/admin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @ResponseStatus(201)
    public MemberDto createAdminOrLibrarian(@RestHeader String authorization, CreateMemberDTO createMemberDTO) {
        securityService.validateAuthorization(authorization, Feature.ADD_ADMIN);
        return memberService.createAdminOrLibrarian(createMemberDTO);
    }


}
