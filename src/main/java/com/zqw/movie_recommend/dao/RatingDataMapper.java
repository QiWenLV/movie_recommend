package com.zqw.movie_recommend.dao;

import com.zqw.movie_recommend.entity.RatingData;

public interface RatingDataMapper {
    int deleteByPrimaryKey(Long mid);

    int insert(RatingData record);

    int insertSelective(RatingData record);

    RatingData selectByPrimaryKey(Long mid);

    int updateByPrimaryKeySelective(RatingData record);

    int updateByPrimaryKey(RatingData record);
}