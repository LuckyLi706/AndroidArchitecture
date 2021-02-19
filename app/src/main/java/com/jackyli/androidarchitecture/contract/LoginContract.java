package com.jackyli.androidarchitecture.contract;

import com.jackyli.androidarchitecture.base.BasePresenter;
import com.jackyli.androidarchitecture.base.IBaseView;
import com.jackyli.androidarchitecture.model.entities.User;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * author : lijie
 * date : 2019/11/19 11:11
 * e-mail : jackyli706@gmail.com
 * description : 登录模块的所有耗时业务
 */
public interface LoginContract {

    interface LoginView extends IBaseView {
        void onLoginNetWorkSuccess();

        void onLoginNetWorkFailed(String error);

        void onLoginDbSuccess();

        void onLoginDbFailed(String error);
    }

    interface IModel {

        Maybe<List<User>> loginDb(String name);

        Observable<ResponseBody> loginNetwork(String mobile, String password);
    }

    abstract class IPresenter extends BasePresenter<LoginView> {
        public abstract void loginNetwork(String mobile, String password);    //网络登录

        public abstract void loginDb(String mobile, String password);  //数据库登录
    }
}
