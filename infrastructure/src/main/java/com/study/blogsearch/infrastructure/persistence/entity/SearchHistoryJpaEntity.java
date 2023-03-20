package com.study.blogsearch.infrastructure.persistence.entity;

import com.study.blogsearch.domain.entity.SearchHistory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@ToString
@Table(name = "search_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SearchHistoryJpaEntity {
    @EmbeddedId
    private KeywordDateKey id;

    @Column(name = "search_count", nullable = false)
    @Builder.Default
    private Long count = 0L;

    @Column(name = "last_updated")
    @Builder.Default
    private LocalDateTime lastUpdated = LocalDateTime.now();

    public static SearchHistoryJpaEntity fromDomainEntity(SearchHistory searchHistory) {
        KeywordDateKey keywordDateKey = new KeywordDateKey(searchHistory.getKeyword(), searchHistory.getDate());
        return SearchHistoryJpaEntity.builder()
                .id(keywordDateKey)
                .count(searchHistory.getCount())
                .lastUpdated(LocalDateTime.now())
                .build();
    }

    public SearchHistory toDomainEntity() {
        return SearchHistory.builder()
                .keyword(this.id.getKeyword())
                .date(this.id.getDate())
                .count(this.count)
                .build();
    }
}
