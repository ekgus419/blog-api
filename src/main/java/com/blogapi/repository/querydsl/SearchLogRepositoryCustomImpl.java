package com.blogapi.repository.querydsl;

import com.blogapi.dto.object.PopularSearchListItem;
import com.blogapi.dto.object.RelationSearchListItem;
import com.blogapi.entity.QSearchLogEntity;
import com.blogapi.entity.SearchLogEntity;
import com.querydsl.core.types.Projections;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class SearchLogRepositoryCustomImpl extends QuerydslRepositorySupport implements SearchLogRepositoryCustom {
    public SearchLogRepositoryCustomImpl() {
        super(SearchLogEntity.class);
    }

    @Override
    public List<PopularSearchListItem> getPopularList() {
        QSearchLogEntity searchLog = QSearchLogEntity.searchLogEntity;

        return from(searchLog)
                .where(searchLog.relation.isFalse())
                .groupBy(searchLog.searchWord)
                .orderBy(searchLog.searchWord.count().desc())
                .limit(15)
                .select(Projections.bean(PopularSearchListItem.class,
                        searchLog.searchWord.count().as("count"),
                        searchLog.searchWord.as("searchWord")
                ))
                .fetch();
    }

    @Override
    public List<RelationSearchListItem> getRelationList(String searchWord) {
        QSearchLogEntity searchLog = QSearchLogEntity.searchLogEntity;

        return from(searchLog)
                .where(searchLog.searchWord.eq(searchWord)
                        .and(searchLog.relationWord.isNotNull()))
                .groupBy(searchLog.relationWord)
                .orderBy(searchLog.relationWord.count().desc())
                .limit(15)
                .select(Projections.bean(RelationSearchListItem.class,
                        searchLog.relationWord.count().as("count"),
                        searchLog.relationWord.as("searchWord")
                ))
                .fetch();
    }
}
