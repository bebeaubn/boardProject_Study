package org.bebeaubn.jpaex;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.bebeaubn.entities.BoardData;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@TestPropertySource(properties = "spring.profiles.active=test")

public class Ex03 {

    @PersistenceContext
    private EntityManager em;


    @Test
    void test1(){
        BoardData data = BoardData.builder()
                .subject("제목!")
                .content("내용!")
                .build();

        em.persist(data);
        em.flush();


        BoardData data2 =em.find(BoardData.class, data.getSeq());
        System.out.printf("createdAt=%s, modifiedAt=%s%n", data2.getCreatedAt(), data2.getModifiedAt());


        try {
            Thread.sleep(3000);   //3초정도 차이둠
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        data2.setSubject("(수정)제목");
        em.flush();

        data2 =em.find(BoardData.class, data.getSeq());
        System.out.printf("createdAt=%s, modifiedAt=%s%n", data2.getCreatedAt(), data2.getModifiedAt());

    }
}
