package com.zqw.movie_recommend.controller;

import com.zqw.movie_recommend.entity.MovieData;
import com.zqw.movie_recommend.entity.RatingData;
import com.zqw.movie_recommend.entity.pojo.MoviePojo;
import com.zqw.movie_recommend.entity.vo.MovieIndexVO;
import com.zqw.movie_recommend.entity.vo.OneMovieVO;
import com.zqw.movie_recommend.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
public class IndexController {

    private final IndexService indexService;

    @Autowired
    public IndexController(IndexService indexService) {
        this.indexService = indexService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String indexList(Model model){
        log.info("加载主页...");
        List<MovieIndexVO> indexMovieList = indexService.getIndexMovieList2();
        List<MoviePojo> topMovieList = indexService.getTopMovieList("2017", 8);
        List<MoviePojo> hotMovieList = indexService.getHotMovieList("2017", 8);

        model.addAttribute("indexList", indexMovieList);
        model.addAttribute("topList", topMovieList);
        model.addAttribute("hotList", hotMovieList);
        return "index";
    }

    @RequestMapping(value = "/index/refresh", method = RequestMethod.GET)
    public String indexListRefresh(Model model){
        log.info("主页列表刷新...");
        List<MovieIndexVO> indexMovieList = indexService.getIndexMovieList2();
        model.addAttribute("indexList", indexMovieList);
        return "index::list1";
    }

    @RequestMapping(value = "/index/one/{mid}", method = RequestMethod.GET)
    public String indexOneMovie(@PathVariable String mid, Model model){
        log.info("获取视频详情...");
        OneMovieVO oneMovie = indexService.getOneMovie(Long.valueOf(mid));
        RatingData oneRating = indexService.getOneMovieRating(Long.valueOf(mid));
        model.addAttribute("oneMovie", oneMovie);
        model.addAttribute("oneRating", oneRating);
        return "test";
    }

    @RequestMapping(value = "/index/recommend/{mid}", method = RequestMethod.GET)
    public String recommendList(@PathVariable String mid, Model model){
        log.info("推荐列表...");
        List<MovieIndexVO> indexMovieList = indexService.getIndexMovieList2();
        model.addAttribute("recommendList", indexMovieList);
        return "test::recommend";
    }
}
