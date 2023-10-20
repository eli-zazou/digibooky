package com.switchfully.www;

import com.switchfully.www.api.CreateMemberDTO;
import com.switchfully.www.domain.Address;
import com.switchfully.www.domain.City;
import com.switchfully.www.domain.Member;
import com.switchfully.www.domain.UserRole;

public class constTest {

    public static final Address anAdress = new Address("loli", "12B", new City("1200", "Bx"));
    public static final CreateMemberDTO johnDoeCreateMemberDTO = new CreateMemberDTO()
            .setAddress(anAdress)
            .setEmail("test@example.com")
            .setFirstName("John")
            .setPassword("1234")
            .setLastName("Doe")
            .setInss("123456789")
            .setRole(UserRole.MEMBER);

    public static final Member johnDoeMember = new Member(
            "123456789",
            "Doe",
            "John",
            "test@example.com",
            "1234",
            anAdress,
            UserRole.MEMBER);

    public static Member janeDoeMember = new Member(
            "223456789",
            "Doe",
            "Jane",
            "test@example.com",
            "1234",
            anAdress,
            UserRole.MEMBER);
    ;
    public static Member johnDoe2Member = new Member(
            "123456789", // <---------- same INSS as John DOE
            "Doe",
            "John2",
            "john2@example.com",
            "1234",
            anAdress,
            UserRole.MEMBER);
    ;
}
