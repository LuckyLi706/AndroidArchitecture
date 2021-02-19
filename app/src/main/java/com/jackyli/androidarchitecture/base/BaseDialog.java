package com.jackyli.androidarchitecture.base;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * author : lijie
 * date : 2019/11/26 9:05
 * e-mail : jackyli706@gmail.com
 * description :
 */
public abstract class BaseDialog {

    protected Dialog dialog;
    //private Unbinder unbinder;

    protected void initView(Context context) {
        if (dialog != null) {
            //unbinder = ButterKnife.bind(this, dialog);
            //tv_Title.setText(getTitle());
        }
    }

    public void closeDialog() {
        if (dialog != null) {
            //unbinder.unbind();
            dialog.dismiss();
        }
    }


    //添加标题栏
    protected abstract String getTitle();


    /**
     * 隐藏输入软键盘
     *
     * @param context
     * @param view
     */
    public static void hideInputManager(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (view != null && imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);  //强制隐藏
        }
    }
}