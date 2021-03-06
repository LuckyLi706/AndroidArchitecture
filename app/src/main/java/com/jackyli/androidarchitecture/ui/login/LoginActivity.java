package com.jackyli.androidarchitecture.ui.login;

import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import com.jackyli.androidarchitecture.R;
import com.jackyli.androidarchitecture.base.BaseActivity;
import com.jackyli.androidarchitecture.databinding.ActivityLoginBinding;
import com.jackyli.androidarchitecture.ui.vms.LoginViewModel;

public class LoginActivity extends BaseActivity<ActivityLoginBinding> {

    private LoginViewModel loginViewModel;
    private LiveData<Boolean> liveData;

    @Override
    public void initView() {
        binding.setClick(this);
        loginViewModel = new ViewModelProvider(this).get(LoginViewModel.class);

        observer();
    }

    @Override
    public ActivityLoginBinding initLayout() {
        return DataBindingUtil.setContentView(this, R.layout.activity_login);  //绑定视图
    }

    public void click(View view) {
        loginViewModel.login(binding.username.getText().toString(), binding.password.getText().toString());
    }

    private void observer() {
        loginViewModel.getLoginResult().observe(this, this::showLoginResult);
    }

    private void showLoginResult(Boolean aBoolean) {
        if (aBoolean) {
            Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
        }
    }
}