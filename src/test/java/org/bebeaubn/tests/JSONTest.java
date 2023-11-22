package org.bebeaubn.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.bebeaubn.entities.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class JSONTest {

    private  ObjectMapper om;

    @BeforeEach
    void init(){
        om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());

    }


    @Test
    void test() throws JsonProcessingException {
      Member member = Member.builder()
                .email("user01@test.org")
                .password("123456")
                .userNm("사용자01")
                .build();
        member.setCreatedAt(LocalDateTime.now());

        String json = om.writeValueAsString(member);
        System.out.println(json);

        Member member2 = om.readValue(json, Member.class);
        System.out.println(member2);
    }
    @Test
     void test2() throws JsonProcessingException {
        List<Member> members = new ArrayList<>();
        for(int i =1; i<=3; i++){
            Member member = Member.builder()
                    .email("user01@test.org")
                    .password("123456")
                    .userNm("사용자01")
                    .build();

            members.add(member);
        }

        String json = om.writeValueAsString(members);
        System.out.println(json);

        List<Member> members2 = om.readValue(json, new TypeReference<List<Member>>() {});

        members2.stream().forEach(System.out::println);
     }



}
