package org.alie.themstatusbar.target;

import android.os.Bundle;


import org.alie.themstatusbar.R;

/**
 * 兼容 4.4-5.0 以及5.0以上的沉浸式
 */
public class TargetCompatActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_compat);
        setToolBarStyle(R.color.orange_deep);
    }
}
