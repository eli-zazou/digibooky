package com.switchfully.www.service.mapper;

import com.switchfully.www.domain.dto.CreateMemberDTO;
import com.switchfully.www.domain.member.Member;
import com.switchfully.www.domain.security.UserRole;
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

    public MemberDto mapToDTO(Member member) {
        return new MemberDto()
                .setId(member.getId())
                .setLastName(member.getLastName())
                .setFirstName(member.getFirstName())
                .setEmail(member.getEmail())
                .setAddress(member.getAddress());
    }

    public Member mapToEntity(CreateMemberDTO createMemberDTO) {
        return new Member(createMemberDTO.getInss(),
                createMemberDTO.getLastName(),
                createMemberDTO.getFirstName(),
                createMemberDTO.getEmail(),
                createMemberDTO.getPassword(),
                createMemberDTO.getAddress(),
                UserRole.MEMBER);
    }

    public Member mapToEntityAdmin(CreateMemberDTO createMemberDTO) {
        return new Member(createMemberDTO.getInss(),
                createMemberDTO.getLastName(),
                createMemberDTO.getFirstName(),
                createMemberDTO.getEmail(),
                createMemberDTO.getPassword(),
                createMemberDTO.getAddress(),
                createMemberDTO.getRole());
    }
}
