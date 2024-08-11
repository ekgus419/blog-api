package com.blogapi.repository.querydsl;

import com.blogapi.dto.object.CommentListItem;
import com.blogapi.dto.object.QCommentListItem;
import com.blogapi.entity.CommentEntity;
import com.blogapi.entity.QCommentEntity;
import com.blogapi.entity.QUserEntity;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class CommentRepositoryCustomImpl extends QuerydslRepositorySupport implements CommentRepositoryCustom {

    public CommentRepositoryCustomImpl() {
        super(CommentEntity.class);
    }

    @Override
    public List<CommentListItem> getCommentList(Integer boardNumber) {
        QCommentEntity comment = QCommentEntity.commentEntity;
        QUserEntity user = QUserEntity.userEntity;

        return from(comment)
                .join(comment.userEmail, user)
                .where(comment.boardNumber.boardNumber.eq(boardNumber))
                .orderBy(comment.writeDatetime.desc())
                .select(new QCommentListItem(
                        user.nickname,
                        user.profileImage,
                        comment.writeDatetime,
                        comment.content
                ))
                .fetch();
    }
}
