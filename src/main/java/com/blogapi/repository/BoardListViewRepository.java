package com.blogapi.repository;

import com.blogapi.entity.BoardListViewEntity;
import com.blogapi.repository.querydsl.BoardListViewRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardListViewRepository extends JpaRepository<BoardListViewEntity, Integer>,
        BoardListViewRepositoryCustom,
        QuerydslPredicateExecutor<BoardListViewEntity> {

    List<BoardListViewEntity> findByOrderByWriteDatetimeDesc();
    List<BoardListViewEntity> findByWriterEmailOrderByWriteDatetimeDesc(String email);
}