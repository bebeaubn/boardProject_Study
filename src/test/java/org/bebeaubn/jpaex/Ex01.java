package org.bebeaubn.jpaex;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.bebeaubn.commons.constants.MemberType;
import org.bebeaubn.entities.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
//@TestPropertySource(activeProfiles = "test")
public class Ex01 {

    @PersistenceContext
    private EntityManager em;

    @BeforeEach
    void init(){
        Member member = new Member();
        member.setUserNo(1L);
        member.setEmail("user01@test.org");
        member.setUserNm("사용자01");
        member.setPassword("123456");
        member.setMobile("01010000000");
        member.setMtype(MemberType.USER);

        em.persist(member);  //변화감시상태
        em.flush();
        em.clear(); // 영속성 비우기


    }
    @Test
    void test2(){
        Member member = em.find(Member.class, 1L);  // DB->영속성 컨텍스트로 추가
        System.out.println(member);

        Member member2 = em.find(Member.class, 1L);  //영속성 컨텍스트 -> 조회
        System.out.println(member2);

        TypedQuery<Member> query = em.createQuery("SELECT m FROM Member AS m WHERE m.email LIKE :key" , Member.class);
        query.setParameter("key", "%user%");
        Member member3 = query.getSingleResult();

        member3.setUserNm("(수정)사용자01");
        em.flush();

    }



    @Test
    void test1(){
        Member member = new Member();
                member.setUserNo(1L);
                member.setEmail("user01@test.org");
                member.setUserNm("사용자01");
                member.setPassword("123456");
                member.setMobile("01010000000");
                member.setMtype(MemberType.USER);



                em.persist(member);  //변화감시상태
                em.flush();

                em.detach(member); //영속성 분리 , 변화감지 x

                member.setUserNm("(수정)사용자01");
                em.flush();

                em.merge(member); //분리된 영속상태 -> 영속상태, 변화감지 O
                em.flush();

               //em.remove(member);
                em.flush();



    }



}
