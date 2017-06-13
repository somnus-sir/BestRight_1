package com.example.administrator.bestright.factory;


import android.support.v4.app.Fragment;
import com.example.administrator.bestright.fragment.F1_BestRight;
import com.example.administrator.bestright.fragment.F2_Follow;
import com.example.administrator.bestright.fragment.F3_Find;
import com.example.administrator.bestright.fragment.F4_Message;
import com.example.administrator.bestright.fragment.F5_Me;

import java.util.HashMap;

public class FragmentFactory {
    public HashMap<Integer,Fragment> map = new HashMap<>();
    public Fragment CreateFragment(int position){
        Fragment fragment = null;
        fragment= map.get(position);
        if (fragment==null){
            switch (position){
                case 1:
                    fragment = new F1_BestRight();
                    break;
                case 2:
                    fragment = new F2_Follow();
                    break;
                case 3:
                    fragment = new F3_Find();
                    break;
                case 4:
                    fragment = new F4_Message();
                    break;
                case 5:
                    fragment = new F5_Me();
                    break;
            }
            map.put(position,fragment);
        }
        return fragment;
    }
}