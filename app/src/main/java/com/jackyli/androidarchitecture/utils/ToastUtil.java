package com.jackyli.androidarchitecture.utils;

import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import com.jackyli.androidarchitecture.MvpApplication;

/**
 * author : lijie
 * date : 2019/12/4 9:57
 * e-mail : jackyli706@gmail.com
 * description :
 */
public class ToastUtil {

    private static Toast toast;

    public static void showToast(String message) {

        //如果是主Looper直接发toast
        if (Looper.myLooper() == Looper.getMainLooper()) {
            Toast.makeText(MvpApplication.getContext(), message, Toast.LENGTH_SHORT).show();
            //子线程用MainLooper的Handler来更新UI
            //toast(message);
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(() -> Toast.makeText(MvpApplication.getContext(), message, Toast.LENGTH_SHORT).show());
        }
    }

    private static void toast(String message) {
        if (toast == null) {
            toast = Toast.makeText(MvpApplication.getContext(),
                    message,
                    Toast.LENGTH_SHORT);
        } else {
            toast.setText(message);
        }
        toast.show();
    }
}
