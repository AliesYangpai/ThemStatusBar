package org.alie.themstatusbar;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

import org.alie.themstatusbar.target.KitkatActivity;
import org.alie.themstatusbar.target.LollipopActivity;
import org.alie.themstatusbar.target.TargetCompatActivity;

/**
 * 部分参考：
 * https://github.com/laobie/StatusBarUtil/blob/master/library/src/main/java/com/jaeger/library/StatusBarUtil.java
 */
public class MainActivity extends AppCompatActivity implements OnClickListener {

    private Button btn1;
    private Button btn2;
    private Button btn3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initListener();


    }

    private void initView(){
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
    }
    private void initListener(){
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1: // 4.4 - 5.0 版本之间，如何进行适配

                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT &&
                Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    openActivity(KitkatActivity.class);
                }else {
                    Toast.makeText(this,"当前API非[4.4，5.0)",Toast.LENGTH_LONG).show();
                }

                break;
            case R.id.btn2:
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    openActivity(LollipopActivity.class);
                } else {
                    Toast.makeText(this,"当前API小于5.0",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.btn3:
                openActivity(TargetCompatActivity.class);
                break;
        }
    }


    private void openActivity(Class<?> mClass) {
        Intent intent = new Intent(this,mClass);
        startActivity(intent);
    }
}
