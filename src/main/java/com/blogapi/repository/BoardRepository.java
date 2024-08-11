package com.blogapi.repository;


import com.blogapi.entity.BoardEntity;
import com.blogapi.repository.querydsl.BoardRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer>,
        BoardRepositoryCustom,
        QuerydslPredicateExecutor<BoardEntity> {

    boolean existsByBoardNumber(Integer boardNumber);

    BoardEntity findByBoardNumber(Integer boardNumber);
    
}
