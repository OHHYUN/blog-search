package com.study.blogsearch.infrastructure.persistence.entity;

import com.study.blogsearch.domain.entity.SearchHistory;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@ToString
@Table(name = "search_history")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@EqualsAndHashCode(of = "id")
public class SearchHistoryJpaEntity {

    @Column(name = "keyword")
    @Id
    private String id;

    @Column(name = "search_count", nullable = false)
    @Builder.Default
    private Long count = 1L;

    @Column(name = "last_updated")
    @Builder.Default
    private LocalDateTime lastUpdated = LocalDateTime.now();

    public void increaseCount() {
        this.count++;
    }

    public static SearchHistoryJpaEntity fromDomainEntity(SearchHistory searchHistory) {
        return SearchHistoryJpaEntity.builder()
                .id(searchHistory.getKeyword())
                .count(searchHistory.getCount())
                .lastUpdated(LocalDateTime.now())
                .build();
    }

    public SearchHistory toDomainEntity() {
        return SearchHistory.builder()
                .keyword(this.id)
                .count(this.count)
                .build();
    }
}
