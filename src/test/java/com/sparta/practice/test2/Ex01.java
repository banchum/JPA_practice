package com.sparta.practice.test2;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

@SpringBootTest
@ActiveProfiles("test")
public class Ex01 {

    @Test
    void ProductRecommendServiceTest(){
        RecSystemClient client = mock(RecSystemClient.class);
        given(client.getProducts()).willReturn(List.of(1,2,3,4));

        ProductService productService = new ProductService(client);
        productService.process();
    }
}
