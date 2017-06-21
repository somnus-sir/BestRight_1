package com.example.administrator.bestright.factory;


import android.support.v4.app.Fragment;

import com.example.administrator.bestright.fragment.f1_fragment.F1_1_Recommend;
import com.example.administrator.bestright.fragment.f1_fragment.F1_2_Video;
import com.example.administrator.bestright.fragment.f1_fragment.F1_3_ImageAText;
import com.example.administrator.bestright.fragment.f4_fragment.F4_1_Message;
import com.example.administrator.bestright.fragment.f4_fragment.F4_2_privateLetter;

import java.util.HashMap;

public class FragmentFactory_F4 {
    public HashMap<Integer,Fragment> map = new HashMap<>();
    public Fragment CreateFragment(int position){
        Fragment fragment = null;
        fragment= map.get(position);
        if (fragment==null){
            switch (position){
                case 0:
                    fragment = new F4_1_Message();
                    break;
                case 1:
                    fragment = new F4_2_privateLetter();
                    break;
            }
            map.put(position,fragment);
        }
        return fragment;
    }
}