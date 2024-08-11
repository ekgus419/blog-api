package com.blogapi.common;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseResult {
    // Success Codes and Messages
    SUCCESS("SU", "정상 처리되었습니다."),

    // Validation Errors
    VALIDATION_FAILED("VF", "유효성 검증에 실패하였습니다."),

    // Duplicate Errors
    DUPLICATE_EMAIL("DE", "이메일이 중복되었습니다."),
    DUPLICATE_NICKNAME("DN", "닉네임이 중복되었습니다."),
    DUPLICATE_TEL_NUMBER("DT", "핸드폰번호가 중복되었습니다."),

    // Not Found Errors
    NOT_EXISTED_USER("NU", "존재하지 않는 회원입니다."),
    NOT_EXISTED_BOARD("NB", "존재하지 않는 게시글입니다."),

    // Authentication Errors
    SIGN_IN_FAIL("SF", "로그인 정보를 확인해주세요."),
    AUTHORIZATION_FAIL("AF", "인증에 실패하였습니다."),

    // Permission Errors
    NO_PERMISSION("NP", "권한이 없습니다."),

    // Database Errors
    DATABASE_ERROR("DBE", "데이터베이스 에러입니다.");

    private final String code;
    private final String message;

}