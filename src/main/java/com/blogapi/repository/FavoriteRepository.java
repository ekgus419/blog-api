package com.blogapi.repository;


import com.blogapi.entity.BoardEntity;
import com.blogapi.entity.FavoriteEntity;
import com.blogapi.entity.UserEntity;
import com.blogapi.repository.querydsl.FavoriteRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Integer>,
        FavoriteRepositoryCustom,
        QuerydslPredicateExecutor<FavoriteEntity> {
    
    FavoriteEntity findByBoardNumberAndUserEmail(BoardEntity boardNumber, UserEntity userEmail);

    // 특정 사용자 이메일과 게시글 번호를 기준으로 좋아요 여부를 확인하는 메소드
    boolean existsByUserEmailAndBoardNumber(UserEntity userEmail, BoardEntity boardNumber);

    // 특정 사용자 이메일과 게시글 번호를 기준으로 좋아요 데이터를 삭제하는 메소드
    void deleteByUserEmailAndBoardNumber(UserEntity userEmail, BoardEntity boardNumber);

    // 특정 게시글 번호를 기준으로 모든 좋아요 데이터를 삭제하는 메소드
    @Transactional // 트랜잭션으로 묶어서 처리
    void deleteByBoardNumber_BoardNumber(Integer boardNumber); // 게시글 번호에 해당하는 모든 좋아요 데이터를 삭제
}
