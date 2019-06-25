package org.alie.themstatusbar.target;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import org.alie.themstatusbar.R;
import org.alie.themstatusbar.util.ImmersiveUtil;

public class TargetCompatPicActivity extends AppCompatActivity {

    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_target_compat_pic);
        ImmersiveUtil.setTranslucentAll(this);
        initView();
//        initData();
    }

    private void initView() {
        iv = findViewById(R.id.iv);
    }

    private void initData() {
        iv.setImageResource(R.drawable.arsenal);
    }
}
