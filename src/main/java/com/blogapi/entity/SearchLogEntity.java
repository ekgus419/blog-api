package com.blogapi.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "search_log")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SearchLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sequence; // 검색 기록의 순서를 나타내는 기본 키 (자동 증가)

    @Column(nullable = false)
    private String searchWord; // 사용자가 검색한 단어

    @Column
    private String relationWord; // 연관 검색어 (있을 경우)

    @Column(nullable = false)
    private boolean relation; // 연관 검색어 여부

    // 팩토리 메소드 - SearchLogEntity 생성 로직을 캡슐화
    public static SearchLogEntity create(String searchWord, String relationWord, boolean relation) {
        SearchLogEntity searchLogEntity = new SearchLogEntity();
        searchLogEntity.searchWord = searchWord;
        searchLogEntity.relationWord = relationWord;
        searchLogEntity.relation = relation;
        return searchLogEntity;
    }
}