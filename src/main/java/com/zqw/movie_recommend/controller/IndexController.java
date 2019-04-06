package com.zqw.movie_recommend.controller;

import com.zqw.movie_recommend.entity.vo.MovieIndexVO;
import com.zqw.movie_recommend.service.IndexService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
        List<MovieIndexVO> indexMovieList = indexService.getIndexMovieList();
        List<MovieIndexVO> topMovieList = indexService.getTopMovieList("2017", 5);
        List<MovieIndexVO> hotMovieList = indexService.getHotMovieList("2017", 8);

        model.addAttribute("indexList", indexMovieList);
        model.addAttribute("topList", topMovieList);
        model.addAttribute("hotList", hotMovieList);
        return "index";
    }

    @RequestMapping(value = "/index/refresh", method = RequestMethod.GET)
    public String indexListRefresh(Model model){
        log.info("主页列表刷新...");
        List<MovieIndexVO> indexMovieList = indexService.getIndexMovieList();
        model.addAttribute("indexList", indexMovieList);
        return "index::list1";
    }
}
