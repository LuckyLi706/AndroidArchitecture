package com.jackyli.androidarchitecture

import android.app.Application
import android.content.Context

/**
 * 在kotlin中使用DataBinding遇到 Unresolved reference: databinding 的坑
 * https://blog.csdn.net/u012150124/article/details/80555665
 */
class MvvmApplication : Application() {

    companion object {

        private lateinit var context: Context

        public fun getContext(): Context {
            return context
        }
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}