package com.sparta.practice.test1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class Ex1 {

    @Test
    @DisplayName("계산기의 더하기 기능 테스트")
    void calculateTest() {
        Calculator calculator = new Calculator();
        int result = calculator.add(100, 2);
        System.out.println(result);
        assertEquals(102, result);
    }

    void sameEqualTest() {
        LocalDate date1 = LocalDate.of(2025, 12, 25);
        LocalDate date2 = LocalDate.of(2025, 12, 25);

        System.out.println(date1 == date2); // 주소비교, 동일성 비교
        System.out.printf("%s, %s, %s%n", date1.equals(date2),date1.hashCode(), date2.hashCode()); // 동등성 비교

        assertEquals(date1, date2); // 동등성 비교, equals and hashCode()
        assertSame(date1, date2); // 동일성 비교, 주소비교
    }

    @Test
    void loginValidationTest() {
        LoginService loginService = new LoginService();

        BadRequestException thrown = assertThrows(BadRequestException.class, () -> {
            loginService.process("",null);
        });

        //필드명에 맞는 예외가 발생했는지 추가 검증
        String message = thrown.getMessage();
        assertTrue(message.contains("이메일"));
        System.out.println(message);
    }
}
