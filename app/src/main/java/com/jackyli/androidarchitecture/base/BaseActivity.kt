package com.jackyli.androidarchitecture.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding

public abstract class BaseActivity<T : ViewDataBinding> : AppCompatActivity() {

    public lateinit var binding: T

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = initLayout()
        initView()
    }

    public abstract fun initLayout(): T

    public abstract fun initView()
}