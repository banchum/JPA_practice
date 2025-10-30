package com.sparta.practice.jpa1;

import com.sparta.practice.user.domain.Users;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test") // H2
@Transactional
public class Ex02 {
    @PersistenceContext
    EntityManager em;

    @BeforeEach
    void init() {
        Users user = new Users();
        //user.setId(1L);
        user.setName("사용자01");
        user.setEmail("user01test.org");

        em.persist(user);
        em.flush();
        System.out.println(user);
        //em.clear(); // 영속성 컨텍스트에서 모든 엔티티를 비우기
    }

    @Test
    @DisplayName("회원번호가 1인 회원을 조회, 캐시로 동작하는지")
    void test1(){
//        Users user = em.find(Users.class, 1L);
//        System.out.println(user);
//
//        Users user1 = em.find(Users.class, 1L);
//        System.out.println(user1);
//        System.out.println(user == user1); //  같은 객체임
    }
}
