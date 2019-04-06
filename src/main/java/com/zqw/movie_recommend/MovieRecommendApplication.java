package com.zqw.movie_recommend;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zqw.movie_recommend.dao")
public class MovieRecommendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieRecommendApplication.class, args);
	}

}
