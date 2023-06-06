package com.hycu.boxoffice.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.math.BigDecimal;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "box_office", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"movie_code", "saved_at"})
})
@Getter
@Setter
@NoArgsConstructor
public class BoxOfficeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "movie_code", unique = true)
    private Integer movieCode;
    @Column(name = "movie_name")
    private String movieName;
    @Column(name = "ranking")
    private Integer ranking;
    @Column(name = "rank_intensity")
    private Integer rankIntensity;
    @Column(name = "new_ranked")
    private Boolean newRanked;
    @Column(name = "sales_amount")
    private Long salesAmount;
    @Column(name = "sales_share")
    private BigDecimal salesShare;
    @Column(name = "sales_intensity")
    private Long salesIntensity;
    @Column(name = "sales_change")
    private BigDecimal salesChange;
    @Column(name = "sales_accumulate")
    private Long salesAccumulate;
    @Column(name = "audience_count")
    private Long audienceCount;
    @Column(name = "audience_intensity")
    private Long audienceIntensity;
    @Column(name = "audience_change")
    private BigDecimal audienceChange;
    @Column(name = "audience_accumulate")
    private Long audienceAccumulate;
    @Column(name = "screen_count")
    private Long screenCount;
    @Column(name = "show_count")
    private Long showCount;
    @Column(name = "opened_at")
    private LocalDate openedAt;
    @Column(name = "saved_at")
    private LocalDate savedAt;
}
