package org.alie.themstatusbar.target;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.alie.themstatusbar.R;
import org.alie.themstatusbar.util.ImmersiveUtil;

/**
 * 这是[4.4,5.0)的沉浸式适配方案
 */
public class KitkatActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kitkat);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            ImmersiveUtil.setColorKitkat(this,R.color.orange_deep);
        }
    }
}
