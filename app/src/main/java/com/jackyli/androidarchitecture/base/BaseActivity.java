package com.jackyli.androidarchitecture.base;


import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * author : lijie
 * date : 2019/11/19 10:04
 * e-mail : jackyli706@gmail.com
 * description :  所有Activity的基类
 */
public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setFullScreen();
        setContentView(getLayoutId());
        init(savedInstanceState);
        initView();
        initData();
    }

    public abstract int getLayoutId();

    //初始化View
    public void initView() {

    }

    //初始化数据
    public void initData() {

    }

    protected abstract void init(Bundle savedInstanceState);


    //设置全屏
    private void setFullScreen() {
        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);
    }
}
