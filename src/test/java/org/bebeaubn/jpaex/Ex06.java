package org.bebeaubn.jpaex;


import org.bebeaubn.commons.constants.MemberType;
import org.bebeaubn.entities.BoardData;
import org.bebeaubn.entities.Member;
import org.bebeaubn.repositories.BoardDataRepository;
import org.bebeaubn.repositories.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
    @TestPropertySource(properties = "spring.profiles.active=test")
    public class Ex06 {
        @Autowired
        private MemberRepository memberRepository;

        @Autowired
        private BoardDataRepository boardDataRepository;

        @Autowired
        private MemberProfileRepository memberProfileRepository;

        @BeforeEach
        void init() {
            MemberProfile profile = new MemberProfile();
            profile.setImage("이미지 경로...");
            memberProfileRepository.saveAndFlush(profile);

            Member member = Member.builder()
                    .email("user01@test.org")
                    .password("123456")
                    .userNm("사용자01")
                    .mtype(MemberType.USER)
                    .profile(profile)
                    .build();

            memberRepository.saveAndFlush(member);

            List<BoardData> items = new ArrayList<>();
            for (int i = 1; i <= 10; i++) {
                BoardData item = BoardData.builder()
                        .subject("제목" + i)
                        .content("내용" + i)
                        .member(member)
                        .build();
                items.add(item);
            }

            boardDataRepository.saveAllAndFlush(items);
        }

        @Test
        void test1() {
            BoardData data = boardDataRepository.findById(1L).orElse(null);
            //Member member = data.getMember();
            //System.out.println(member);
        }

        @Test
        void test2() {
            Member member = memberRepository.findById(1L).orElse(null);
            List<BoardData> items = member.getItems();
            items.stream().forEach(System.out::println);
        }

        @Test
        void test3() {
            Member member = memberRepository.findById(1L).orElse(null);
            MemberProfile profile = member.getProfile();

            System.out.println(profile);
        }

        @Test
        void test4() {
            MemberProfile profile = memberProfileRepository.findById(1L).orElse(null);
            Member member = profile.getMember();
            System.out.println(member);
        }
    }
}
