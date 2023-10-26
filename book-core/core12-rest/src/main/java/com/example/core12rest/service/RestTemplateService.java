package com.example.core12rest.service;

import com.example.core12rest.dto.PostDto;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {
    public PostDto getApi() {
        URI uri = UriComponentsBuilder
                .fromUriString("https://jsonplaceholder.typicode.com")
                .path("/posts/{id}")
                .queryParam("limit", 1)     // query 변수
                .encode()
                .build(1);                    // path 변수

        RestTemplate restTemplate = new RestTemplate();

        // return restTemplate.getForEntity(uri, PostDto.class).getBody();
        RequestEntity<Void> requestEntity = RequestEntity.get(uri).build();
        return restTemplate.exchange(requestEntity, PostDto.class).getBody();
    }

    public PostDto postApi() {
        URI uri = UriComponentsBuilder
                .fromUriString("https://jsonplaceholder.typicode.com")
                .path("/posts")
                .encode()
                .build().toUri();

        PostDto postDto = new PostDto();
        postDto.setTitle("cat");
        postDto.setBody("meow");

        RestTemplate restTemplate = new RestTemplate();

        // return restTemplate.postForEntity(uri, postDto, PostDto.class).getBody();
        RequestEntity<PostDto> requestEntity = RequestEntity.post(uri).body(postDto);  // body 변수
        return restTemplate.exchange(requestEntity, PostDto.class).getBody();
    }
}
