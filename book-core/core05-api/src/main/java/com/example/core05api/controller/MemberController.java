package com.example.core05api.controller;

import com.example.core05api.entity.MemberDto;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1")
@RestController
public class MemberController {

    // GET http://localhost:8080/api/v1/member/1?search=ranking
    @GetMapping("/members/{id}")
    public String getMember(@PathVariable long id, @RequestParam String search) {
        System.out.println("id = " + id + ", search = " + search);
        return search + ": 7";
    }

    @PostMapping("/members")
    public MemberDto createMember(@RequestBody MemberDto dto) {
        System.out.println("dto = " + dto);
        return dto;
    }
}
