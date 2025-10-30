package com.sparta.practice.user.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name="P_USER", indexes = @Index(name="idx_user_name", columnList = "name, createdAt DESC"))
public class Users {
    // 데이터를 관리하려면 데이터의 구조가 필요하고 그 구조를 엔티티라고 한다.
    // 엔티티를 정의하려면 기본키가 있어야함
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String email;
    private String introduction;
    private LocalDate createdAt;

    // GeneratedValue를 커스텀하려면 엔티티 영역에 메서드 추가
}
