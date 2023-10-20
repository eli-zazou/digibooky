package com.switchfully.www.repository;

import com.switchfully.www.constTest;
import com.switchfully.www.domain.Author;
import com.switchfully.www.domain.Book;
import com.switchfully.www.domain.Isbn;
import com.switchfully.www.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.switchfully.www.constTest.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

    private MemberRepository memberRepository;

    @BeforeEach
    void setup() {
        memberRepository = new MemberRepository();
    }

    @Test
    void constructor_thenCreateAdminMember(){
        Assertions.assertThat(memberRepository.getAllMember().size()).isEqualTo(1);
    }

    @Test
    void save_givenAMember_thenMemberIsInRepository(){
        Member memberToSave = johnDoeMember;

        Assertions.assertThat(memberToSave).isEqualTo(memberRepository.addMember(memberToSave));
    }

    @Test
    void save_givenAMemberAlreadyIn_thenAssertIllegalArgumentException(){
        Member memberToSave = johnDoeMember;
        memberRepository.addMember(memberToSave);

        assertThrows(IllegalArgumentException.class, () -> {
            memberRepository.addMember(memberToSave);
        });
    }

    @Test
    void getAllMembers_givenNoMember_thenReturnListWithAdmin(){
        Collection<Member> allMembers = memberRepository.getAllMember();
        Assertions.assertThat(allMembers.size()).isEqualTo(1);
    }

    @Test
    void getAllMembers_givenTwoMembers_thenContainExactlyThoseTwoAndAdmin(){
        Member member1 =  johnDoeMember;
        Member member2 = janeDoeMember;
        memberRepository.addMember(member1);
        memberRepository.addMember(member2);

        Collection<Member> actualMembers = memberRepository.getAllMember();

        Assertions.assertThat(actualMembers).contains(member1, member2);
        Assertions.assertThat(actualMembers.size()).isEqualTo(3);

    }

    @Test
    void createMember_givenTwoDiffMembersSameInss_thenThrowException(){
        Member johnDoe = johnDoeMember;
        Member johnDoe2 = johnDoe2Member;

        memberRepository.addMember(johnDoe);

        assertThrows(IllegalArgumentException.class, () -> {
            memberRepository.addMember(johnDoe2);
        });
    }

    @Test
    void getMemberById_givenNonExistantId_thenReturnOptionalEmpty(){
        String givenId = "duqqhiduqshod";

        Optional<Member> actualOptional = memberRepository.getMemberById(givenId);

        Assertions.assertThat(actualOptional).isEmpty();

    }

    @Test
    void getMemberById_givenExistantId_thenReturnCorrectMember(){
        Member givenMember = johnDoe2Member;
        memberRepository.addMember(givenMember);
        String givenId = givenMember.getId();

        Optional<Member> actualOptional = memberRepository.getMemberById(givenId);

        Assertions.assertThat(actualOptional.get()).isEqualTo(givenMember);

    }

    @Test
    void getMemberByEmail_givenExistingEmail_thenReturnOptional(){
        String givenEmail = "john2@example.com";
        memberRepository.addMember(johnDoe2Member);

        Optional<Member> actualOptional = memberRepository.getByEmail(givenEmail);

        Assertions.assertThat(actualOptional).containsSame(johnDoe2Member);

    }

    @Test
    void getMemberByEmail_givenNonExistingEmail_thenReturnOptionalEmpty(){
        String givenEmail = "john2@example.com";

        Optional<Member> actualOptional = memberRepository.getByEmail(givenEmail);

        Assertions.assertThat(actualOptional).isEmpty();
    }

}