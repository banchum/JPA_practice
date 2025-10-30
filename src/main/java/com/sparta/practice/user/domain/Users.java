package com.sparta.practice.user.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Builder
@Data
@Entity
@Table(name="P_USER", indexes = @Index(name="idx_user_name", columnList = "user_name, createdAt DESC"))
@EntityListeners(AuditingEntityListener.class) // 리스너
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    // 데이터를 관리하려면 데이터의 구조가 필요하고 그 구조를 엔티티라고 한다.
    // 엔티티를 정의하려면 기본키가 있어야함
    @Id
    //@GeneratedValue(strategy = GenerationType.UUID)
    //private UUID id;
    @GeneratedValue
    private long id;

    @Column(name="user_name", length = 50, nullable = false)
    private String name;

    //@Transient // 엔티티 내에서만 필요한 항목이고 테이블에는 반영되지 않음
    @Column(length = 50, unique = true, nullable = false) // 기본키와 유니크의 차이 null은 중복 가능하다.
    private String email;

    @Lob
    private String introduction;

    @Enumerated(EnumType.STRING) // 디폴트로는 0,1,2 등으로 사용하지만 순서가 바뀌면 해당 매핑 번호도 바뀌므로 해당 옵션을 선택
    private UserRole role;

    //@CreationTimestamp
    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    //@UpdateTimestamp
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime modifiedAt;

    @Temporal(TemporalType.DATE)
    private Date birthDate;

    // GeneratedValue를 커스텀하려면 엔티티 영역에 메서드 추가
}
