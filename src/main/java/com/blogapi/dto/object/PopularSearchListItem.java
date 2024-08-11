package com.blogapi.dto.object;

import com.querydsl.core.annotations.QueryProjection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PopularSearchListItem {
    private Long count;
    private String searchWord;

    @QueryProjection
    public PopularSearchListItem(Long count, String searchWord) {
        this.count = count;
        this.searchWord = searchWord;
    }
}
