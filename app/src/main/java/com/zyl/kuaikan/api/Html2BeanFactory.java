package com.zyl.kuaikan.api;

import android.util.Log;

import com.zyl.kuaikan.bean.ChapterContentBean;
import com.zyl.kuaikan.bean.ChapterListBean;
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

public class Html2BeanFactory extends Converter.Factory {
    private static final String TAG="Html2BeanFactory";
    public static Html2BeanFactory create(){
        return new Html2BeanFactory();
    }

    @Nullable
    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        if(type== List.class){
            return new Html2BeanFactory.PopCartoonsBody<Type>();
        }else if(type== ChapterListBean.class){
            return new Html2BeanFactory.ChapterListBody<ChapterListBean>();
        }else if(type==ChapterContentBean.class){
            return new Html2BeanFactory.ChapterContentBody<ChapterContentBean>();
        }
        return null;
    }

    private static class ChapterContentBody<T> implements Converter<ResponseBody,T>{
        @Override
        public T convert(ResponseBody value) {
            ChapterContentBean contentBean=new ChapterContentBean();
            List<String> urlList=new ArrayList<>();
            Document document=null;
            try{
                document=Jsoup.parse(value.string());
            }catch (IOException ex){
                ex.printStackTrace();
                return null;
            }
            String tmpString;
            Elements elements=document.select("div.ft").get(0).getElementsByTag("li");

            if(elements.get(0).select("a[href]").size()==0){
                contentBean.setLastUrl(null);
            }else{
                tmpString=elements.get(0).selectFirst("a[href]").attr("href");
                contentBean.setLastUrl(tmpString);
            }
            if(elements.get(2).select("a[href]").size()==0){
                contentBean.setNextUrl(null);
            }else{
                tmpString=elements.get(2).selectFirst("a[href]").attr("href");
                contentBean.setNextUrl(tmpString);
            }
            Element element=document.select(".list").select(".comic-imgs").first();
            for(Element e:element.getElementsByTag("img")){
                tmpString=e.attr("data-kksrc");
                urlList.add(tmpString);
            }
            contentBean.setPictureList(urlList);
            return (T)contentBean;
        }
    }

    private static class ChapterListBody<T> implements Converter<ResponseBody,T>{
        @Override
        public T convert(ResponseBody value) {
            ChapterListBean chapterListBean=new ChapterListBean();
            List<ChapterListBean.ChapterItem> chapterItemList=new ArrayList<>();
            List<ChapterListBean.CommonCartoon> commonCartoonList=new ArrayList<>();
            ChapterListBean.ChapterItem chapterItem;
            ChapterListBean.CommonCartoon commonCartoon;
            String tmpString;
            Document document=null;
            try{
                document=Jsoup.parse(value.string());
            }catch (IOException ex){
                ex.printStackTrace();
                return null;
            }
            Element element=document.selectFirst("div.article-info");
            tmpString=element.select(".comic-img").select(".fl-left").get(0).getElementsByTag("img").get(0).attr("src");
            chapterListBean.setCoverUrl(tmpString);
            tmpString=element.selectFirst("div.comic-name").text();
            chapterListBean.setTitle(tmpString);
            tmpString=element.selectFirst("div.author-nickname").text();
            chapterListBean.setAuthor(tmpString);
            tmpString=element.selectFirst("div.switch-content").getElementsByTag("p").get(0).text();
            chapterListBean.setBrief(tmpString);
            if(element.select(".btn").select(".btn-primary").select(".btn-follow").select(".followed")==null){
                chapterListBean.setFollowed(false);
            }else{
                chapterListBean.setFollowed(true);
            }
            tmpString=element.selectFirst("div.num").getElementsByTag("li").get(1).selectFirst("span").text();
            chapterListBean.setHeatIndex(tmpString.substring(1,tmpString.length()));
            tmpString=element.selectFirst("div.num").getElementsByTag("li").get(2).selectFirst("span").text();
            chapterListBean.setPraiseIndex(tmpString.substring(1,tmpString.length()));
            tmpString=element.select(".btn").select(".btn-primary").select("a[href]").attr("href");
            chapterListBean.setFirstChapterUrl(tmpString);
            element=document.selectFirst("div.article-list");
            for(Element e:element.getElementsByTag("tr")){
                chapterItem=new ChapterListBean.ChapterItem();
                tmpString=e.select("a[href]").get(0).attr("href");
                chapterItem.setChapterUrl(tmpString);
                tmpString=e.select("a[href]").get(0).getElementsByTag("img").attr("src");
                chapterItem.setChapterCoverUrl(tmpString);
                tmpString=e.select("a[href]").get(1).attr("title");
                chapterItem.setChapterTitle(tmpString);
                tmpString=e.getElementsByTag("td").get(2).text();
                chapterItem.setChapterPraise(tmpString.split(" ")[1]);
                tmpString=e.getElementsByTag("td").get(3).text();
                chapterItem.setChapterTime(tmpString);
                chapterItemList.add(chapterItem);
            }
            element=document.select(".comic-item-detail").select(".kk-mod").select(".recommend-box").first();
            for(Element e:element.select("div.item")){
                commonCartoon=new ChapterListBean.CommonCartoon();
                tmpString=e.select("a[href]").get(0).attr("href");
                commonCartoon.setUrl(tmpString);
                tmpString=e.select("a[href]").get(0).getElementsByTag("img").attr("src");
                commonCartoon.setCoverUrl(tmpString);
                tmpString=e.select("div.title").select("a[href]").first().text();
                commonCartoon.setTitle(tmpString);
                tmpString=e.select(".praise-num-l").select(".show-tip").text().split(" ")[3];
                commonCartoon.setPraiseIndex(tmpString);
                tmpString=e.select(".praise-num-r").select(".show-tip").text().split(" ")[3];
                commonCartoon.setDiscussIndex(tmpString);
                tmpString=e.selectFirst("div.content").getElementsByTag("p").text();
                commonCartoon.setBrief(tmpString);
                commonCartoonList.add(commonCartoon);
            }
            chapterListBean.setChapterItems(chapterItemList);
            chapterListBean.setCommonCartoons(commonCartoonList);
            return (T)chapterListBean;
        }
    }
    private static class PopCartoonsBody<T> implements Converter<ResponseBody,T>{
        @Override
        public T convert(ResponseBody value) {
            List<PopularCartoon> list=new ArrayList<>();
            PopularCartoon cartoon;
            String tmpString;
            Document document=null;
            try {
                document = Jsoup.parse(value.string());
            } catch (IOException e) {
                e.printStackTrace();
                return null;
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
