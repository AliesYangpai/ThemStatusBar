package org.alie.themstatusbar.target;

import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.alie.themstatusbar.R;

/**
 * 这是5.0以上的沉浸式适配方案
 */
public class LollipopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            // 状态栏
            getWindow().setStatusBarColor(ContextCompat.getColor(this,R.color.orange_deep));
            // 虚拟键盘
            getWindow().setNavigationBarColor(ContextCompat.getColor(this,R.color.orange_deep));

        }
        setContentView(R.layout.activity_lollipop);

    }
}
