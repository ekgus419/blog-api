package com.blogapi.repository.querydsl;

import com.blogapi.dto.object.PopularSearchListItem;
import com.blogapi.dto.object.RelationSearchListItem;

import java.util.List;

public interface SearchLogRepositoryCustom {
    List<PopularSearchListItem> getPopularList();
    List<RelationSearchListItem> getRelationList(String searchWord);
}
