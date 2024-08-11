package com.blogapi.repository;


import com.blogapi.entity.ImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ImageRepository extends JpaRepository<ImageEntity, Integer> {

    // 특정 게시글 번호를 기준으로 모든 이미지를 조회하는 메소드
    List<ImageEntity> findByBoardNumber_BoardNumber(Integer boardNumber);

    // 특정 게시글 번호를 기준으로 모든 이미지를 삭제하는 메소드
    @Transactional
    void deleteByBoardNumber_BoardNumber(Integer boardNumber); // 게시글 번호에 해당하는 모든 이미지를 삭제
}
