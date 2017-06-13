package com.example.administrator.bestright.activity;

import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.administrator.bestright.R;
import com.example.administrator.bestright.factory.FragmentFactory;
import com.example.administrator.bestright.fragment.F1_BestRight;
import com.readystatesoftware.systembartint.SystemBarTintManager;


/**
 * RadioButton  默认选中问题
 */
public class MainActivity extends AppCompatActivity {

    private RadioGroup rg;
    private FrameLayout llFragment;
    private FragmentFactory fragmentFactory;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;
    private RadioButton rb5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Immersive();//沉浸式状态栏
        initView();//初始化view
        initData();//初始化数据

        //RadioGroup监听
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            Fragment fragment = null;
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //当前选中的button
                int checkedRadioButtonId = group.getCheckedRadioButtonId();
                if (rb1.getId() == checkedRadioButtonId) {
                    fragment = fragmentFactory.CreateFragment(1);
                } else if (rb2.getId() == checkedRadioButtonId) {
                    fragment = fragmentFactory.CreateFragment(2);
                } else if (rb3.getId() == checkedRadioButtonId) {
                    fragment = fragmentFactory.CreateFragment(3);
                } else if (rb4.getId() == checkedRadioButtonId) {
                    fragment = fragmentFactory.CreateFragment(4);
                } else if (rb5.getId() == checkedRadioButtonId) {
                    fragment = fragmentFactory.CreateFragment(5);
                }


                FragmentManager sfm = getSupportFragmentManager();
                FragmentTransaction fts = sfm.beginTransaction();
                FragmentTransaction replace = fts.replace(R.id.ll_fragment, fragment);
                fts.commit();

            }


        });


    }


    /**
     * 初始化数据
     */
    private void initData() {
        fragmentFactory = new FragmentFactory();

        //默认选中第一个
        rb1.setChecked(true);
        FragmentManager sfm = getSupportFragmentManager();
        FragmentTransaction fts = sfm.beginTransaction();
        Fragment fragment = fragmentFactory.CreateFragment(1);
        FragmentTransaction replace = fts.replace(R.id.ll_fragment, fragment);
        fts.commit();
    }

    /**
     * 初始化view
     */
    private void initView() {
        rg = (RadioGroup) findViewById(R.id.rg_main);
        rb1 = (RadioButton) findViewById(R.id.radiobt1);
        rb2 = (RadioButton) findViewById(R.id.radiobt2);
        rb3 = (RadioButton) findViewById(R.id.radiobt3);
        rb4 = (RadioButton) findViewById(R.id.radiobt4);
        rb5 = (RadioButton) findViewById(R.id.radiobt5);
        llFragment = (FrameLayout) findViewById(R.id.ll_fragment);
    }

    /**
     * 设置沉浸式状态栏
     */
    private void Immersive() {
        //当系统版本为4.4或者4.4以上时可以使用沉浸式状态栏
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
    }
}
