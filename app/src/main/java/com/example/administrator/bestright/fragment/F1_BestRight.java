package com.example.administrator.bestright.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bestright.R;
import com.example.administrator.bestright.factory.FragmentFactory_F1;
import com.example.administrator.bestright.fragment.fragment_f1.F1_1_Recommend;

import java.util.zip.Inflater;

/**
 * 移动后出现消失现象，以前好像遇到过
 */
public class F1_BestRight extends Fragment {
    private ViewPager vp;
    private String[] contains = new String[]{"推荐", "视频", "图文"};
    private FragmentFactory_F1 fragmentFactory_f1;
    private TabLayout tabLayout;
    private VpAdapter vpAdapter;
    private View view;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //第二次以后进入都将原来的view移除
        if(view!=null){
            ViewGroup parent = (ViewGroup) view.getParent();
            if(parent!=null){
                parent.removeView(view);
            }
            return view;
        }
        //view
        view = inflater.inflate(R.layout.f1_bestright, container, false);
        vp = (ViewPager) view.findViewById(R.id.vp_f1);
        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);
        initData();

        //viewpager
        FragmentManager sfm = getActivity().getSupportFragmentManager();
        vpAdapter = new VpAdapter(sfm);
        vp.setAdapter(vpAdapter);
        tabLayout.setupWithViewPager(vp);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }




    class VpAdapter extends FragmentStatePagerAdapter {
        public VpAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentFactory_f1.CreateFragment(position);
        }

        @Override
        public int getCount() {
            return contains.length;
        }


        @Override
        public CharSequence getPageTitle(int position) {
            return contains[position];
        }
    }





    /**
     * 初始化数据
     */
    private void initData() {
        fragmentFactory_f1 = new FragmentFactory_F1();
    }


}
