package com.jackyli.androidarchitecture.ui.vms

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackyli.androidarchitecture.api.ApiService
import com.jackyli.androidarchitecture.repository.net.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

public class LoginViewModel : ViewModel() {

    var liveDataLogin: MutableLiveData<Boolean> = MutableLiveData()

    public fun getLoginResult(): MutableLiveData<Boolean> {
        return liveDataLogin
    }

    public fun login(name: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var retrofit2 = RetrofitManager()
            val execute = retrofit2.service.loginNetWork(name, pass).execute()
            val result = execute?.body()
            if (result?.result == "success") {
                liveDataLogin.postValue(true)
            } else {
                liveDataLogin.postValue(false)
            }
        }
    }
}