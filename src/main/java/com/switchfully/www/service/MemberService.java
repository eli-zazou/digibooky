package com.switchfully.www.service;

import com.switchfully.www.api.CreateMemberDTO;
import com.switchfully.www.domain.Member;
import com.switchfully.www.domain.dto.MemberDto;
import com.switchfully.www.repository.MemberRepository;
import com.switchfully.www.service.mapper.MemberMapper;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;


@ApplicationScoped
public class MemberService {

    private MemberRepository memberRepository;
    private MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    public MemberDto createMember(CreateMemberDTO createMemberDTO) {
        Member memberToAdd = memberMapper.mapToEntity(createMemberDTO);
        return memberMapper.mapToDTO(memberRepository.addMember(memberToAdd));
    }

    public MemberDto createAdminOrLibrarian(CreateMemberDTO createMemberDTO) {
        Member memberToAdd = memberMapper.mapToEntityAdmin(createMemberDTO);
        return memberMapper.mapToDTO(memberRepository.addMember(memberToAdd));
    }


    public List<MemberDto> getAllMembers() {
        return memberMapper.mapToDTO(memberRepository.getAllMember().stream().toList());
    }
}
