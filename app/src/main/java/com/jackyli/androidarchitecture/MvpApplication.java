package com.jackyli.androidarchitecture;

import android.app.Application;
import android.content.Context;

public class MvpApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
