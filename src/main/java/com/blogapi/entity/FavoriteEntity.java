package com.blogapi.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite")
@Getter
@NoArgsConstructor
public class FavoriteEntity {

    // 복합키로 사용
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 기본 키 (자동 증가)

    // 여러 유저가 여러 게시글을 좋아할 수 있음 (Many-to-One 관계)
    @ManyToOne
    @JoinColumn(name = "user_email", referencedColumnName = "email")
    private UserEntity userEmail; // 사용자의 이메일 (외래 키)

    // 하나의 게시글에 여러 유저가 좋아요를 누를 수 있음 (Many-to-One 관계)
    @ManyToOne
    @JoinColumn(name = "board_number", referencedColumnName = "boardNumber")
    private BoardEntity boardNumber; // 게시글 번호 (외래 키)

    // 팩토리 메소드 - FavoriteEntity 생성 로직을 캡슐화
    public static FavoriteEntity create(UserEntity userEmail, BoardEntity boardNumber) {
        FavoriteEntity favoriteEntity = new FavoriteEntity();
        favoriteEntity.userEmail = userEmail;
        favoriteEntity.boardNumber = boardNumber;
        return favoriteEntity;
    }
}

