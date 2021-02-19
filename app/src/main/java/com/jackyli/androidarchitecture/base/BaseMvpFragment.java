package com.jackyli.androidarchitecture.base;

import android.os.Bundle;

import com.jackyli.androidarchitecture.utils.ToastUtil;
import com.jackyli.androidarchitecture.utils.view.*;

public abstract class BaseMvpFragment<T extends BasePresenter> extends BaseFragment implements IBaseView {

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
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
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
        ProgressBarUtil.showProgressBar(mActivity, message);
    }

    public void showLoadingOrText(String message) {
        ProgressBarUtil.showLoadingOrText(mActivity, message);
    }
}
