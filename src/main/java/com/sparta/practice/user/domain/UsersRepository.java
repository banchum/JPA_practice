package com.sparta.practice.user.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;

import javax.swing.text.html.Option;

import java.util.List;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long>, QuerydslPredicateExecutor<Users> {
    // 스프링 IOC 컨테이너
    // 이게 있으면 DB 기술을 쓸 수 있다

    Optional <Users> findByEmail(String email);

    List<Users> findByEmailContainingAndNameContainingOrderByEmailDesc(String key1, String ket2);

    @Query("SELECT u FROM Users u WHERE u.email LIKE :k1 AND u.name LIKE :k2 ORDER BY u.email DESC") //JPQL
    List<Users> getList(@Param("k1") String key1, @Param("k2") String ket2);

    //List<Users> findByEmailContainingAndNameContaining(String key1, String ket2, Pageable pageable);//페이징
    Page<Users> findByEmailContainingAndNameContaining(String key1, String ket2, Pageable pageable);// 페이지형식으로 바뀜 -> 전체 카운트한번 하고 데이터 가져옴
}
