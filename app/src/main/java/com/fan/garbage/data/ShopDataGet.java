package com.fan.garbage.data;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class ShopDataGet {
    public static String getData() throws IOException {
        Document document= Jsoup.connect("https://uland.taobao.com/coupon/edetail?e=Mqiv9eArwucGQASttHIRqbRsxpJAB9hg1U8ALpQTU9sQzxWZZqjo%2BfVX3zvIuL7KZVST3oq4cbYdJP3Xafk2z79fwBwwUiqlshjd%2F6slxGTwYnPZvb1Jvm7PVn13QcLN%2FfsIcQx%2BbXUxcPP" +
                "c3IMH9g%3D%3D&traceId=0be5bd3c15329820303235231e&activityId=8c49c128623443ef8e253997d39b22d0").get();
        Elements element=document.select("div.pic")
                .select("img");
        Log.e("data",element.attr("src"));
        return element.attr("src");
    }
}
