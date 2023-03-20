package com.study.blogsearch.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDate;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class KeywordDateKey implements Serializable {

    @Column(name = "keyword")
    private String keyword;

    @Column(name = "date")
    private LocalDate date;
}
