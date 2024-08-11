package com.blogapi.repository.querydsl;

import com.blogapi.entity.BoardListViewEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface BoardListViewRepositoryCustom {

    List<BoardListViewEntity> getTop3BoardList(LocalDateTime nowDatetime);

    List<BoardListViewEntity> getSearchBoardList(String searchWord);

}
