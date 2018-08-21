package com.zyl.kuaikan.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.zyl.kuaikan.R;
import com.zyl.kuaikan.applicaiton.MyApplication;
import com.zyl.kuaikan.util.Utilities;


public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView,View.OnClickListener{
    private static final String TAG="BaseActivity";
    protected P presenter;
    public Context context;
    private TextView tv_homePager;
    private TextView tv_authorCenter;
    private TextView tv_login;
    private TextView tv_register;
    private TextView tv_attention;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            Window window=getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.parseColor("#282828"));
        }
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        context=this;
        MyApplication.getInstance().addActivity(this);
        presenter=initPresenter();
    }

    @Override
    protected void onDestroy() {
        MyApplication.getInstance().removeActivity(this);
        if(presenter!=null){
            presenter.detach();
            presenter=null;
        }
        super.onDestroy();
    }

    public void initView(View.OnClickListener listener){
        tv_attention=findViewById(R.id.tv_attention);
        tv_authorCenter=findViewById(R.id.tv_author_center);
        tv_homePager=findViewById(R.id.tv_home_page);
        tv_login=findViewById(R.id.tv_login);
        tv_register=findViewById(R.id.tv_register);
        tv_authorCenter.setOnClickListener(listener);
        tv_attention.setOnClickListener(listener);
        tv_homePager.setOnClickListener(listener);
        tv_login.setOnClickListener(listener);
        tv_register.setOnClickListener(listener);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_home_page:{

                break;
            }
            case R.id.tv_author_center:{

                break;
            }
            case R.id.tv_login:{

                break;
            }
            case R.id.tv_register:{

                break;
            }
            case R.id.tv_attention:{

                break;
            }
        }
    }

    public void startActivity(Activity activity,Class<? extends BaseActivity> clas){
        Intent intent=new Intent(activity,clas);
        startActivity(intent);
    }

    public abstract P initPresenter();

    @Override
    public void dismissLoadingDialog() {

    }

    @Override
    public void showloadingDialog(String msg) {

    }
}
