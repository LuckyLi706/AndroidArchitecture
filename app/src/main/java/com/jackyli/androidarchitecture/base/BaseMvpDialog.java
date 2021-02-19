package com.jackyli.androidarchitecture.base;

import android.content.Context;
import android.widget.Toast;

import com.jackyli.androidarchitecture.MvpApplication;
import com.jackyli.androidarchitecture.utils.view.*;

/**
 * author : lijie
 * date : 2019/12/6 9:53
 * e-mail : jackyli706@gmail.com
 * description :
 */
public abstract class BaseMvpDialog<T extends BasePresenter> extends BaseDialog implements IBaseView {


    public T mPresenter;

    @SuppressWarnings("unchecked")
    @Override
    protected void initView(Context context) {
        super.initView(context);
        this.context = context;
        if (dialog != null) {
            mPresenter = bindPresenter();
            if (mPresenter != null) {
                mPresenter.attachView(this);
            }
        }
    }

    protected abstract void initData();

    //关闭dialog
    @Override
    public void closeDialog() {
        super.closeDialog();
        mPresenter.detachView();
    }

    //绑定Presenter
    protected abstract T bindPresenter();

    private Context context;

    protected void getContext(Context context) {
        this.context = context;
    }


    @Override
    public void hideLoading() {
        ProgressBarUtil.dismissProgressBar();
    }

    @Override
    public void showLoading(String message) {
        ProgressBarUtil.showProgressBar(context, message);
    }

    public void showLoadingOrText(String message) {
        ProgressBarUtil.showLoadingOrText(context, message);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(MvpApplication.getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private long lastClickTime = 0;

    /**
     * 处理快速双击，多击事件，在TIME时间内只执行一次事件
     *
     * @return 是否在多机
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
}
