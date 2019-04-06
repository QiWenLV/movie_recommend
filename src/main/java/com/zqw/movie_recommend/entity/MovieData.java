package com.zqw.movie_recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieData {
    private Long mid;
    private String seasonCount;
    private String year;
    private String episodes;
    private String site;
    private String doubanSite;
    private String poster;
    private String title;
    private String imdb;
    private String duration;
    private String summary;
    //作家*
    private String writers;
    //导演*
    private String directors;
    //国家*
    private String countries;
    //上映时间*
    private String pubdate;
    //类型*
    private String genres;
    //演员*
    private String casts;
    //其他译名*
    private String aka;
    //语言*
    private String languages;

}