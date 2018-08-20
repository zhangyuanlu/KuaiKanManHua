package com.zyl.kuaikan.applicaiton;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.Stack;

public class MyApplication extends Application {
    private static final String TAG="MyApplication";
    private static MyApplication app;
    private static Context mContext;
    private static Stack<Activity> activityStack;

    @Override
    public void onCreate() {
        super.onCreate();
        app =this;
        mContext=getApplicationContext();
        Fresco.initialize(this);
    }
    public static MyApplication getInstance(){
        return app;
    }
    public static Context getApp(){
        return mContext;
    }
    public void addActivity(Activity activity){
        if(activityStack==null){
            activityStack=new Stack<>();
        }
        activityStack.add(activity);
    }
    public void removeActivity(Activity activity){
        if(activity!=null){
            activityStack.remove(activity);
            activity.finish();
            activity=null;
        }
    }
    public void finishAllActivity(){
        int size=activityStack.size();
        for(int i=0;i<size;i++){
            if(activityStack.get(i)!=null){
                activityStack.get(i).finish();
            }
            activityStack.clear();
        }
    }
    public void exitApp(){
        try{
            finishAllActivity();
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }catch (Exception ex){
            Log.e(TAG,"exitApp Exception");
            ex.printStackTrace();
        }
    }
}
