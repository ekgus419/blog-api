package com.blogapi.controller;

import com.blogapi.dto.request.auth.LoginRequestDto;
import com.blogapi.dto.request.auth.JoinUpRequestDto;
import com.blogapi.dto.response.auth.LoginResponseDto;
import com.blogapi.dto.response.auth.JoinUpResponseDto;
import com.blogapi.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    // 회원가입
    @PostMapping("/join-up")
    public ResponseEntity<? super JoinUpResponseDto> joinUp(@RequestBody @Valid JoinUpRequestDto requestBody) {

        return authService.joinUp(requestBody);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<? super LoginResponseDto> login(@RequestBody @Valid LoginRequestDto requestBody) {
        return authService.login(requestBody);
    }

}
