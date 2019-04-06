package com.zqw.movie_recommend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
public class MovieEntity {

    /**
     * _id : 25934014
     * season_count :
     * rating : {"stars":["43.2","38.2","15.8","2.2","0.7"],"average":"8.4","rating_people":"242689"}
     * year : 2016
     * episodes :
     * site :
     * douban_site :
     * writers : [{"id":"1014996","name":"达米恩·查泽雷"}]
     * directors : [{"id":"1014996","name":"达米恩·查泽雷"}]
     * countries : ["美国"]
     * pubdate : ["2017-02-14(中国大陆)","2016-08-31(威尼斯电影节)","2016-12-25(美国)"]
     * genres : ["剧情","爱情","歌舞"]
     * poster : https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2425658570.jpg
     * title : 爱乐之城 La La Land
     * imdb : tt3783958
     * casts : [{"id":"1012531","name":"瑞恩·高斯林"},{"id":"1040506","name":"艾玛·斯通"},{"id":"1014178","name":"约翰·传奇"},{"id":"1040580","name":"罗丝玛丽·德薇特"},{"id":"1315253","name":"芬·维特洛克"},{"id":"1325899","name":"杰西卡·罗德"},{"id":"1347723","name":"水野索诺娅"},{"id":"1339675","name":"考莉·赫尔南德斯"},{"id":"1147911","name":"J·K·西蒙斯"},{"id":"1049663","name":"汤姆·艾弗瑞特·斯科特"},{"id":"1320506","name":"米根·费伊"},{"id":"1346113","name":"达蒙·冈普顿"},{"id":"1032466","name":"贾森·福克斯"},{"id":"1252378","name":"乔什·平茨"},{"id":"1237561","name":"艾米·科恩"},{"id":"1344851","name":"特里·沃尔特斯"},{"id":"1365679","name":"汤姆·谢尔顿"},{"id":"1328316","name":"辛达·亚当斯"},{"id":"1365680","name":"克劳丁·克劳迪奥"},{"id":"1365681","name":"D·A·瓦拉赫"},{"id":"1120945","name":"特雷弗·里斯奥尔"},{"id":"1368728","name":"奥莉维亚·汉密尔顿"},{"id":"1368729","name":"安娜·查泽雷"},{"id":"1276426","name":"马里乌斯·代·弗里斯"},{"id":"1368730","name":"妮科尔·库隆"},{"id":"1027989","name":"迈尔斯·安德森"},{"id":"1308171","name":"约翰·辛德曼"},{"id":"1123746","name":"瓦拉里·雷·米勒"},{"id":"1150551","name":"基夫·范登·霍伊维尔"},{"id":"1119424","name":"佐伊·霍尔"},{"id":"1127761","name":"登普西·帕皮恩"},{"id":"1348657","name":"辛德拉·车"}]
     * aka : ["星声梦里人(港)","乐来越爱你(台)","爵士情缘","啦啦之地"]
     * languages : ["英语"]
     * duration : 128
     * summary : 米娅（艾玛·斯通EmmaStone饰）渴望成为一名演员，但至今她仍旧只是片场咖啡厅里的一名平凡的咖啡师，尽管不停的参加着大大小小的试镜，但米娅收获的只有失败。某日，在一场派对之中，米娅邂逅了名为塞巴斯汀（瑞恩·高斯林RyanGosling饰）的男子，起初两人之间产生了小小的矛盾，但很快，米娅便被塞巴斯汀身上闪耀的才华以及他对爵士乐的纯粹追求所吸引，最终两人走到了一起。
     在塞巴斯汀的鼓励下，米娅辞掉了咖啡厅的工作，专心为自己写起了剧本，与此同时，塞巴斯汀为了获得一份稳定的收入，加入了一支流行爵士乐队，开始演奏自己并不喜欢的现代爵士乐，没想到一炮而红。随着时间的推移，努力追求梦想的两人，彼此之间的距离却越来越远，在理想和感情之间，他们必须做出选择。
     */

    private String _id;
    private String season_count;
    private RatingBean rating;
    private String year;
    private String episodes;
    private String site;
    private String douban_site;
    private String poster;
    private String title;
    private String imdb;
    private String duration;
    private String summary;
    private List<WritersBean> writers;
    private List<DirectorsBean> directors;
    private List<String> countries;
    private List<String> pubdate;
    private List<String> genres;
    private List<CastsBean> casts;
    private List<String> aka;
    private List<String> languages;

    @NoArgsConstructor
    @Data
    public static class RatingBean {
        /**
         * stars : ["43.2","38.2","15.8","2.2","0.7"]
         * average : 8.4
         * rating_people : 242689
         */

        private String average;
        private String rating_people;
        private List<String> stars;
    }

    @NoArgsConstructor
    @Data
    public static class WritersBean {
        /**
         * id : 1014996
         * name : 达米恩·查泽雷
         */

        private String id;
        private String name;
    }

    @NoArgsConstructor
    @Data
    public static class DirectorsBean {
        /**
         * id : 1014996
         * name : 达米恩·查泽雷
         */

        private String id;
        private String name;
    }

    @NoArgsConstructor
    @Data
    public static class CastsBean {
        /**
         * id : 1012531
         * name : 瑞恩·高斯林
         */

        private String id;
        private String name;
    }
}
