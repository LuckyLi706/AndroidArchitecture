package com.jackyli.androidarchitecture.base;

import android.os.Bundle;
import android.view.KeyEvent;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.jackyli.androidarchitecture.utils.ToastUtil;
import com.jackyli.androidarchitecture.utils.view.*;

public abstract class BaseMvpActivity<T extends BasePresenter> extends BaseActivity implements IBaseView {

    protected T mPresenter;


    @SuppressWarnings("unchecked")
    @Override
    protected void init(Bundle savedInstanceState) {
        mPresenter = bindPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    protected abstract T bindPresenter();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
    }

    //打印Toast
    @Override
    public void showToast(String message) {
        ToastUtil.showToast(message);
    }

    @Override
    public void hideLoading() {
        ProgressBarUtil.dismissProgressBar();
    }

    @Override
    public void showLoading(String message) {
        ProgressBarUtil.showProgressBar(this, message);
    }

    public void showLoadingOrText(String message) {
        ProgressBarUtil.showLoadingOrText(this, message);
    }

    private long firstTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        long secondTime = System.currentTimeMillis();
        if (keyCode == KeyEvent.KEYCODE_BACK) { // 监控/拦截/屏蔽返回键
            if (secondTime - firstTime < 2000) {
                finish();
                System.exit(-1);
            } else {
                showToast("再按一次返回键退出");
                firstTime = System.currentTimeMillis();
            }
            return true;
        }
//        else if (keyCode == KeyEvent.KEYCODE_MENU) {   //点击菜单键
//            //
//        } else if (keyCode == KeyEvent.KEYCODE_HOME) {  //点击home键
//
//        }
        return super.onKeyDown(keyCode, event);
    }

    private long lastClickTime = 0;


    /**
     * 处理快速双击，多击事件，在TIME时间内只执行一次事件
     *
     * @return 是否在进行多次点击
     */
    public boolean isFastDoubleClick() {
        long currentTime = System.currentTimeMillis();
        long timeInterval = currentTime - lastClickTime;
        if (timeInterval < 20) {
            lastClickTime = System.currentTimeMillis();
            return true;
        } else {
            lastClickTime = System.currentTimeMillis();
            return false;
        }
    }


    /**
     * 切换fragment
     *
     * @param from        当前的fragment
     * @param to          切换的fragment
     * @param transaction 任务器
     * @param layout      布局
     */
    public void switchFragment(Fragment from, Fragment to,
                               FragmentTransaction transaction, int layout) {
        if (!to.isAdded()) { // 先判断是否被add过
            transaction.hide(from).add(layout, to).commit(); // 隐藏当前的fragment，add下一个到Activity中
        } else {
            transaction.hide(from).show(to).commit(); // 隐藏当前的fragment，显示下一个
        }
    }
}
