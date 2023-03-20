package com.study.blogsearch.infrastructure.persistence.repository;

import com.study.blogsearch.infrastructure.persistence.entity.KeywordDateKey;
import com.study.blogsearch.infrastructure.persistence.entity.SearchHistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SearchHistoryJpaRepository extends JpaRepository<SearchHistoryJpaEntity, KeywordDateKey> {
}
