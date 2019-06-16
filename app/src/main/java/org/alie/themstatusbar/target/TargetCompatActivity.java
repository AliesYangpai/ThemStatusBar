package org.alie.themstatusbar.target;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import org.alie.themstatusbar.R;
import org.alie.themstatusbar.util.ImmersiveUtil;

/**
 * 兼容 4.4-5.0 以及5.0以上的沉浸式
 */
public class TargetCompatActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_compat);
        ImmersiveUtil.setColorAll(this,R.color.orange_deep);
    }
}
