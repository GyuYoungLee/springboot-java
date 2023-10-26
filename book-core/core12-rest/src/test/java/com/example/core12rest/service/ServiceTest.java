package com.example.core12rest.service;

import com.example.core12rest.dto.PostDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ServiceTest {
    @Autowired
    private RestTemplateService restTemplateService;
    @Autowired
    private WebClientService webClientService;

    @Test
    void testAPI() {
        System.out.println("======================");

        PostDto result1 = restTemplateService.getApi();
        System.out.println("result = " + result1.getTitle());

        PostDto result2 = restTemplateService.postApi();
        System.out.println("result = " + result2.getTitle());

        PostDto result3 = webClientService.getApi();
        System.out.println("result = " + result3.getTitle());

        PostDto result4 = webClientService.postApi();
        System.out.println("result = " + result4.getTitle());

        System.out.println("======================");
    }
}
