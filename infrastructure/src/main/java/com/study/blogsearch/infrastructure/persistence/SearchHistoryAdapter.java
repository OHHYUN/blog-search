package com.study.blogsearch.infrastructure.persistence;

import com.study.blogsearch.domain.entity.SearchHistory;
import com.study.blogsearch.domain.repository.SearchHistoryRepository;
import com.study.blogsearch.infrastructure.persistence.entity.KeywordDateKey;
import com.study.blogsearch.infrastructure.persistence.entity.SearchHistoryJpaEntity;
import com.study.blogsearch.infrastructure.persistence.repository.SearchHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SearchHistoryAdapter implements SearchHistoryRepository {

    private final SearchHistoryJpaRepository repository;

    @Override
    public SearchHistory saveSearchHistory(SearchHistory searchHistory) {
        return repository.save(SearchHistoryJpaEntity.fromDomainEntity(searchHistory))
                .toDomainEntity();
    }

    @Override
    public SearchHistory findSearchHistory(SearchHistory searchHistory) {
        KeywordDateKey id = new KeywordDateKey(searchHistory.getKeyword(), searchHistory.getDate());
        return repository.findById(id)
                .orElse(SearchHistoryJpaEntity.builder()
                        .id(id)
                        .build())
                .toDomainEntity();
    }

    @Override
    public List<SearchHistory> findTop10Keyword(LocalDate date) {
        return repository.findTop10KeywordByDate(date).stream()
                .map(SearchHistoryJpaEntity::toDomainEntity).collect(Collectors.toList());
    }
}
