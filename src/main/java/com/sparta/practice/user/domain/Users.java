package com.sparta.practice.user.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Users {
    // 데이터를 관리하려면 데이터의 구조가 필요하고 그 구조를 엔티티라고 한다.
    // 엔티티를 정의하려면 기본키가 있어야함
    @Id
    private Long id;
    private String name;
    private String email;
    private String password;

}
