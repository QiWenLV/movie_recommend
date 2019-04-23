package com.zqw.movie_recommend.service.impl;

import com.zqw.movie_recommend.dao.MovieDataMapper;
import com.zqw.movie_recommend.dao.RatingDataMapper;
import com.zqw.movie_recommend.entity.MovieData;
import com.zqw.movie_recommend.entity.RatingData;
import com.zqw.movie_recommend.entity.pojo.MoviePojo;
import com.zqw.movie_recommend.entity.vo.MovieIndexVO;
import com.zqw.movie_recommend.service.IndexService;
import com.zqw.movie_recommend.utils.ImageUrlCheck;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class IndexServiceImpl implements IndexService {

    private final RatingDataMapper ratingDataMapper;
    private final MovieDataMapper movieDataMapper;

    @Autowired
    public IndexServiceImpl(MovieDataMapper movieDataMapper, RatingDataMapper ratingDataMapper) {
        this.movieDataMapper = movieDataMapper;
        this.ratingDataMapper = ratingDataMapper;
    }


    @Override
    public List<MovieIndexVO> getIndexMovieList() {

        List<MovieData> movieData = movieDataMapper.selectListRand(12);

        return movieData.stream().map(this::commonMovie).collect(Collectors.toList());
    }

    @Override
    public List<MovieIndexVO> getTopMovieList(String year, Integer m) {

        List<MoviePojo> moviePojos = movieDataMapper.selectListTop(year, m);


        return null;
    }

    @Override
    public List<MovieIndexVO> getHotMovieList(String year, Integer m) {
        return null;
    }

    @Override
    public MovieIndexVO getOneMovie(Long mid) {
        MovieData movieData = movieDataMapper.selectByPrimaryKey(mid);
        return commonMovie(movieData);
    }


    public MovieIndexVO commonMovie(MovieData m){
        //平均分
        RatingData ratingData = ratingDataMapper.selectByPrimaryKey(m.getMid());
        if(ratingData == null){
            ratingData = RatingData.builder()
                    .average("0.0")
                    .build();
        }
        String average = ratingData.getAverage();

        //导演
        String directors = m.getDirectors();
        if (!"".equals(directors)) {
            directors = directors.substring(1, directors.length());
        }

        //检查图片链接
        String imageUrl = m.getPoster();
        if(!ImageUrlCheck.checkUrl(imageUrl)){
            imageUrl = ImageUrlCheck.getDoubanImage(m.getMid());
            if(!"".equals(imageUrl)){
                //更新
                movieDataMapper.updateByPrimaryKeySelective(MovieData.builder().poster(imageUrl).build());
            } else {
                //删除数据
            }
        }

        //导出数据
        return MovieIndexVO.builder()
                .img(imageUrl)
                .score(average)
                .star(Math.round(Float.valueOf(average)) / 2)
                .title(m.getTitle())
                .directors(directors)
                .build();
    }
}
