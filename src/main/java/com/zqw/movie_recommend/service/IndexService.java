package com.zqw.movie_recommend.service;

import com.zqw.movie_recommend.entity.pojo.MoviePojo;
import com.zqw.movie_recommend.entity.vo.MovieIndexVO;
import sun.rmi.server.InactiveGroupException;

import java.util.List;

public interface IndexService {

    /**
     * 获取主页推荐电影
     * @return
     */
    List<MovieIndexVO> getIndexMovieList();

    /**
     * 主页推荐电影，优化
     * @return
     */
    List<MovieIndexVO> getIndexMovieList2();
    /**
     * 获取本年高分电影
     * @param year
     * @param m 获取数量
     * @return
     */
    List<MoviePojo> getTopMovieList(String year, Integer m);

    /**
     * 获取本年热门电影
     * @return
     */
    List<MoviePojo> getHotMovieList(String year, Integer m);


    MovieIndexVO getOneMovie(Long mid);



}
