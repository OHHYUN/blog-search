package com.study.blogsearch.infrastructure.persistence;

import com.study.blogsearch.domain.entity.SearchHistory;
import com.study.blogsearch.domain.repository.SearchHistoryRepository;
import com.study.blogsearch.infrastructure.persistence.entity.KeywordDateKey;
import com.study.blogsearch.infrastructure.persistence.entity.SearchHistoryJpaEntity;
import com.study.blogsearch.infrastructure.persistence.repository.SearchHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SearchHistoryAdapter implements SearchHistoryRepository {

    private final SearchHistoryJpaRepository repository;

    @Override
    @Transactional
    public SearchHistory findAndUpdate(SearchHistory searchHistory) {
        KeywordDateKey id = new KeywordDateKey(searchHistory.getKeyword(), searchHistory.getDate());
        Optional<SearchHistoryJpaEntity> entity = repository.findByIdWithLock(id);
        if(entity.isPresent()) {
            SearchHistoryJpaEntity searchHistoryJpaEntity = entity.get();
            searchHistoryJpaEntity.increaseCount();
            SearchHistoryJpaEntity updatedEntity = repository.save(searchHistoryJpaEntity);
            return updatedEntity.toDomainEntity();
        }
        SearchHistoryJpaEntity newEntity = SearchHistoryJpaEntity.builder()
                .id(id)
                .build();
        return repository.save(newEntity).toDomainEntity();
    }

    @Override
    public List<SearchHistory> findTop10Keyword(LocalDate date) {
        return repository.findTop10ByIdDateOrderByCountDesc(date).stream()
                .map(SearchHistoryJpaEntity::toDomainEntity).collect(Collectors.toList());
    }
}
