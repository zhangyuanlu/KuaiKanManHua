package com.zyl.kuaikan.login;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zyl.kuaikan.R;
import com.zyl.kuaikan.base.BaseActivity;
import com.zyl.kuaikan.bean.LoginUserBean;
import com.zyl.kuaikan.bean.UserBean;
import com.zyl.kuaikan.view.CountDwonButton;

import io.realm.Realm;

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View{
    private static final String TAG="LoginActivity";
    private EditText et_phone,et_pwd,et_setcode;
    private Button bt_foregt,bt_login;
    private CountDwonButton bt_getcode;
    private CheckBox ck_rember;
    private TextView bt_register;
    private LinearLayout layout_sms_code,layout_login_progress;

    private CharSequence phone,passWord,remember,verifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){
        super.initView(this);
        et_phone=findViewById(R.id.et_phone);
        et_pwd=findViewById(R.id.et_pwd);
        et_setcode=findViewById(R.id.et_code);
        bt_getcode=findViewById(R.id.bt_codec);
        bt_foregt=findViewById(R.id.bt_forget);
        bt_login=findViewById(R.id.bt_login);
        bt_register=findViewById(R.id.bt_register);
        ck_rember=findViewById(R.id.ck_remember);
        layout_sms_code=findViewById(R.id.layout_smscode);
        layout_login_progress=findViewById(R.id.layout_login_progress);
        bt_getcode.setOnClickListener(this);
        bt_foregt.setOnClickListener(this);
        bt_login.setOnClickListener(this);
        bt_register.setOnClickListener(this);
    }

    @Override
    public LoginContract.Presenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.bt_codec:{
                bt_getcode.startCountDown(this);
                presenter.getVerifyCode(et_phone.getText().toString(),"5");
                break;
            }
            case R.id.bt_forget:{

                break;
            }
            case R.id.bt_login:{
                phone=et_phone.getText();
                passWord=et_pwd.getText();
                remember=ck_rember.isChecked()?"1":"0";
                verifyCode=et_setcode.getText();
                presenter.verifyLogin(phone,passWord);
                break;
            }
            case R.id.bt_register:{

            }
            default:
                break;
        }
    }

    /**
     * 登录成功后保存关键信息
     * @param user 登录用户
     */
    @Override
    public void successLogin(LoginUserBean user) {
        super.successLogin(user);
        showToastMsg(getString(R.string.login_success));
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        UserBean bean = realm.where(UserBean.class).findFirst();
        bean.setName(et_phone.getText().toString());
        bean.setPassword(et_pwd.getText().toString());
        bean.setRemember(ck_rember.isChecked());
        realm.commitTransaction();

        finish();
    }

    @Override
    public void failedLogin(int resultCode) {
        Log.e(TAG,"resultCode="+resultCode);
        String msg=null;
        switch (resultCode){
            case LoginPresenter.LOGIN_FAILED_NEDD_CODE:{
                msg=getString(R.string.login_need_code);
                break;
            }
            case LoginPresenter.LOGIN_FAILED_EMPTY_CODE:{
                msg=getString(R.string.login_empty_code);
                break;
            }
            case LoginPresenter.LOGIN_FAILED_INVALID1_CODE:
            case LoginPresenter.LOGIN_FAILED_INVALID2_CODE:{
                msg=getString(R.string.login_invalid_code);
                break;
            }
            case LoginPresenter.LOGIN_FAILED_MANY_CODE:{
                msg=getString(R.string.login_wrong_code);
                break;
            }
            case LoginPresenter.LOGIN_FAILED_WRONG_PWD:{
                msg=getString(R.string.login_wrong_pwd);
                break;
            }
            default:
                break;
        }
        showToastMsg(msg);
        layout_sms_code.setVisibility(View.VISIBLE);
        layout_login_progress.setVisibility(View.GONE);
    }

    @Override
    public void verifyResult(int resultCode) {
        String msg=null;
        switch (resultCode){
            case LoginPresenter.WRONG_PHONENUM:
            case LoginPresenter.WRONG_PASSWORD:{
                msg=getString(R.string.verify_login_input);
                break;
            }
            case LoginPresenter.LEGAL_PHONE_AND_PWD:{
                presenter.tryToLogin(phone.toString(),passWord.toString(),remember.toString(),verifyCode.toString());
                layout_login_progress.setVisibility(View.VISIBLE);
                break;
            }
            default:
                break;
        }
        showToastMsg(msg);
    }

    @Override
    public void successGetCode() {
        Log.i(TAG,"get code success");
    }

    @Override
    public void failedGetCode() {
        Log.i(TAG,"get code failed");
    }
}
