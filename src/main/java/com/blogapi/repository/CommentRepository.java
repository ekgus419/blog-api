package com.blogapi.repository;


import com.blogapi.entity.CommentEntity;
import com.blogapi.repository.querydsl.CommentRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CommentRepository extends
        JpaRepository<CommentEntity, Integer>, CommentRepositoryCustom,
        QuerydslPredicateExecutor<CommentEntity> {

    // 특정 게시글 번호를 기준으로 모든 댓글을 삭제하는 메소드
    @Transactional
    void deleteByBoardNumber_BoardNumber(Integer boardNumber);

}