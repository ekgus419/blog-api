package com.blogapi.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentNumber; // 댓글 번호 (기본 키, 자동 생성)

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content; // 댓글 내용

    @Column(nullable = false)
    private LocalDateTime writeDatetime; // 댓글 작성 날짜 및 시간

    // 여러 명의 유저가 댓글을 작성할 수 있음 (Many-to-One 관계)
    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private UserEntity userEmail; // 댓글 작성자의 이메일 (외래 키)

    // 하나의 게시글에 여러 개의 댓글이 달릴 수 있음 (Many-to-One 관계)
    @ManyToOne
    @JoinColumn(name = "board_number", referencedColumnName = "boardNumber")
    private BoardEntity boardNumber; // 게시글 번호 (외래 키)

    // 팩토리 메소드 - CommentEntity 생성 로직을 캡슐화
    public static CommentEntity create(BoardEntity boardNumber, String content, UserEntity userEmail) {
        CommentEntity commentEntity = new CommentEntity();
        commentEntity.boardNumber = boardNumber;
        commentEntity.content = content;
        commentEntity.writeDatetime = LocalDateTime.now();
        commentEntity.userEmail = userEmail;
        return commentEntity;
    }
}

