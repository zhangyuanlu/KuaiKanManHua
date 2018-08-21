package com.zyl.kuaikan.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.zyl.kuaikan.R;
import com.zyl.kuaikan.base.BaseActivity;
import com.zyl.kuaikan.bean.UserBean;
import com.zyl.kuaikan.util.Utilities;

public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View{

    private EditText et_phone,et_pwd,et_setcode;
    private Button bt_getcode,bt_foregt,bt_login;
    private CheckBox ck_rember;
    private TextView bt_register;

    private CharSequence phone,passWord,remember,verifyCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();
    }

    private void initView(){
        et_phone=findViewById(R.id.et_phone);
        et_pwd=findViewById(R.id.et_pwd);
        et_setcode=findViewById(R.id.et_code);
        bt_getcode=findViewById(R.id.bt_codec);
        bt_foregt=findViewById(R.id.bt_forget);
        bt_login=findViewById(R.id.bt_login);
        bt_register=findViewById(R.id.bt_register);
        ck_rember=findViewById(R.id.ck_remember);
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

                break;
            }
            case R.id.bt_forget:{

                break;
            }
            case R.id.bt_login:{
                phone=et_phone.getText();
                passWord=et_pwd.getText();
                remember=ck_rember.isChecked()?"1":"0";
                presenter.verifyLogin(phone,passWord);
                break;
            }
            case R.id.bt_register:{

            }
            default:
                break;
        }
    }

    @Override
    public void successLogin(UserBean user) {

    }

    @Override
    public void failedLogin(int resultCode) {

    }

    @Override
    public void verifyResult(int resultCode) {
        switch (resultCode){
            case LoginPresenter.WRONG_PHONENUM:{

                break;
            }
            case LoginPresenter.WRONG_PASSWORD:{

                break;
            }
            case LoginPresenter.LEGAL_PHONE_AND_PWD:{
                presenter.tryToLogin(phone.toString(),passWord.toString(),remember.toString(),null);
                break;
            }
            default:
                break;
        }
    }
}
