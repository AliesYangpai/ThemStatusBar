package org.alie.themstatusbar.target;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.alie.themstatusbar.R;
import org.alie.themstatusbar.util.ImmersiveUtil;

/**
 * 这是5.0以上的沉浸式适配方案
 */
public class LollipopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lollipop);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ImmersiveUtil.setColorLollipop(this,R.color.orange_deep);
        }
    }
}
