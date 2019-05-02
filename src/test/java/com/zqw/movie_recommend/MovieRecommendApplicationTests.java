package com.zqw.movie_recommend;

import com.alibaba.fastjson.JSON;
import com.zqw.movie_recommend.dao.MovieDataMapper;
import com.zqw.movie_recommend.dao.RatingDataMapper;
import com.zqw.movie_recommend.entity.MovieData;
import com.zqw.movie_recommend.entity.MovieEntity;
import com.zqw.movie_recommend.entity.RatingData;
import com.zqw.movie_recommend.utils.ImageUrlCheck;
import com.zqw.movie_recommend.utils.KV;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.*;
import java.sql.JDBCType;
import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MovieRecommendApplicationTests {

    @Autowired
    private MovieDataMapper movieDataMapper;

    @Autowired
    private RatingDataMapper ratingDataMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;

	@Test
	public void contextLoads() throws IOException {

		String filePath = "C:\\Users\\启文\\Desktop\\html\\movielens-douban-dataset\\spider.json";
		File file = new File(filePath);
		if (!file.exists()) {
			return;
		}

		String movieId = "";
		try {
			LineIterator iterator = FileUtils.lineIterator(file, "UTf-8");
            long count = 0;
			while (iterator.hasNext()) {
				String line = iterator.nextLine();
                if( count < 2526) {
                    count ++;
                    continue;
                }
                if(count % 100 == 0){
                    Thread.sleep(20000);
                }
                Thread.sleep(2000);
                MovieEntity m = JSON.parseObject(line, MovieEntity.class);
                movieId = m.get_id();

                String imgUrl = getImg(m.getPoster(), Long.valueOf(movieId));

                if (imgUrl == null) {
                    continue;
                }

                //导入数据
                //数据处理
                MovieData movieData = MovieData.builder()
                        .mid(Long.valueOf(movieId))
                        .seasonCount(m.getSeason_count())
                        .year(m.getYear())
                        .episodes(m.getEpisodes())
                        .site(m.getSite())
                        .doubanSite(m.getDouban_site())
                        .poster(m.getPoster())
                        .title(m.getTitle())
                        .imdb(m.getImdb())
                        .duration(m.getDuration())
                        .summary(m.getSummary())
                        .writers(m.getWriters().stream()
                                .map(MovieEntity.WritersBean::getName)
                                .reduce("", (x, y) -> x + "/" + y))
                        .directors(m.getDirectors().stream().map(MovieEntity.DirectorsBean::getName).reduce("", (x, y) -> x +"/"+y))
                        .countries(m.getCountries().stream().reduce("", (x, y) -> x +"/"+y))
                        .pubdate(m.getPubdate().stream().limit(10).reduce("", (x, y) -> x +"/"+y))
                        .genres(m.getGenres().stream().reduce("", (x, y) -> x +"/"+y))
                        .casts(m.getCasts().stream().map(MovieEntity.CastsBean::getName).limit(12).reduce("", (x, y) -> x +"/"+y))
                        .aka(m.getAka().stream().reduce("", (x, y) -> x +"/"+y))
                        .languages(m.getLanguages().stream().reduce("", (x, y) -> x +"/"+y))
                        .build();

                MovieEntity.RatingBean rating = m.getRating();

                if(!"".equals(rating.getRating_people()) && !"".equals(rating.getAverage())){
                    RatingData ratingData = RatingData.builder()
                            .mid(Long.valueOf(movieId))
                            .stars5(rating.getStars().get(0))
                            .stars4(rating.getStars().get(1))
                            .stars3(rating.getStars().get(2))
                            .stars2(rating.getStars().get(3))
                            .stars1(rating.getStars().get(4))
                            .ratingPeople(rating.getRating_people())
                            .average(rating.getAverage())
                            .build();
                    ratingDataMapper.insert(ratingData);
                }
                movieDataMapper.insert(movieData);

                System.out.println(count + "     " + m.get_id());
                count ++;
            }
            System.out.println("结束：" + count);
		} catch (IOException e) {
            System.out.println("异常电影：" + movieId);
			e.printStackTrace();
		} catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


	public String getImg(String url, Long mid){
        String imageUrl = url;
        if(!ImageUrlCheck.checkUrl(imageUrl)){
            System.out.println(mid + ": 进入更新");
            imageUrl = ImageUrlCheck.getDoubanImage(mid);
            if(!"".equals(imageUrl)){
                //更新
                movieDataMapper.updateByPrimaryKeySelective(MovieData.builder().mid(mid).poster(imageUrl).build());
                System.out.println(mid + ": 更新成功");
                return imageUrl;
            } else {
                //删除数据
                System.out.println("电影图片无效：" + mid);
                return null;
            }
        }
        System.out.println(mid + ": 正常数据");
        return url;
    }

    @Test
    public void test2(){

	    RowMapper<MovieData> rowMapper = new BeanPropertyRowMapper<MovieData>(MovieData.class);
        List<MovieData> query = jdbcTemplate.query("select * from movie_data", rowMapper);

        query.stream().map(x -> new KV<Long, String>(x.getMid(), x.getPoster()))
                .forEach(x -> {
                    getImg(x.getV(), x.getK());
                });
    }


    @Test
    public void test3(){

        RowMapper<MovieData> rowMapper = new BeanPropertyRowMapper<MovieData>(MovieData.class);
        List<MovieData> query = jdbcTemplate.query("select * from movie_data", rowMapper);


    }
}
