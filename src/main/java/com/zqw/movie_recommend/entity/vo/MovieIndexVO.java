package com.zqw.movie_recommend.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MovieIndexVO {

    private Integer star;
    private String title;
    private String img;
    private String directors;
    private String score;
}
