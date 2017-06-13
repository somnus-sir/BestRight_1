package com.example.administrator.bestright.factory;


import android.support.v4.app.Fragment;
import com.example.administrator.bestright.fragment.fragment_f1.F1_1_Recommend;
import com.example.administrator.bestright.fragment.fragment_f1.F1_2_Video;
import com.example.administrator.bestright.fragment.fragment_f1.F1_3_ImageAText;

import java.util.HashMap;

public class FragmentFactory_F1 {
    public HashMap<Integer,Fragment> map = new HashMap<>();
    public Fragment CreateFragment(int position){
        Fragment fragment = null;
        fragment= map.get(position);
        if (fragment==null){
            switch (position){
                case 0:
                    fragment = new F1_1_Recommend();
                    break;
                case 1:
                    fragment = new F1_2_Video();
                    break;
                case 2:
                    fragment = new F1_3_ImageAText();
                    break;
            }
            map.put(position,fragment);
        }
        return fragment;
    }
}