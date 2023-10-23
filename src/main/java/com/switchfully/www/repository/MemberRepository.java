package com.switchfully.www.repository;

import com.switchfully.www.domain.member.Address;
import com.switchfully.www.domain.member.City;
import com.switchfully.www.domain.member.Member;
import com.switchfully.www.domain.security.UserRole;
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
        this.createFirstAdmin();
    }

    public Member addMember(Member member) {
        if (memberById.containsValue(member)) {
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

    public Optional<Member> getByEmail(String email) {
        return memberById
                .values()
                .stream()
                .filter(member -> member.getEmail().equals(email))
                .findFirst();
    }


    private void createFirstAdmin() {
        Member admin = new Member("1", "admin", "admin", "admin@test.com", "12346", new Address("Stationsstraat", "80", new City("8000", "Brugge")), UserRole.ADMIN);
        this.memberById.put(admin.getId(), admin);
    }

}