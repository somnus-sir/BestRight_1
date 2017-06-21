package com.example.administrator.bestright.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.administrator.bestright.R;
import com.example.administrator.bestright.factory.FragmentFactory_F4;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class F4_Message extends Fragment {

    private ViewPager vp;

    private String[] arr = new String[]{"提醒","私信"};
    private FragmentFactory_F4 fragmentFactory_f4;
    private TabLayout tabLayout;
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
        view = inflater.inflate(R.layout.f4_message, container, false);
        tabLayout = (TabLayout) view.findViewById(R.id.tl_f4msg);
        vp = (ViewPager) view.findViewById(R.id.vp_f4msg);

        initData();//初始化数据

        android.support.v4.app.FragmentManager sfm = getActivity().getSupportFragmentManager();
        MsgAdapter msgAdapter = new MsgAdapter(sfm);
        vp.setAdapter(msgAdapter);
        tabLayout.setupWithViewPager(vp);

        return view;
    }

    /**
     * 初始化数据
     */
    private void initData() {
        fragmentFactory_f4 = new FragmentFactory_F4();
    }


    class MsgAdapter extends FragmentStatePagerAdapter {

        public MsgAdapter(android.support.v4.app.FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentFactory_f4.CreateFragment(position);
        }

        @Override
        public int getCount() {
            return arr.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return arr[position];
        }
    }



}
