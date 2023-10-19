package com.switchfully.www.service;

import com.switchfully.www.api.CreateMemberDTO;
import com.switchfully.www.domain.Member;
import com.switchfully.www.domain.dto.MemberDto;
import com.switchfully.www.repository.MemberRepository;
import com.switchfully.www.service.mapper.MemberMapper;
import jakarta.enterprise.context.ApplicationScoped;


@ApplicationScoped
public class MemberService {

    private MemberRepository memberRepository;
    private MemberMapper memberMapper;

    public MemberService(MemberRepository memberRepository, MemberMapper memberMapper){
        this.memberRepository = memberRepository;
    }

    public MemberDto createMember(CreateMemberDTO createMemberDTO) {
        Member memberToAdd = memberMapper.mapToEntity(createMemberDTO);
        return memberMapper.mapToDTO(memberRepository.addMember(memberToAdd));
    }
}
