package com.study.blogsearch.infrastructure.persistence.repository;

import com.study.blogsearch.infrastructure.persistence.entity.KeywordDateKey;
import com.study.blogsearch.infrastructure.persistence.entity.SearchHistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface SearchHistoryJpaRepository extends JpaRepository<SearchHistoryJpaEntity, KeywordDateKey> {

    @Query("SELECT s FROM SearchHistoryJpaEntity s WHERE s.id.date = :date ORDER BY s.count DESC")
    List<SearchHistoryJpaEntity> findTop10KeywordByDate(LocalDate date);
}
