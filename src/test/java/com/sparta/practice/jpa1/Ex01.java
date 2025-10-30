package com.sparta.practice.jpa1;

import com.sparta.practice.user.domain.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class Ex01 {

    @PersistenceContext
    EntityManager em;

    @Test
    @Transactional
    void test1() {
        //과거 사용방식
        //EntityTransaction tx = em.getTransaction();
        //tx.begin();

        // 비영속 - 변화감지 X
        Users user = new Users();
        user.setId(1L);
        user.setName("사용자1");
        user.setEmail("user01@test.org");

        // 영속 - em.persist(...)엔티티의 상태 변화 감지
        // 트랜젝션 단위로 묶어서 실행해야함
        em.persist(user);
        em.flush(); //SQL 실행 INSERT
        //과거 사용방식
        //tx.commit();

        em.detach(user); // 준영속, 변화감지 X 업데이트 쿼리는 실행되지 않을것임

        user.setName("(수정)사용자");
        em.flush();

        user.setEmail("(수정)user01@test.org");
        em.merge(user); // 영속상태, 변화감지 O
        em.flush();

        em.remove(user); //삭제x, 삭제 상태
        em.flush(); // 이때 delete 쿼리가 삭제
    }
}
