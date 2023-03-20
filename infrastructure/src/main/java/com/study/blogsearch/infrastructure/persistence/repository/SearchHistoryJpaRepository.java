package com.study.blogsearch.infrastructure.persistence.repository;

import com.study.blogsearch.infrastructure.persistence.entity.KeywordDateKey;
import com.study.blogsearch.infrastructure.persistence.entity.SearchHistoryJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.persistence.LockModeType;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


public interface SearchHistoryJpaRepository extends JpaRepository<SearchHistoryJpaEntity, KeywordDateKey> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM SearchHistoryJpaEntity s WHERE s.id = :key")
    Optional<SearchHistoryJpaEntity> findByIdWithLock(@Param("key") KeywordDateKey key);


    List<SearchHistoryJpaEntity> findTop10ByIdDateOrderByCountDesc(LocalDate date);

}
