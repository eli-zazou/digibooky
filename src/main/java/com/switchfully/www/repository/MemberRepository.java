package com.switchfully.www.repository;

import com.switchfully.www.domain.Member;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
public class MemberRepository {
    private final Map<String, Member> memberById;

    public MemberRepository() {
        this.memberById = new HashMap<>();
    }

    public Member addMember(Member member){
        // TODO need to check Inss and throw error if already there.
        if(memberById.containsValue(member)){
            throw new IllegalArgumentException("This member already exists.");
        }
        memberById.put(member.getId(), member);
        return member;
    }
    public Collection<Member> getAllMember() {
        return memberById.values();
    }
    public Optional<Member> getMemberById(String id) {
        return Optional.ofNullable(memberById.get(id));
    }

    }