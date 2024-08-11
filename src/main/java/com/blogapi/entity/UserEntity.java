package com.blogapi.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserEntity {

    @Id
    @Column(length = 50)
    private String email; // 이메일은 User 엔티티의 기본 키로 사용됩니다.

    @Column(nullable = false)
    private String password; // 사용자의 비밀번호

    @Column(nullable = false, length = 20)
    private String nickname; // 사용자의 닉네임

    @Column(length = 15)
    private String telNumber; // 사용자의 휴대폰 번호

    @Lob
    @Column(columnDefinition = "TEXT")
    private String address; // 사용자의 주소

    @Lob
    @Column(columnDefinition = "TEXT")
    private String addressDetail; // 사용자의 상세 주소

    @Lob
    @Column(columnDefinition = "TEXT")
    private String profileImage; // 사용자의 프로필 이미지 URL

    @Column(nullable = false)
    private Boolean agreedPersonal; // 개인정보 동의 여부

    // 팩토리 메소드 - UserEntity 생성 로직을 캡슐화
    public static UserEntity create(String email, String password, String nickname, String telNumber,
                                String address, String addressDetail, Boolean agreedPersonal) {
        UserEntity userEntity = new UserEntity();
        userEntity.email = email;
        userEntity.password = password;
        userEntity.nickname = nickname;
        userEntity.telNumber = telNumber;
        userEntity.address = address;
        userEntity.addressDetail = addressDetail;
        userEntity.profileImage = null;
        userEntity.agreedPersonal = agreedPersonal;
        return userEntity;
    }

    // 닉네임 수정
    public void patchNickname(String newNickname) {
        this.nickname = newNickname;
    }

    public void patchProfileImage(String newProfileImage) {
        this.profileImage = newProfileImage;
    }

}

