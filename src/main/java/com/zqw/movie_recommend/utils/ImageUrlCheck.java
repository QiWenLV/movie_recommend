package com.zqw.movie_recommend.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.Charset;

public class ImageUrlCheck {


    public static void main(String[] args) throws IOException {

        checkUrl("https://img1.doubanio.com/lpic/s2387558.jpg");
        checkUrl("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2391500455.jpg");

        System.out.println(getDoubanImage(26678760L));
        System.out.println(getDoubanImage(1302724L));
        System.out.println(getDoubanImage(1296518L));
    }

    public static Boolean checkUrl(String url) {

        CloseableHttpClient httpClient = HttpClients.createDefault();

        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
            response = httpClient.execute(httpGet);
        } catch (IOException e) {
            return false;
        }
        // 输出返回状态码 成功返回：HTTP/1.1 200 OK，
        // 判断字符串中是否含有“200”即可表示测试连接成功
        return 200 == response.getStatusLine().getStatusCode();
    }

    public static String getDoubanImage(Long mid){

        String url = "https://movie.douban.com/subject/"+mid+"/photos?type=R";
        HttpClient httpClient = HttpClients.createDefault();
        HttpResponse execute = null;
        try {
            execute = httpClient.execute(new HttpGet(url));
            String html = EntityUtils.toString(execute.getEntity(), Charset.forName("utf-8"));

            Document doc = Jsoup.parse(html);
            //
            Elements select = doc.select("div.article div.cover img");
            if(select.size() == 0){
                return "";
            }
            return select.get(0).attr("src");

        } catch (IOException e) {
            System.out.println("请求超时：" + url);
            e.printStackTrace();
        }
        return "";
    }
}
