package com.blogapi.repository.querydsl;

import com.blogapi.dto.object.CommentListItem;

import java.util.List;

public interface CommentRepositoryCustom {

    List<CommentListItem> getCommentList(Integer boardNumber);
}
