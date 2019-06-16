package org.alie.themstatusbar.util;

import android.app.Activity;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


/**
 * Created by Alie on 2019/6/16.
 * 类描述 沉浸式的工具类，4.4以下别想了，google不支持沉浸式，
 * 版本
 */
public class ImmersiveUtil {

    /**
     * 5.0以上设置沉浸式状态栏
     *
     * @param activity
     * @param color
     */
    public static void setColorLollipop(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 状态栏
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, color));
            // 虚拟键盘
            activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, color));

        }
    }

    /**
     * [4.4,5.0）设置沉浸式状态栏
     *
     * @param activity
     * @param color
     */
    public static void setColorKitkat(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            // 1.加入flag标记
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            // 2.反射获取状态栏高度
            int height = getStatusHeight(activity);

            // 3.将高度设置到占位view中
            View view = new View(activity);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            view.setLayoutParams(layoutParams);
            view.setBackgroundColor(ContextCompat.getColor(activity, color));
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(view);

            // 4.获取content的子布局并设置fitSystem属性
            FrameLayout frameLayout = activity.findViewById(android.R.id.content);
            for (int i = 0; i < frameLayout.getChildCount(); i++) {
                View childAt = frameLayout.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) childAt;
                    viewGroup.setFitsSystemWindows(true);
                    viewGroup.setClipChildren(true);
                }
            }
        }
    }


    /**
     * 适配[4.4,5.0)和 5.0以上的封装方法，
     *
     * @param activity
     * @param color
     */
    public static void setColorAll(Activity activity, int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {

            // 1.加入flag标记
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

            // 2.反射获取状态栏高度
            int height = getStatusHeight(activity);

            // 3.将高度设置到占位view中
            View view = new View(activity);
            LinearLayout.LayoutParams layoutParams =
                    new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
            view.setLayoutParams(layoutParams);
            view.setBackgroundColor(ContextCompat.getColor(activity, color));
            ViewGroup decorView = (ViewGroup) activity.getWindow().getDecorView();
            decorView.addView(view);

            // 4.获取content的子布局并设置fitSystem属性
            FrameLayout frameLayout = activity.findViewById(android.R.id.content);
            for (int i = 0; i < frameLayout.getChildCount(); i++) {
                View childAt = frameLayout.getChildAt(i);
                if (childAt instanceof ViewGroup) {
                    ViewGroup viewGroup = (ViewGroup) childAt;
                    viewGroup.setFitsSystemWindows(true);
                    viewGroup.setClipChildren(true);
                }
            }
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // 状态栏
            activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, color));
            // 虚拟键盘
            activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, color));

        }
    }


    // ************************************************************************************//

    /**
     * 通过反射 获取状态栏高度
     *
     * @return
     */
    private static int getStatusHeight(Activity activity) {
        int height = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField("status_bar_height").get(object).toString();
            height = Integer.parseInt(heightStr);
            //dp--px
            height = activity.getResources().getDimensionPixelSize(height);
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
     * 暂时不用
     *
     * @return
     */
    private static int hasNavagation(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
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
     * 获取虚拟键的高度，仍然使用反射处理
     * 暂时不用
     */
    private static int getNavagationHeight(Activity activity) {
        int height = -1;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField("navigation_bar_height").get(object).toString();
            height = Integer.parseInt(heightStr);
            //dp--px
            height = activity.getResources().getDimensionPixelSize(height);
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
