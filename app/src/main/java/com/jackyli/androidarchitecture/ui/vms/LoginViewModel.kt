package com.jackyli.androidarchitecture.ui.vms

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jackyli.androidarchitecture.model.UserInfo
import com.jackyli.androidarchitecture.repository.db.AppDataBase
import com.jackyli.androidarchitecture.repository.net.RetrofitManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.lang.Exception

/**
 *  利用协程来使用调度
 *
 *  GlobalScope是生命周期是process级别的，即使Activity或Fragment已经被销毁，协程仍然在执行。所以需要绑定生命周期。
 *  lifecycleScope只能在Activity、Fragment中使用，会绑定Activity和Fragment的生命周期
 *  viewModelScope只能在ViewModel中使用，绑定ViewModel的生命周期
 */
class LoginViewModel : ViewModel() {

    var liveDataLogin: MutableLiveData<Boolean> = MutableLiveData()
    var liveDataUser: LiveData<List<UserInfo>> = MutableLiveData()

    fun getLoginResult(): MutableLiveData<Boolean> {
        return liveDataLogin
    }

    fun getUserInfoFromDb(): LiveData<List<UserInfo>> {
        liveDataUser = AppDataBase.getInstance().getUserDao().getUsers()
        return liveDataUser
    }

    fun login(name: String, pass: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val retrofit2 = RetrofitManager()
                val execute = retrofit2.service.loginNetWork(name, pass).execute()
                val result = execute?.body()
                if (result?.result == "Success") {
                    liveDataLogin.postValue(true)

                    if (liveDataUser.value == null || liveDataUser.value?.size == 0) {
                        val userInfo = UserInfo(0, name, pass)
                        insertUser(userInfo)
                    }
                } else {
                    liveDataLogin.postValue(false)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun insertUser(userInfo: UserInfo) {
        viewModelScope.launch(Dispatchers.IO) {
            AppDataBase.getInstance().getUserDao().insertUser(userInfo)
        }
    }
}