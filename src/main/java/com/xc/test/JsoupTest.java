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
    private static final String URL = "http://horoscope.ohippo.com/zodiac-signs/virgo/daily-20180822";

    public static void main(String[] args) throws IOException {

        Document doc = Jsoup.connect(URL).get();
        Elements elements = doc.getElementsByTag("script");
    }

    /**
     * jsoup javascript 代码
     * @throws IOException
     */
    public static void jsoupScript() throws IOException {
        Document doc = Jsoup.connect(URL).get();
        Elements scripts = doc.getElementsByTag("script");
        for (Element script : scripts) {
            String data = script.data().trim();
            //筛选以var zodiac_content 开头
            if (!data.startsWith("var zodiac_content")) {
                continue;
            }
            //截取；
            String[] split = data.split(";");
            for (String item : split) {
                //以 var zodiac_content 开头
                if (item.startsWith("var zodiac_content")) {
//                    System.out.println("matchData:"+item);
                    //截取数据
                    String subData = item.substring(item.indexOf("{"), item.lastIndexOf("}")+1);
                    System.out.println("subData:"+subData.replaceAll("\\s*",""));
                    System.out.println();
                }
            }
            String title = doc.title();
            System.out.println("title:" + title);

        }
    }
}
