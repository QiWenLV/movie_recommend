package com.zqw.movie_recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@Builder
@AllArgsConstructor
public class RatingData {
    private Long mid;

    private String stars5;

    private String stars4;

    private String stars3;

    private String stars2;

    private String stars1;

    private String average;

    private String ratingPeople;

}