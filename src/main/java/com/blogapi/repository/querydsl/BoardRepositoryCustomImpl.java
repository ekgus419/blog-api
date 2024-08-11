package com.blogapi.repository.querydsl;

import com.blogapi.dto.object.BoardListItem;
import com.blogapi.dto.object.QBoardListItem;
import com.blogapi.dto.object.QCommentListItem;
import com.blogapi.entity.BoardEntity;
import com.blogapi.entity.QBoardEntity;
import com.blogapi.entity.QUserEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class BoardRepositoryCustomImpl extends QuerydslRepositorySupport implements BoardRepositoryCustom {
    public BoardRepositoryCustomImpl() {
        super(BoardEntity.class);
    }

    @Override
    public BoardListItem getBoard(Integer boardNumber) {
        QBoardEntity board = QBoardEntity.boardEntity;
        QUserEntity user = QUserEntity.userEntity;
        return from(board)
                .join(board.writerEmail, user)
                .where(board.boardNumber.eq(boardNumber))
                .select(new QBoardListItem(
                        board.boardNumber,
                        board.title,
                        board.content,
                        board.writeDatetime,
                        user.email,
                        user.nickname,
                        user.profileImage
                ))
                .fetchOne();
    }
}
