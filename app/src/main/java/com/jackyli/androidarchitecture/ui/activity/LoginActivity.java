package com.jackyli.androidarchitecture.ui.activity;

import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.jackyli.androidarchitecture.R;
import com.jackyli.androidarchitecture.base.BaseMvpActivity;
import com.jackyli.androidarchitecture.contract.LoginContract;
import com.jackyli.androidarchitecture.db.AppDataBase;
import com.jackyli.androidarchitecture.model.entities.User;
import com.jackyli.androidarchitecture.presenter.LoginPresenter;

import java.util.List;

public class LoginActivity extends BaseMvpActivity<LoginPresenter> implements LoginContract.LoginView {

    private String mUserName;
    private String mPassWord;

    @Override
    protected LoginPresenter bindPresenter() {
        return new LoginPresenter();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void onLoginNetWorkSuccess() {
        AppDataBase.getInstance().getUserDao().insertOne(new User(mUserName, mPassWord));
        Toast.makeText(this, "网络登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginNetWorkFailed(String error) {
        Toast.makeText(this, "网络登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginDbSuccess() {
        Toast.makeText(this, "数据库登录成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginDbFailed(String error) {
        Toast.makeText(this, "数据库登录失败", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initView() {
        EditText et_UserName = findViewById(R.id.username);
        EditText et_PassWord = findViewById(R.id.password);
        Button btn_Login = findViewById(R.id.login);

        btn_Login.setOnClickListener(v -> {
            mUserName = et_UserName.getText().toString();
            mPassWord = et_PassWord.getText().toString();
            if (TextUtils.isEmpty(mUserName) || TextUtils.isEmpty(mPassWord)) {
                Toast.makeText(this, "用户名或者密码不能为空", Toast.LENGTH_SHORT).show();
            } else {
                List<User> userList = AppDataBase.getInstance().getUserDao().getUser(mUserName);
                if (userList == null || userList.size() == 0) {
                    mPresenter.loginNetwork(mUserName, mPassWord);
                } else {
                    mPresenter.loginDb(userList.get(0).getUserName(), userList.get(0).getPassWord());
                }
            }
        });
    }

    @Override
    public void initData() {
    }
}
