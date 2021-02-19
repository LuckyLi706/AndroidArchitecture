package com.jackyli.androidarchitecture.presenter;

import android.annotation.SuppressLint;

import com.jackyli.androidarchitecture.MvpApplication;
import com.jackyli.androidarchitecture.R;
import com.jackyli.androidarchitecture.contract.LoginContract;
import com.jackyli.androidarchitecture.model.LoginModel;
import com.jackyli.androidarchitecture.utils.EncryptUtil;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * author : lijie
 * date : 2019/11/19 11:12
 * e-mail : jackyli706@gmail.com
 * description : 登录模块的P层
 */
public class LoginPresenter extends LoginContract.IPresenter {

    private final LoginModel loginModel;

    public LoginPresenter() {
        loginModel = new LoginModel();
    }

    @SuppressLint("CheckResult")
    @Override
    public void loginNetwork(String mobile, String password) {
        mView.showLoadingOrText("登录中...");
        loginModel.loginNetwork(mobile, password).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(responseBody -> {
                    if (mView == null) {
                        return;
                    }
                    mView.hideLoading();
                    mView.onLoginNetWorkSuccess();
                }, throwable -> {
                    if (mView == null) {
                        return;
                    }
                    mView.hideLoading();
                    if (throwable == null) {
                        mView.onLoginNetWorkFailed("登录失败");
                    } else {
                        mView.onLoginNetWorkFailed(throwable.getMessage());
                    }
                });
    }

    @SuppressLint("CheckResult")
    @Override
    public void loginDb(String mobile, String password) {
        loginModel.loginDb(mobile).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(users -> {
                    if (mView == null) {
                        return;
                    }
                    if (users.size() > 0) {
                        if (users.get(0).getPassWord().equals(password)) {
                            mView.onLoginDbSuccess();
                        } else {
                            mView.onLoginDbFailed(MvpApplication.getContext().getString(R.string.user_and_pass_error));
                        }
                    } else {
                        mView.onLoginDbFailed(MvpApplication.getContext().getString(R.string.user_and_pass_error));
                    }
                }, throwable -> {
                    if (mView == null) {
                        return;
                    }
                    if (throwable == null) {
                        mView.onLoginDbFailed(MvpApplication.getContext().getString(R.string.user_and_pass_error));
                    } else {
                        mView.onLoginDbFailed(throwable.getMessage());
                    }
                });
    }
}
