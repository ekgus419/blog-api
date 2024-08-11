package com.blogapi.service;

import com.blogapi.dto.response.search.GetPopularListResponseDto;
import com.blogapi.dto.response.search.GetRelationListResponseDto;
import org.springframework.http.ResponseEntity;

public interface SearchService {

    // 인기 검색어 리스트
    ResponseEntity<? super GetPopularListResponseDto> getPopularList();

    // 연관 검색어 리스트
    ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord);
}
