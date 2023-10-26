package com.example.core12rest.service;

import com.example.core12rest.dto.PostDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {
    public PostDto getApi() {
        WebClient webClient = WebClient.create("https://jsonplaceholder.typicode.com");

        return webClient.get()
                .uri(uri -> uri
                        .path("/posts/{id}")
                        .queryParam("limit", 1)    // query 변수
                        .build(1))                   // path 변수
                .retrieve()
                .bodyToMono(PostDto.class)
                .block();
    }

    public PostDto postApi() {
        WebClient webClient = WebClient.builder()
                .baseUrl("https://jsonplaceholder.typicode.com")
                .build();

        PostDto postDto = new PostDto();
        postDto.setTitle("cat");
        postDto.setBody("meow");

        return webClient.post()
                .uri(uri -> uri
                        .path("/posts")
                        .build())
                .bodyValue(postDto)                               // body 변수
                .exchangeToMono(resp -> {
                    if (resp.statusCode().equals(HttpStatus.CREATED)) {
                        return resp.bodyToMono(PostDto.class);
                    } else {
                        return resp.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }
}
