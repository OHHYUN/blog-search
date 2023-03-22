package com.study.blogsearch.infrastructure.persistence;

import com.study.blogsearch.domain.entity.SearchHistory;
import com.study.blogsearch.domain.exception.errorcode.SearchHistoryErrorCode;
import com.study.blogsearch.domain.repository.SearchHistoryRepository;
import com.study.blogsearch.infrastructure.persistence.entity.SearchHistoryJpaEntity;
import com.study.blogsearch.infrastructure.persistence.repository.SearchHistoryJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.study.blogsearch.infrastructure.persistence.util.ExceptionUtils.handleDbExceptions;

@Component
@RequiredArgsConstructor
public class SearchHistoryAdapter implements SearchHistoryRepository {

    private final SearchHistoryJpaRepository repository;

    @Override
    @Transactional
    public SearchHistory findAndUpdate(SearchHistory searchHistory) {
        return handleDbExceptions(() -> performFindAndUpdate(searchHistory), SearchHistoryErrorCode.DATA_UPDATE_ERROR);
    }

    public SearchHistory performFindAndUpdate(SearchHistory searchHistory) {
        Optional<SearchHistoryJpaEntity> foundEntity = repository.findByIdWithLock(searchHistory.getKeyword());

        SearchHistoryJpaEntity entity;
        if(foundEntity.isPresent()) {
            entity = foundEntity.get();
            entity.increaseCount();
        } else {
            entity = SearchHistoryJpaEntity.builder()
                    .id(searchHistory.getKeyword())
                    .build();
        }
        return repository.saveAndFlush(entity).toDomainEntity();
    }

    @Override
    public List<SearchHistory> findTop10Keyword() {
        return handleDbExceptions(() -> repository.findTop10ByOrderByCountDesc().stream()
                .map(SearchHistoryJpaEntity::toDomainEntity).collect(Collectors.toList()), SearchHistoryErrorCode.DATA_FETCH_ERROR);
    }
}
