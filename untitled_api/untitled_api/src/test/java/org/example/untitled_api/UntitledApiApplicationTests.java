package org.example.untitled_api;

import org.assertj.core.api.Assertions;
import org.example.untitled_api.domain.Member;
import org.example.untitled_api.repository.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UntitledApiApplicationTests {

    @Autowired
    private final MemberRepository memberRepository;

    public UntitledApiApplicationTests(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Test
    void contextLoads() {
        Member member = new Member();
        member.setName("dante");
        memberRepository.save(member);

        Member findMember = memberRepository.findById(member.getId()).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());


    }

}
