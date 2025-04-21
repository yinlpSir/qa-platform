package com.gjw.wdsystem;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Objects;

@SpringBootTest
public class HotSpiderTest {
    @Test
    public void test2() throws IOException {
        Document document = Jsoup.connect("https://www.zhihu.com/hot")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/130.0.0.0 Safari/537.36")
                .header("Cookie","YD00517437729195%3AWM_TID=P45%2Fj%2B93ATpEBBFFEVbVw89wGptntsSx; YD00517437729195%3AWM_NI=cOf6InpyQGTbRG8vKpW%2BkY%2FriTOlL5m8obMxkCLpM7dao3j%2FLK2bBCC9GoBiS1Ys0Iu1OylQsnrPd3Ft0RpHlXq5fY01HTxxZLjQqIbUNij5nv3kUtkl3hi2hhSIKsnHVHc%3D; YD00517437729195%3AWM_NIKE=9ca17ae2e6ffcda170e2e6eed1f57af8879e8aea5cb38e8fb3c85b939a8f86c57c87e9bc8ec45bb0bab6a7c22af0fea7c3b92aa191aad7cc66b4a79fa8d76a9299f897ca7c95f5a5baae7aab8c818dcf3fafe8a183d074a99d8491d03da8bfa1d4e9259095fe83c14981e78990c16391ef9794b343b7ef819aef5bb1b2beb1e73986ac00b3f344b088bfd6e553f4ef88b5fb749a94a6b2f443a28b828ae93faeaafd8cd880a9b4fd89ec3aacb89daaca79ae989aa9e237e2a3; __snaker__id=sO89VNGleQ5HoXD9; q_c1=0eb1852b92e64f3ba2dee0b7715d4b48|1715588441000|1715588441000; _zap=dd0f7c42-de26-42c4-8820-2323d11201a8; d_c0=AQBSIhvDLhmPTkVWEbrLrSyZx9ErCxqnCbQ=|1725414883; _xsrf=f3TpVL9re53oFaWpFlzwnCK95UkGnGkk; z_c0=2|1:0|10:1730081439|4:z_c0|80:MS4xbG5ablB3QUFBQUFtQUFBQVlBSlZUV3BrQUdpRHZrUDNYNXRFX3Vzal9VeGZ3a2JsOUZxNERRPT0=|cc6752f5445becfd0f968a4e3076d35bef89f026ec6bf160f3d4323d45f8758f; ff_supports_webp=1; __zse_ck=003_b61gy2Bnc0FAaIBh7iWW1Be/GxqxrlwVgeChxFJI8X4MpqFTFMTE=FeR+CAvLssQ3Yua92YGNI6SsrX8EClG6ubR71ixKSp9KqEA1X6F7=xB; Hm_lvt_98beee57fd2ef70ccdd5ca52b9740c49=1730858385,1730955235,1731033687,1731114364; HMACCOUNT=E02D49AB793531D0; SESSIONID=LAO8DUY17zw8ldp6xNweKPyvrH1HKuAX7g2WTgVPIDD; JOID=VVwXB0if-9dBY5dTI53sxZLjtnkx0LyefiPBDBSsgqZ_CPcgE_ZA-CVkklMiAYZpvW6grBF7nbh11NlsIOrP530=; osd=VVwQB0yf-9BBZ5dTJJ3oxZLktn0x0LueeiPBCxSogqZ4CPMgE_FA_CVklVMmAYZuvWqgrBZ7mbh109loIOrI53k=; tst=h; BEC=4da77e64be3cf762e3831e43ab259290; Hm_lpvt_98beee57fd2ef70ccdd5ca52b9740c49=1731117930")
                .get();
        Element bodyElement = document.body();

        /*
         * 获取所有热门问题
         *
         * .select("#TopstoryContent div.HotList-list section.HotItem"); // 或者这样也可以
         * */
        Elements elements = bodyElement
                .getElementById("TopstoryContent")
                .selectFirst(".HotList-List")
                .children();

        for (Element e : elements){
            Element aElement = e.selectFirst(".HotItem-content").firstElementChild();

            String problemLink = aElement.attr("href");
            String problemTitle = aElement.attr("title");
            Element imgElement = e.selectFirst(".HotItem-img");
            if(Objects.nonNull(imgElement)){
                String img = imgElement.firstElementChild().attr("src");
                System.out.println(img);
            }

        }

    }
}
