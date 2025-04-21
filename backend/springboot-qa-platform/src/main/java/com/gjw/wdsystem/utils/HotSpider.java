package com.gjw.wdsystem.utils;

import com.gjw.wdsystem.vo.HotProblemVO;
import lombok.Data;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 写的一个爬虫组件。
 * 以下爬的是知乎的热门专栏的所有热门问题
 * 用的是Jsoup库。
 * 参考过的文章：https://blog.csdn.net/xhmico/article/details/135291629
 */
@Component
@Data
@ConfigurationProperties(prefix = "spider")
public class HotSpider {
    private static String url = "https://www.zhihu.com/hot"; // 爬取的链接
    private String UserAgent; //
    private String cookie; // 因为爬取的网址需要先登录，所以这里拿到我提前登录的知乎返回的cookie

    /**
     *  爬取并返回网页的body
     * @return html 的 body区域的所有内容
     */
    private Element getHtmlBody(){
        Document document = null;
        try {
            document = Jsoup.connect(url)
                    .header("User-Agent", UserAgent)
                    .header("Cookie",cookie)
//                    .data("key","value")
                    .get(); // get() 表示get请求,当然也可以post()
        } catch (IOException e) {
            throw new RuntimeException("爬虫异常!",e);
        }
        return document.body();
    }

    /**
     *
     * @return
     */
    public List<HotProblemVO> getAllHotProblems(){
        Elements elements = getHtmlBody()
                .getElementById("TopstoryContent")
                .selectFirst(".HotList-List")
                .children();
        var data = new ArrayList<HotProblemVO>();
        for (Element e : elements){
            Element aElement = e.selectFirst(".HotItem-content").firstElementChild();
            HotProblemVO hotProblemVO = new HotProblemVO();

            String problemLink = aElement.attr("href");
            hotProblemVO.setProblemLink(problemLink);

            String problemTitle = aElement.attr("title");
            hotProblemVO.setTitle(problemTitle);

            hotProblemVO.setHeat(aElement.nextElementSibling().ownText()); // ownText() 应该是获取自身的文本内容,不会获取子节点的文本内容。

            String problemContent = aElement.lastElementChild().text();
            hotProblemVO.setContent(problemContent);

            Element imgElement = e.selectFirst(".HotItem-img");
            if(Objects.nonNull(imgElement)){
                String img = imgElement.firstElementChild().attr("src");
                hotProblemVO.setCover(img);
            }

            data.add(hotProblemVO);
        }
        return data;
    }
}
