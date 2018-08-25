package com.zyl.kuaikan.util;

import android.content.Context;
import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import io.reactivex.annotations.Nullable;

public class Utilities {
    /**
     * 获得网络时间
     * @return
     */
    public static Date getNetTime(){
        String webUrl = "http://www.ntsc.ac.cn";
        try {
            URL url = new URL(webUrl);
            URLConnection uc = url.openConnection();
            uc.setReadTimeout(5000);
            uc.setConnectTimeout(5000);
            uc.connect();
            long correctTime = uc.getDate();
            Date date = new Date(correctTime);
            return date;
        } catch (Exception e) {
            return new Date();
        }
    }

    /**
     * 验证手机号码的合法性
     * @param telephone
     * @return
     */
    public static boolean verifyPhoneNum(String telephone){
        if (telephone.length() != 11) {
            return false;
        }
        Pattern pattern = Pattern.compile("^1[3,5]\\d{9}||18[6,8,9]\\d{8}$");
        Matcher matcher = pattern.matcher(telephone);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 验证密码的合法性
     * @param password
     * @return
     */
    public static boolean verifyPassword(String password){
        if(password.length()<8||password.length()>30){
            return false;
        }
        Pattern pattern=Pattern.compile("^[A-Za-z]+$");
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return true;
        }
        pattern=Pattern.compile("^(\\d+[A-Za-z]+[A-Za-z0-9]*)|([A-Za-z]+\\d+[A-Za-z0-9]*)$");
        matcher = pattern.matcher(password);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 获得屏幕宽高
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 自适应宽高
     * @param simpleDraweeView
     * @param imagePath
     * @param imageWidth
     */
    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, String imagePath, final int imageWidth) {
        final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                layoutParams.width = imageWidth;
                layoutParams.height = (int) ((float) (imageWidth * height) / (float) width);
                simpleDraweeView.setLayoutParams(layoutParams);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                Log.d("TAG", "Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                throwable.printStackTrace();
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder().setControllerListener(controllerListener).setUri(Uri.parse(imagePath)).build();
        simpleDraweeView.setController(controller);
    }
}
