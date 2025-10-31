package com.sparta.practice.jpa1;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.Tuple;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.practice.user.domain.QUsers;
import com.sparta.practice.user.domain.Users;
import com.sparta.practice.user.domain.UsersRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.springframework.data.domain.Sort.Order.desc;

@SpringBootTest
@ActiveProfiles("test")
public class Ex04 {
    @Autowired
    UsersRepository repository;

    @Autowired
    JPAQueryFactory queryFactory;

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
        List<Users> users = repository.findAll(Sort.by(desc("createdAt")));
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

    @Test
    void test3(){
        List<Users> items = repository.findByEmailContainingAndNameContainingOrderByEmailDesc("user","사용자");
        System.out.println("test 쿼리");
        items.forEach(System.out::println);
    }

    @Test
    void test4(){
        List<Users> items = repository.getList("%user%","%사용자%");
        System.out.println("test 쿼리");
        items.forEach(System.out::println);
    }

    @Test
    void test5(){ // querydsl 사용
        QUsers users = QUsers.users;

        List<Users> items = (List<Users>) repository.findAll(users.email.contains("user"), Sort.by(desc("email")));
        items.forEach(System.out::println);
    }

    @Test
    void test6(){ // querydsl 사용
        QUsers users = QUsers.users;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(users.email.contains("user"))
                .and(users.name.contains("사용자"));

        List<Users> items = (List<Users>)repository.findAll(builder,users.email.desc());
        items.forEach(System.out::println);
    }

    @Test
    void test7(){ // querydsl 사용 predicate 구현
        QUsers users = QUsers.users;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(users.email.contains("user"))
                .and(users.name.contains("사용자"));

        List<Users> items = queryFactory.selectFrom(users)
                .where(builder)
                .orderBy(users.email.desc())
                .offset(0)
                .limit(3)
                .fetch();
        items.forEach(System.out::println);
    }

    @Test
    void test8(){
        QUsers users = QUsers.users;
        BooleanBuilder builder = new BooleanBuilder();
        builder.and(users.email.contains("user"))
                .and(users.name.contains("사용자"));

        List<Tuple> items = queryFactory.selectDistinct(users.email, users.name).from(users).fetch();
        items.forEach(t->System.out.printf("%s, %s%n", t.get(users.email), t.get(users.name)));
        System.out.println(items);
    }

    @Test
    void test9(){
        QUsers users = QUsers.users;
        Long total = queryFactory.select(users.count()).from(users).fetchFirst(); // 통계 내는 함수 있음
        System.out.println(total);
    }

    @Test
    void test10(){ //페이징
        Pageable pageable = PageRequest.of(1, 3, Sort.by(desc("createdAt")));
        //Page<Users> data = repository.findByEmailContainingAndNameContaining("user", "사용자", pageable);
        Page<Users> data = repository.findAll(pageable);
        List<Users> items = data.getContent();
        items.forEach(System.out::println);
        System.out.printf("총 갯수:%d, 총 페이지수: %d%n", data.getTotalElements(), data.getTotalPages());
    }

}
