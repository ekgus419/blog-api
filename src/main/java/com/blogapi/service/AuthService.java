package com.blogapi.service;

import com.blogapi.dto.request.auth.LoginRequestDto;
import com.blogapi.dto.request.auth.JoinUpRequestDto;
import com.blogapi.dto.response.auth.LoginResponseDto;
import com.blogapi.dto.response.auth.JoinUpResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    // 회원가입
    ResponseEntity<? super JoinUpResponseDto> joinUp(JoinUpRequestDto dto);
    
    // 로그인
    ResponseEntity<? super LoginResponseDto> login(LoginRequestDto dto);

}
