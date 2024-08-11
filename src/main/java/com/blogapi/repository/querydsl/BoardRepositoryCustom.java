package com.blogapi.repository.querydsl;

import com.blogapi.dto.object.BoardListItem;

import java.util.List;

public interface BoardRepositoryCustom {
    BoardListItem getBoard(Integer boardNumber);
}
