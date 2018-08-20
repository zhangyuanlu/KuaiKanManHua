package com.zyl.kuaikan.API;

import android.util.Log;

import com.zyl.kuaikan.bean.PopularCartoon;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.annotations.Nullable;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class PopCartoonsFactory extends Converter.Factory {
    private static final String TAG="PopCartoonsFactory";
    public static PopCartoonsFactory create(){
        return new PopCartoonsFactory();
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if(type== List.class){
            return new PopCartoonsBody<Type>();
        }
        return null;
    }
    private static class PopCartoonsBody<T> implements Converter<ResponseBody,T>{
        PopCartoonsBody(){

        }
        @Override
        public T convert(ResponseBody value) {
            List<PopularCartoon> list=new ArrayList<>();
            PopularCartoon cartoon;
            String tmpString;
            Document document= null;
            try {
                document = Jsoup.parse(value.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Elements elements=document.select("li.items");
            for(Element e:elements){
                cartoon=new PopularCartoon();
                tmpString=e.select("a[href]").get(0).attr("href");
                cartoon.setUrlDetail(tmpString);
                cartoon.setId(tmpString.split("/")[3]);
                cartoon.setTitle(e.select("a[href]").get(0).attr("title"));
                cartoon.setAuthor(e.select(".topic-title").select(".fl-left").get(0).text());
                tmpString=e.select(".praise").select(".ico-praise").select(".fl-right").get(0).text();
                cartoon.setPopIndex(tmpString.substring(1,tmpString.length()));
                cartoon.setBitmapUrl(e.getElementsByTag("img").get(2).attr("data-kksrc"));
                list.add(cartoon);
            }
            return (T)list;
        }
    }
}
