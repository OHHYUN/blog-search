package com.study.blogsearch.infrastructure.persistence.repository;

import com.study.blogsearch.infrastructure.persistence.entity.SearchHistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;


public interface SearchHistoryJpaRepository extends JpaRepository<SearchHistoryJpaEntity, String> {

    @Transactional
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM SearchHistoryJpaEntity s WHERE s.id = :keyword")
    Optional<SearchHistoryJpaEntity> findByIdWithLock(@Param("keyword") String keyword);

    List<SearchHistoryJpaEntity> findTop10ByOrderByCountDesc();
}
