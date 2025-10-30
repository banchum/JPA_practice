package com.sparta.practice.jpa1;

import com.sparta.practice.user.domain.Users;
import com.sparta.practice.user.domain.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@SpringBootTest
@ActiveProfiles("test")
public class Ex04 {
    @Autowired
    UsersRepository repository;

    @BeforeEach
    void init() { // 스트림 콜렉션
        List<Users> items = IntStream.rangeClosed(1, 10)
                .mapToObj( i -> Users.builder()
                        .email("user"+i+"@test.org")
                        .name("사용자"+i)
                        .build())
                .toList();

        repository.saveAllAndFlush(items); // repository 사용
    }

    @Test
    void test1(){
        List<Users> users = repository.findAll(Sort.by(Sort.Order.desc("createdAt")));
        users.forEach(System.out::println);

        repository.deleteAll(users);
        repository.flush();

        //repository.find
    }

    @Test
    void test2(){
        Users users = repository.findByEmail("user2@test.org").orElseThrow(RuntimeException::new); // 옵셔널 형태로 진행
        System.out.println(users);
    }

}
