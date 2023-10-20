package com.switchfully.www.service.mapper;

import com.switchfully.www.api.CreateMemberDTO;
import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.Member;
import com.switchfully.www.domain.UserRole;
import com.switchfully.www.domain.dto.BookDto;
import com.switchfully.www.domain.dto.CreateBookDto;
import com.switchfully.www.domain.dto.MemberDto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class MemberMapper {
    public List<MemberDto> mapToDTO(List<Member> members) {
        return members
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    public MemberDto mapToDTO(Member member){
        return new MemberDto()
                .setId(member.getId())
                .setInss(member.getInss())
                .setLastName(member.getLastName())
                .setFirstName(member.getFirstName())
                .setEmail(member.getEmail())
                .setAddress(member.getAddress());
    }

    public Member mapToEntity (CreateMemberDTO createMemberDTO){
        return new Member(createMemberDTO.getInss(),
                createMemberDTO.getLastName(),
                createMemberDTO.getFirstName(),
                createMemberDTO.getEmail(),
                createMemberDTO.getPassword(),
                createMemberDTO.getAddress(),
                UserRole.MEMBER);
    }

    public Member mapToEntityAdmin (CreateMemberDTO createMemberDTO){
        return new Member(createMemberDTO.getInss(),
                createMemberDTO.getLastName(),
                createMemberDTO.getFirstName(),
                createMemberDTO.getEmail(),
                createMemberDTO.getPassword(),
                createMemberDTO.getAddress(),
                createMemberDTO.getRole());
    }

}
