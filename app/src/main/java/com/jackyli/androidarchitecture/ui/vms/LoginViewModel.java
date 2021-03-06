package com.jackyli.androidarchitecture.ui.vms;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.jackyli.androidarchitecture.api.ApiService;
import com.jackyli.androidarchitecture.model.Result;
import com.jackyli.androidarchitecture.model.UserInfo;
import com.jackyli.androidarchitecture.repository.db.AppDataBase;
import com.jackyli.androidarchitecture.repository.net.RetrofitManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginViewModel extends ViewModel {

    public MutableLiveData<Boolean> mLDLogin;
    public MutableLiveData<Boolean> mLDLoading = new MutableLiveData<>();
    private LiveData<List<UserInfo>> listLiveData = new MutableLiveData<>();

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
                mLDLogin.postValue(result.getResult().equals("Success"));
            }

            @Override
            public void onFailure(Call<Result> call, Throwable t) {
                mLDLogin.postValue(false);
            }
        });
    }

    public LiveData<List<UserInfo>> getUser() {
        LiveData<List<UserInfo>> userDao = AppDataBase.getInstance().getUserDao().getUsers();
        return userDao;
    }
}
