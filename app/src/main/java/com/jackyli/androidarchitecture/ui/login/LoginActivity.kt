package com.jackyli.androidarchitecture.ui.login

import android.view.View
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jackyli.androidarchitecture.MvvmApplication
import com.jackyli.androidarchitecture.R
import com.jackyli.androidarchitecture.base.BaseActivity
import com.jackyli.androidarchitecture.databinding.ActivityLoginBinding
import com.jackyli.androidarchitecture.ui.vms.LoginViewModel
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity<ActivityLoginBinding>() {

    private lateinit var loginViewModel: LoginViewModel

    override fun initLayout(): ActivityLoginBinding {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        return binding
    }

    override fun initView() {
        binding.click = this
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        addObserver()
    }

    private fun addObserver() {
        loginViewModel.getLoginResult().observe(this, Observer {
            if (it) {
                Toast.makeText(MvvmApplication.getContext(), "登录成功", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(MvvmApplication.getContext(), "登录失败", Toast.LENGTH_SHORT).show();
            }
        })
    }

    fun click(v: View) {
        loginViewModel.login(username.text.toString(), password.text.toString())
    }
}