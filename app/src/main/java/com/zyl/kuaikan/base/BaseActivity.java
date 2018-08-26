package com.zyl.kuaikan.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.zyl.kuaikan.R;
import com.zyl.kuaikan.applicaiton.MyApplication;
import com.zyl.kuaikan.bean.LoginUserBean;
import com.zyl.kuaikan.bean.UserBean;
import com.zyl.kuaikan.home.MainActivity;
import com.zyl.kuaikan.login.LoginActivity;

import io.realm.Realm;

public abstract class BaseActivity<P extends IBasePresenter> extends AppCompatActivity implements IBaseView,View.OnClickListener{
    private static final String TAG="BaseActivity";
    private static final int RESULTCODE_LOGIN_SUCCESS =100;
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
        tryToLogin();
    }

    /**
     * 尝试免密登录
     */
    public void tryToLogin(){
        Realm realm=Realm.getDefaultInstance();
        final UserBean userBean=realm.where(UserBean.class).findFirst();
        if(userBean!=null) {
            presenter.tryToLogin(userBean.getName(),userBean.getPassword(),"1",null);
        }
    }

    /**
     * 登录成功后拿到更新的用户信息更新UI
     * @param user 登录用户
     */
    @Override
    public void successLogin(LoginUserBean user) {
        MyApplication.isOnline=true;
        Intent intent=new Intent();
        intent.putExtra("name",user.getData().getUser().getNickname());
        setResult(RESULTCODE_LOGIN_SUCCESS,intent);
        tv_register.setVisibility(View.GONE);
        tv_login.setText(user.getData().getUser().getNickname());
    }

    @Override
    public void failedLogin(int resultCode) {
        Log.e(TAG,"failedLogin,resultCode="+resultCode);
        tv_register.setVisibility(View.VISIBLE);
        tv_login.setText(getString(R.string.login));
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
                Intent intent=new Intent(this,MainActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.tv_author_center:{

                break;
            }
            case R.id.tv_login:{
                if(!MyApplication.isOnline) {
                    startActivity(this, LoginActivity.class, RESULTCODE_LOGIN_SUCCESS);
                }else{
                    MyApplication.isOnline=false;
                    tv_register.setVisibility(View.VISIBLE);
                    tv_login.setText(getString(R.string.login));
                    Realm realm=Realm.getDefaultInstance();
                    realm.beginTransaction();
                    realm.where(UserBean.class).findAll().deleteAllFromRealm();
                    realm.commitTransaction();
                }
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
    public void startActivity(Activity activity,Class<? extends BaseActivity> clas,int requestCode){
        Intent intent=new Intent(activity,clas);
        startActivityForResult(intent,requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode== RESULTCODE_LOGIN_SUCCESS &&requestCode== RESULTCODE_LOGIN_SUCCESS){
            String name=data.getStringExtra("name");
            tv_login.setText(name);
            tv_register.setVisibility(View.GONE);
        }
    }

    public abstract P initPresenter();

    @Override
    public void showToastMsg(String msg) {
        if(!TextUtils.isEmpty(msg)){
            Toast.makeText(context,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
