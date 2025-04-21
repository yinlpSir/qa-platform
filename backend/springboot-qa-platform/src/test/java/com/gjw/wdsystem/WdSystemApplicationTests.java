package com.gjw.wdsystem;

import com.gjw.wdsystem.mapper.FollowMapper;
import com.gjw.wdsystem.pojo.Follow;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@SpringBootTest
class WdSystemApplicationTests {

    @Autowired
    private FollowMapper followMapper;

    @Autowired
    private ResourceLoader resourceLoader;

    @Test
    void contextLoads() throws IOException {
        Document document = Jsoup.parse(new URL("https://www.zhihu.com/hot"),3000);
        Elements elements = document.getElementsByClass("HotItem");
        for (Element element : elements){
            System.out.println(element.getElementsByClass("HotItem-content"));
        }
    }

    @Test
    void defaultHeadPortrait() throws IOException {
//        Path path = Paths.get(new URL("src/main/resources"));
        Resource resource = resourceLoader.getResource("classpath:static/default_headPortrait.jpg");
        System.out.println(resource.getInputStream().getClass().getName());
    }

    @Test
    void test2(){
        List<Follow> follows = followMapper.getFollowers("8771084db68f4e99acfcb114fd164825");
        System.out.println(follows);
    }

}
