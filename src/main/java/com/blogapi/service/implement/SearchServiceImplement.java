package com.blogapi.service.implement;

import com.blogapi.dto.object.PopularSearchListItem;
import com.blogapi.dto.object.RelationSearchListItem;
import com.blogapi.dto.response.ResponseDto;
import com.blogapi.dto.response.search.GetPopularListResponseDto;
import com.blogapi.dto.response.search.GetRelationListResponseDto;
import com.blogapi.exception.ExceptionHandler;
import com.blogapi.repository.SearchLogRepository;
import com.blogapi.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImplement implements SearchService {

    private final ExceptionHandler exceptionHandler;

    private final SearchLogRepository searchLogRepository;

    @Override
    public ResponseEntity<? super GetPopularListResponseDto> getPopularList() {

        List<PopularSearchListItem> searchListItems;

        try {
            searchListItems = searchLogRepository.getPopularList();
        }  catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }

        return GetPopularListResponseDto.success(searchListItems);
    }

    @Override
    public ResponseEntity<? super GetRelationListResponseDto> getRelationList(String searchWord) {

        List<RelationSearchListItem> searchListItems;

        try {

            searchListItems = searchLogRepository.getRelationList(searchWord);

        }  catch (Exception exception) {
            return exceptionHandler.handleException(exception);
        }

        return GetRelationListResponseDto.success(searchListItems);

    }

}

