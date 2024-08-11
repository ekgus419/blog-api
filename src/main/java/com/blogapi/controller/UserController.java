package com.blogapi.controller;

import com.blogapi.dto.request.user.PatchNicknameRequestDto;
import com.blogapi.dto.request.user.PatchProfileImageRequestDto;
import com.blogapi.dto.response.user.GetLoginUserResponseDto;
import com.blogapi.dto.response.user.GetUserProfileResponseDto;
import com.blogapi.dto.response.user.PatchNicknameResponseDto;
import com.blogapi.dto.response.user.PatchProfileImageResponseDto;
import com.blogapi.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 로그인 유저 정보 가져오기
    @GetMapping("")
    public ResponseEntity<? super GetLoginUserResponseDto> getLoginUser(
            @AuthenticationPrincipal String email
    ) {
        return userService.getLoginUser(email);
    }

    // 특정 회원 정보 가져오기
    @GetMapping("/{email}")
    public ResponseEntity<? super GetUserProfileResponseDto> getUserProfile(
            @PathVariable("email") String email
    ) {
        return userService.getUserProfile(email);
    }

    // 닉네임 수정하기
    @PatchMapping("/nickname")
    public ResponseEntity<? super PatchNicknameResponseDto> patchNickname(
            @RequestBody @Valid PatchNicknameRequestDto requestBody,
            @AuthenticationPrincipal String email
    ) {
        return userService.patchNickname(requestBody, email);
    }

    // 프로필 이미지 수정하기
    @PatchMapping("/profile-image")
    public ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(
            @RequestBody @Valid PatchProfileImageRequestDto requestBody,
            @AuthenticationPrincipal String email
    ) {
        return userService.patchProfileImage(requestBody, email);
    }
}

