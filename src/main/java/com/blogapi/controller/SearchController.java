package com.blogapi.controller;

import com.blogapi.dto.response.search.GetPopularListResponseDto;
import com.blogapi.dto.response.search.GetRelationListResponseDto;
import com.blogapi.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    // 인기 검색어 목록 가져오기
    @GetMapping("/popular-list")
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {
        return searchService.getPopularList();
    }

    // 연관 검색어 목록 가져오기
    @GetMapping("/{searchWord}/relation-list")
    public ResponseEntity <? super GetRelationListResponseDto> getRelationList(
            @PathVariable("searchWord") String searchWord
    ) {
        return searchService.getRelationList(searchWord);
    }

}
