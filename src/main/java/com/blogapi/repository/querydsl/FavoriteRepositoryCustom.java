package com.blogapi.repository.querydsl;

import com.blogapi.dto.object.FavoriteListItem;

import java.util.List;

public interface FavoriteRepositoryCustom {

    List<FavoriteListItem> getFavoriteList(Integer boardNumber);
}
