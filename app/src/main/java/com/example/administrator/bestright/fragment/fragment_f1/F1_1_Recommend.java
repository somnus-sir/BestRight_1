package com.example.administrator.bestright.fragment.fragment_f1;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.example.administrator.bestright.R;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class F1_1_Recommend extends Fragment {

    private PullToRefreshListView ptflv;
    private ListView lv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1_1_recommend, container, false);
        lv = (ListView) view.findViewById(R.id.lv_F11);
        lv.setAdapter(new lvAdapter());
        return view;
    }

    class lvAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 10;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView==null) {
                convertView = View.inflate(getContext(), R.layout.item_f11_contains,null);
            }
            return convertView;
        }


    }

    public static class ViewHolder{



    }

}
