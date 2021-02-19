package com.jackyli.androidarchitecture.model;

import com.jackyli.androidarchitecture.api.ApiService;
import com.jackyli.androidarchitecture.contract.LoginContract;
import com.jackyli.androidarchitecture.db.AppDataBase;
import com.jackyli.androidarchitecture.manager.RetrofitManager;
import com.jackyli.androidarchitecture.model.entities.User;

import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import okhttp3.ResponseBody;

/**
 * author : lijie
 * date : 2019/11/19 11:12
 * e-mail : jackyli706@gmail.com
 * description : 登录的M层
 */
public class LoginModel implements LoginContract.IModel {

    @Override
    public Maybe<List<User>> loginDb(String name) {
        return AppDataBase.getInstance().getUserDao().getUser2(name);
    }

    @Override
    public Observable<ResponseBody> loginNetwork(String userName, String password) {
        return RetrofitManager.get().create(ApiService.class).loginNetwork(userName,password);
    }
}
