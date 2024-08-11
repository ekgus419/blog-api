package com.blogapi.repository;

import com.blogapi.entity.SearchLogEntity;
import com.blogapi.repository.querydsl.SearchLogRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface SearchLogRepository extends JpaRepository<SearchLogEntity, Integer>,
        SearchLogRepositoryCustom,
        QuerydslPredicateExecutor<SearchLogEntity> {
}
