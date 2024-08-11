package com.blogapi.repository.querydsl;

import com.blogapi.dto.object.QCommentListItem;
import com.blogapi.entity.BoardListViewEntity;
import com.blogapi.entity.QBoardListViewEntity;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BoardListViewRepositoryCustomImpl extends QuerydslRepositorySupport implements  BoardListViewRepositoryCustom{

    public BoardListViewRepositoryCustomImpl() {
        super(BoardListViewEntity.class);
    }

    /**
     * 주간 상위 3 게시물 리스트
     * SELECT *
     * FROM board_list_view
     * WHERE write_datetime BETWEEN '2024-07-23 20:54' AND '2024-07-29 20:54'
     * ORDER BY favorite_count DESC, comment_count DESC, view_count DESC, write_datetime DESC
     * LIMIT 3;
     */
    @Override
    public List<BoardListViewEntity> getTop3BoardList(LocalDateTime nowDatetime) {
        QBoardListViewEntity boardListView = QBoardListViewEntity.boardListViewEntity;

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime oneWeekAgo = now.minus(1, ChronoUnit.WEEKS);

        return from(boardListView)
                .where(boardListView.writeDatetime.between(oneWeekAgo, nowDatetime))
                .orderBy(boardListView.favoriteCount.desc(),
                        boardListView.commentCount.desc(),
                        boardListView.viewCount.desc(),
                        boardListView.writeDatetime.desc())
                .limit(3)
                .fetch();
    }

    /**
     * 검색어 리스트 불러오기
     * SELECT *
     * FROM board_list_view
     * WHERE title LIKE '%수정%' OR content LIKE '%수정%'
     * ORDER BY write_datetime DESC;
     */
    @Override
    public List<BoardListViewEntity> getSearchBoardList(String searchWord) {
        QBoardListViewEntity boardListView = QBoardListViewEntity.boardListViewEntity;
        BooleanExpression titleContains = boardListView.title.contains(searchWord);
        BooleanExpression contentContains = boardListView.content.contains(searchWord);

        return from(boardListView)
                .where(titleContains.or(contentContains))
                .orderBy(boardListView.writeDatetime.desc())
                .fetch();
    }


}
