package com.switchfully.www.service;

import com.switchfully.www.api.CreateMemberDTO;
import com.switchfully.www.constTest;
import com.switchfully.www.domain.*;
import com.switchfully.www.domain.dto.MemberDto;
import com.switchfully.www.repository.MemberRepository;
import com.switchfully.www.service.mapper.MemberMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MemberServiceTest {


    private MemberRepository memberRepositoryMock;
    private MemberMapper memberMapperMock;
    private MemberService memberService;
    private Address anAdress;

    @BeforeEach
    void setup(){
        memberRepositoryMock = mock(MemberRepository.class);
        memberMapperMock = mock(MemberMapper.class);
        memberService = new MemberService(memberRepositoryMock, memberMapperMock);
        anAdress = new Address("loli", "12B", new City("1200", "Bx"));
    }


    @Test
    void createMember_givenACreateMemberDto_thenReturnMember(){
        CreateMemberDTO createMemberDTO = constTest.johnDoeCreateMemberDTO;

        Member memberToAdd = new Member(createMemberDTO.getInss(),
                createMemberDTO.getLastName(),
                createMemberDTO.getFirstName(),
                createMemberDTO.getEmail(),
                createMemberDTO.getPassword(),
                createMemberDTO.getAddress(),
                createMemberDTO.getRole());

        MemberDto expectedMemberDto = new MemberDto()
                .setAddress(memberToAdd.getAddress())
                .setId(memberToAdd.getId())
                .setEmail(memberToAdd.getEmail())
                .setFirstName(memberToAdd.getFirstName())
                .setLastName(memberToAdd.getLastName());

        when(memberMapperMock.mapToEntity(createMemberDTO)).thenReturn(memberToAdd);
        when(memberRepositoryMock.addMember(memberToAdd)).thenReturn(memberToAdd);
        when(memberMapperMock.mapToDTO(memberToAdd)).thenReturn(expectedMemberDto);

        MemberDto result = memberService.createMember(createMemberDTO);

        assertEquals(expectedMemberDto, result);

    }





}