package com.blogapi.entity;

import com.blogapi.dto.request.board.PatchBoardRequestDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "board")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BoardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int boardNumber; // 게시판 번호 (기본 키, 자동 생성)

    @Column(nullable = false)
    private String title; // 게시글 제목

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; // 게시글 내용

    @Column(nullable = false)
    private LocalDateTime writeDatetime; // 작성 날짜 및 시간

    @Column(nullable = false)
    private int favoriteCount; // 좋아요 수

    @Column(nullable = false)
    private int commentCount; // 댓글 수

    @Column(nullable = false)
    private int viewCount; // 조회 수

    // 여러 명의 유저가 게시글을 작성할 수 있음 (Many-to-One 관계)
    @ManyToOne
    @JoinColumn(name = "writer_email", referencedColumnName = "email")
    private UserEntity writerEmail; // 작성자의 이메일 (외래 키)

    // 팩토리 메소드 - BoardEntity 생성 로직을 캡슐화
    public static BoardEntity create(String title, String content, UserEntity writerEmail) {
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.title = title;
        boardEntity.content = content;
        boardEntity.writeDatetime = LocalDateTime.now();
        boardEntity.favoriteCount = 0;
        boardEntity.commentCount = 0;
        boardEntity.viewCount = 0;
        boardEntity.writerEmail = writerEmail;
        return boardEntity;
    }

    // 조회수 증가
    public void increaseViewCount() {
        this.viewCount++;
    }

    // 좋아요수 증가
    public void increaseFavoriteCount() {
        this.favoriteCount++;
    }

    // 좋아요수 감소
    public void decreaseFavoriteCount() {
        this.favoriteCount--;
    }

    // 댓글수 증가
    public void increaseCommentCount() {
        this.commentCount++;
    }

    // 게시글 수정
    public void patchBoard(PatchBoardRequestDto dto) {
        this.title = dto.getTitle();
        this.content = dto.getContent();
    }
}

