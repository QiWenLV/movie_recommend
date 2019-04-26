package com.zqw.movie_recommend.dao;

import com.zqw.movie_recommend.entity.MovieData;
import com.zqw.movie_recommend.entity.pojo.MoviePojo;
import com.zqw.movie_recommend.entity.vo.MovieIndexVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MovieDataMapper {
    int deleteByPrimaryKey(Long mid);

    int insert(MovieData record);

    int insertSelective(MovieData record);

    MovieData selectByPrimaryKey(Long mid);

    int updateByPrimaryKeySelective(MovieData record);

    int updateByPrimaryKeyWithBLOBs(MovieData record);

    int updateByPrimaryKey(MovieData record);

//    @Select(value = "SELECT * FROM movie_data ORDER BY RAND() LIMIT #{m}")
    List<MovieData> selectListRand(Integer m);

    List<MovieData> selectListLimit(List<Integer> inLimit);

    List<MoviePojo> selectListTop(String year, Integer m);

    List<MoviePojo> selectListHot(String year, Integer m);
}