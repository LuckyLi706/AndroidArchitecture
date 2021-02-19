package com.jackyli.androidarchitecture.base;

/**
 * author : lijie
 * date : 2019/12/5 10:47
 * e-mail : jackyli706@gmail.com
 * description :
 */
public interface IBaseView {

    void showLoading(String message);

    void hideLoading();

    void showToast(String message);

    void showLoadingOrText(String message);
}
