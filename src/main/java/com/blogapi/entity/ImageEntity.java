package com.blogapi.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence; // 이미지 순서를 나타내는 기본 키 (자동 증가)

    @Column(nullable = false)
    private String image; // 이미지의 URL

    // 하나의 게시글에 여러 이미지를 포함할 수 있음 (Many-to-One 관계)
    @ManyToOne
    @JoinColumn(name = "board_number", referencedColumnName = "boardNumber")
    private BoardEntity boardNumber; // 게시글 번호 (외래 키)

    public static ImageEntity create(BoardEntity boardNumber, String image) {
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.boardNumber = boardNumber;
        imageEntity.image = image;
        return imageEntity;
    }
}
