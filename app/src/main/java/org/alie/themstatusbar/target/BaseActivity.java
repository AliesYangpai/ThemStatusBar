package org.alie.themstatusbar.target;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import org.alie.themstatusbar.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

    }



    public void setToolBarStyle(int styleColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // [4.4-5.0）
            // 1.先拿到状态栏高度 使用反射
            int statusHeight = getStatusHeight();
            // 2.创建一个占位statusBarView，它的高度就是 状态栏的高度
            ViewGroup decorView = (ViewGroup)getWindow().getDecorView();
            View statusBarView = new View(this);
            LinearLayout.LayoutParams params =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, statusHeight);
            statusBarView.setLayoutParams(params);
            statusBarView.setBackgroundColor(ContextCompat.getColor(this, styleColor));
            decorView.addView(statusBarView);
            ViewGroup parent = findViewById(android.R.id.content);
            for (int i = 0, count = parent.getChildCount(); i < count; i++) {
                View childView = parent.getChildAt(i);
                if (childView instanceof ViewGroup) {
                    childView.setFitsSystemWindows(true);
                    ((ViewGroup) childView).setClipToPadding(true);
                }
            }

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 状态栏
            getWindow().setStatusBarColor(ContextCompat.getColor(this, R.color.orange_deep));
            // 虚拟键盘
            getWindow().setNavigationBarColor(ContextCompat.getColor(this, R.color.orange_deep));

        }
    }

    /**
     * 通过反射 获取状态栏高度
     *
     * @return
     */
    private int getStatusHeight() {
        int height = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField("status_bar_height").get(object).toString();
            height = Integer.parseInt(heightStr);
            //dp--px
            height = getResources().getDimensionPixelSize(height);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return height;
    }

    /**
     * 是否含有底部虚拟键，按断逻辑是 getRealMetrics 与getMetricsz进行比较处理
     *
     * @return
     */
    private int hasNavagation() {
        Display display = getWindowManager().getDefaultDisplay();
        DisplayMetrics displayRealMetrics = new DisplayMetrics();
        DisplayMetrics displayMetrics = new DisplayMetrics();

        display.getRealMetrics(displayRealMetrics);// 包含底部导航栏
        display.getMetrics(displayMetrics);

        int realHeight = displayRealMetrics.heightPixels;
        int height = displayRealMetrics.heightPixels;

        int success = realHeight - height;

        return success;
    }


    /**
     * 获取虚拟键盘的高度，仍然使用反射处理
     */
    private int getNavagationHeight() {
        int height = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField("navigation_bar_height").get(object).toString();
            height = Integer.parseInt(heightStr);
            //dp--px
            height = getResources().getDimensionPixelSize(height);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }


        return height;
    }
}
