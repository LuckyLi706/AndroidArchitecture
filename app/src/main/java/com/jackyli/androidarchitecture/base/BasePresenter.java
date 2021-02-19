package com.jackyli.androidarchitecture.base;

import java.lang.ref.WeakReference;

/**
 * author : lijie
 * date : 2019/12/5 10:15
 * e-mail : jackyli706@gmail.com
 * description :
 */
public abstract class BasePresenter<V extends IBaseView> {
    private WeakReference<V> mViewRef;
    public V mView;

    void attachView(V view) {
        mViewRef = new WeakReference<V>(view);
        mView = mViewRef.get();
    }

    void detachView() {
        mViewRef.clear();
        mView = null;
    }

    public boolean isViewNull(String methodName) {
        if (mView == null) {
            return true;
        }
        return false;
    }
}
