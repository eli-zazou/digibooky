package com.switchfully.www.repository;

import com.switchfully.www.domain.*;
import com.switchfully.www.exceptions.UnauthorizatedException;
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

    public Member addMember(Member member){
        if(memberById.containsValue(member)){
            throw new IllegalArgumentException("This member already exists.");
        }
        memberById.put(member.getId(), member);
        return member;
    }

    public Member addAdminOrLibrarian(Member User){
        // TODO need to check Inss and throw error if already there.
        if(memberById.containsValue(User)){
            throw new IllegalArgumentException("This member already exists.");
        }
        memberById.put(User.getId(), User);
        return User;

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

    public Member authenticate(String email, String password) {
        return memberById.values().stream().filter(x -> x.getEmail().equals(email) && x.getPassword().equals(password)).findFirst().get();
    }

    private void createFirstAdmin(){
        Address address = new Address("Rue Henri Maus", "25", new City("1000", "Brussels"));
        Member admin = new Member("1", "admin", "admin", "admin@test.com", "12346",address, UserRole.ADMIN);
        this.memberById.put(admin.getId(), admin);
    }

    }