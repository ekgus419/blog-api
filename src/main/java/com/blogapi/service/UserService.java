package com.blogapi.service;

import com.blogapi.dto.request.user.PatchNicknameRequestDto;
import com.blogapi.dto.request.user.PatchProfileImageRequestDto;
import com.blogapi.dto.response.user.GetLoginUserResponseDto;
import com.blogapi.dto.response.user.GetUserProfileResponseDto;
import com.blogapi.dto.response.user.PatchNicknameResponseDto;
import com.blogapi.dto.response.user.PatchProfileImageResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {

    // 회원 정보 조회
    ResponseEntity<? super GetUserProfileResponseDto> getUserProfile(String email);

    // 로그인 회원 정보 조회
    ResponseEntity<? super GetLoginUserResponseDto> getLoginUser(String email);

    // 닉네임 수정
    ResponseEntity<? super PatchNicknameResponseDto> patchNickname(PatchNicknameRequestDto dto, String email);

    // 프로필 이미지 수정
    ResponseEntity<? super PatchProfileImageResponseDto> patchProfileImage(PatchProfileImageRequestDto dto, String email);
}
