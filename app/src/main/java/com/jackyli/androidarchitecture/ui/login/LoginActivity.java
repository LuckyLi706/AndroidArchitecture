package com.jackyli.androidarchitecture.ui.login;

import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.jackyli.androidarchitecture.R;
import com.jackyli.androidarchitecture.base.BaseActivity;
import com.jackyli.androidarchitecture.databinding.ActivityLoginBinding;
import com.jackyli.androidarchitecture.model.UserInfo;
import com.jackyli.androidarchitecture.ui.vms.LoginViewModel;

import java.util.List;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private LoginViewModel loginViewModel;

    @Override
    public void initView() {
        binding.setClick(this);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        addObserver();
    }

    @Override
    public ActivityLoginBinding initLayout() {
        return DataBindingUtil.setContentView(this, R.layout.activity_login);  //绑定视图
    }

    public void click(View view) {
        loginViewModel.login(binding.username.getText().toString(), binding.password.getText().toString());
    }

    private void addObserver() {
        loginViewModel.getLoginResult().observe(this, this::showLoginResult);
        loginViewModel.getUser().observe(this, this::showUserPass);
    }

    private void showLoginResult(Boolean aBoolean) {
        if (aBoolean) {
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }

    private void showUserPass(List<UserInfo> userInfoList) {
        if (userInfoList != null && userInfoList.size() > 0) {
            UserInfo userInfo = userInfoList.get(0);
            binding.username.setText(userInfo.getUserName());
            binding.password.setText(userInfo.getPassWord());
        }
    }
}