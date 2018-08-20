package com.zyl.kuaikan.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by zhangyuanlu on 18-8-16.
 */

public class CarouselAdapter extends PagerAdapter {
    private List<ImageView> imageViews;
    private ViewPager viewPager;

    public CarouselAdapter(List<ImageView> imageViews,ViewPager viewPager){
        this.imageViews=imageViews;
        this.viewPager=viewPager;
    }
    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView=imageViews.get(position%imageViews.size());
        viewPager.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        viewPager.removeView(imageViews.get(position%imageViews.size()));
    }
}
