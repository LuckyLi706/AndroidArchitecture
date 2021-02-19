package com.jackyli.androidarchitecture.utils.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.ColorStateList;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.ColorInt;

import com.jackyli.androidarchitecture.R;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

/**
 * author : lijie
 * date : 2021/2/19 14:54
 * e-mail : jackyli706@gmail.com
 * description :
 */
public class ProgressBarUtil {
    private static Dialog dialog = null;
    private static View view;

    /**
     * 加载进度，可以设置进度条的着色
     *
     * @param context       上下文对象
     * @param loadInfoHints 加载进度提示内容
     * @param tintColor     进度条颜色
     */
    public static void showProgressBar(Context context, String loadInfoHints, @ColorInt int tintColor) {

        view = LayoutInflater.from(context).inflate(R.layout.progress_bar_view, null);
        TextView tv_load_progress_hint = view.findViewById(R.id.tv_load_progress_hint);
        // 设置加载进度提示内容
        if (!TextUtils.isEmpty(loadInfoHints)) {
            tv_load_progress_hint.setText(loadInfoHints);
        } else {
            tv_load_progress_hint.setText("加载中...");
        }
        MaterialProgressBar progressBar = view.findViewById(R.id.custom_material_circular);
        // 设置进度条着色颜色
        progressBar.setIndeterminateTintList(ColorStateList.valueOf(tintColor));
        showDialog(context);// 创建对话框展示自定义进度条
    }

    /**
     * 加载进度，默认进度条颜色：深灰色
     *
     * @param context       上下文对象
     * @param loadInfoHints 加载进度提示内容
     */
    public static void showProgressBar(Context context, String loadInfoHints) {
        isShowLoadingOrText = false;
        view = LayoutInflater.from(context).inflate(R.layout.progress_bar_view, null);
        TextView tv_load_progress_hint = view.findViewById(R.id.tv_load_progress_hint);
        // 设置加载进度提示内容
        if (!TextUtils.isEmpty(loadInfoHints)) {
            tv_load_progress_hint.setText(loadInfoHints);
        } else {
            tv_load_progress_hint.setText("加载中...");
        }
        showDialog(context);// 创建对话框展示自定义进度条
    }

    private static boolean isShowLoadingOrText = true;

    /**
     * @param context       上下文对象
     * @param loadInfoHints 提示内容
     */
    public static void showText(Context context, String loadInfoHints) {
        TextView tv_load_progress_hint = view.findViewById(R.id.tv_load_progress_hint);
        // 设置加载进度提示内容
        if (!TextUtils.isEmpty(loadInfoHints)) {
            tv_load_progress_hint.setText(loadInfoHints);
        } else {
            tv_load_progress_hint.setText("加载中...");
        }
    }

    public static void showLoadingOrText(Context context, String loadInfoHints) {
        ((Activity) context).runOnUiThread(() -> {
            if (isShowLoadingOrText) {
                showProgressBar(context, loadInfoHints);
            } else {
                showText(context, loadInfoHints);
            }
        });
    }

    /**
     * 显示自定义进度对话框
     *
     * @param context
     */
    private static void showDialog(Context context) {
        //View inflate = LayoutInflater.from(context).inflate(layout, null);
        //自定义dialog显示风格
        dialog = new Dialog(context, R.style.DialogCentre);
        //点击其他区域不消失
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.setContentView(view);

        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.width = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(wlp);
        dialog.show();
        //dialog.show();
    }

    /**
     * 进度框消失
     */
    public static void dismissProgressBar() {
        isShowLoadingOrText = true;
        if (dialog != null) {
            dialog.dismiss();
        }
    }

}
