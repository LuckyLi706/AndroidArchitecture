package com.jackyli.androidarchitecture.ui.vms;

import android.annotation.SuppressLint;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.jackyli.androidarchitecture.api.ApiService;
import com.jackyli.androidarchitecture.model.Result;
import com.jackyli.androidarchitecture.model.UserInfo;
import com.jackyli.androidarchitecture.repository.db.AppDataBase;
import com.jackyli.androidarchitecture.repository.net.RetrofitManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<Boolean> mLDLogin;
    private final LiveData<List<UserInfo>> listLiveData = new MutableLiveData<>();

    public MutableLiveData<Boolean> getLoginResult() {
        if (mLDLogin == null) {
            mLDLogin = new MutableLiveData<>();
        }
        return mLDLogin;
    }

    public void login(String name, String pass) {
        Call<Result> call = RetrofitManager.get().create(ApiService.class).loginNetwork(name, pass);
        call.enqueue(new Callback<Result>() {
            @Override
            public void onResponse(Call<Result> call, Response<Result> response) {
                Result result = response.body();
                assert result != null;
                if (result.getResult().equals("Success")) {
                    mLDLogin.postValue(true);
                    UserInfo userInfo = new UserInfo();
                    userInfo.setUserName(name);
                    userInfo.setPassWord(pass);
                    if (listLiveData.getValue() != null && listLiveData.getValue().size() > 0) {
                        insertDb(userInfo);
                    }
                } else {
                    mLDLogin.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                mLDLogin.postValue(false);
            }
        });
    }

    public LiveData<List<UserInfo>> getUser() {
        return AppDataBase.getInstance().getUserDao().getUsers();
    }

    @SuppressLint("CheckResult")
    public void insertDb(UserInfo userInfo) {
        //可以考虑使用线程池或者rxjava来做
        AppDataBase.getInstance().getUserDao().addUser(userInfo).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(integer -> {

        }, throwable -> {

        });
    }
}
