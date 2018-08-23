package com.xc.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Jsoup 截取字符串
 */
public class JsoupTest {

    //    private static final String URL = "https://www.bilibili.com/";
    private static final String TODAY_URL = "http://horoscope.ohippo.com/zodiac-signs/virgo/daily-20180823";
    private static final String TOMORROW_URL = "http://horoscope.ohippo.com/zodiac-signs/virgo/daily-20180824";
    private static final String MONTHLY_URL = "http://horoscope.ohippo.com/zodiac-signs/virgo/monthly-201808";
    private static final String YEARLY_URL = "http://horoscope.ohippo.com/zodiac-signs/virgo/yearly-2018";
    private static final String CHARACTER_URL = "http://horoscope.ohippo.com/zodiac-signs/virgo/characteristics";

    public static void main(String[] args) throws IOException {

        jsoupScript(TODAY_URL);
        jsoupScript(TOMORROW_URL);
        jsoupScript(MONTHLY_URL);
        jsoupScript(YEARLY_URL);
        jsoupScript(CHARACTER_URL);
    }

    /**
     * jsoup javascript 代码
     * @param url
     * @throws IOException
     */
    public static void jsoupScript(String url) throws IOException {
        System.out.println("url:"+url);
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.getElementsByTag("script");
        for (Element script : elements) {
            String data = script.data().trim();
            //筛选以var zodiac_content 开头
            if (!data.startsWith("var zodiac_content")) {
                continue;
            }
            //截取；
            int flag = 0;
            int index = 0;
            int count = data.length();
            for (index = 0; index < count; index++) {
                String subStr = data.substring(index, index + 1);
                if ("[".equals(subStr)) {
                    flag += 1;
                }
                if ("]".equals(subStr)) {
                    flag -= 1;
                    if (flag == 0) {
                        System.out.println("index = " + index);
                        break;
                    }
                }
            }
            //截取数据
            String subData = data.substring(data.indexOf("["), index + 1);
            System.out.println("subData:" + subData);
            System.out.println("-----------------------------------------");
        }
    }
}
