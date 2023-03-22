package com.study.blogsearch.infrastructure.persistence.repository;

import com.study.blogsearch.domain.entity.SearchHistory;
import com.study.blogsearch.infrastructure.persistence.SearchHistoryAdapter;
import com.study.blogsearch.infrastructure.persistence.entity.SearchHistoryJpaEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
@DataJpaTest
class SearchHistoryAdapterTest {

    @Autowired
    private SearchHistoryJpaRepository searchHistoryJpaRepository;

    private SearchHistoryAdapter searchHistoryAdapter;

    @BeforeEach
    void setUp() {
        searchHistoryAdapter = new SearchHistoryAdapter(searchHistoryJpaRepository);
        SearchHistoryJpaEntity entity = SearchHistoryJpaEntity.builder()
                .id("keyword")
                .build();
        SearchHistoryJpaEntity entity1 = searchHistoryJpaRepository.saveAndFlush(entity);
    }

    @Test
    @DisplayName("저장 조회 테스트")
    void findKeyword() {
        String keyword = "keyword";
        SearchHistoryJpaEntity entity = SearchHistoryJpaEntity.builder()
                .id(keyword)
                .build();
        searchHistoryJpaRepository.save(entity);
        final var findEntity = searchHistoryJpaRepository.findByIdWithLock(keyword).orElseThrow();

        assertEquals(findEntity.getId(), keyword);
        assertEquals(1, findEntity.getCount());
    }
    @Test
    @DisplayName("순차적인 조회 일 경우의 테스트")
    void findAndUpdate() {

        String keyword = "돼지";
        LocalDate date = LocalDate.now();
        SearchHistory searchHistory = SearchHistory.builder()
                .keyword(keyword)
                .date(date)
                .build();

        for (int i = 0; i < 20; i++) {
             searchHistoryAdapter.findAndUpdate(searchHistory);

        }
        SearchHistoryJpaEntity searchHistoryJpaEntity = searchHistoryJpaRepository.findById(keyword).orElseThrow();
        assertEquals(20, searchHistoryJpaEntity.getCount());
    }

}