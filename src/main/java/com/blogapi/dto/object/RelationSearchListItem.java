package com.blogapi.dto.object;

import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RelationSearchListItem {
    private Long count;
    private String searchWord;

    @QueryProjection
    public RelationSearchListItem(Long count, String searchWord) {
        this.count = count;
        this.searchWord = searchWord;
    }
}
