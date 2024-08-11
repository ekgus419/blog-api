package com.blogapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity(name = "board_list_view")
@Table(name = "board_list_view")
public class BoardListViewEntity {

    @Id
    private int boardNumber; // 게시판 번호 (기본 키)

    private String title; // 게시글 제목
    private String content; // 게시글 내용
    private String titleImage; // 대표 이미지

    private int viewCount; // 조회수
    private int favoriteCount; // 좋아요수
    private int commentCount; // 댓글수

    private LocalDateTime writeDatetime; // 작성 날짜 및 시간
    private String writerEmail; // 작성자의 이메일
    private String writerNickname; // 작성자의 닉네임
    private String writerProfileImage; // 작성자의 프로필 이미지

}

