package com.sparta.practice.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    // 스프링 IOC 컨테이너
    // 이게 있으면 DB 기술을 쓸 수 있다

    Optional <Users> findByEmail(String email);
}
