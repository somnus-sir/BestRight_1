package com.example.administrator.bestright.fragment;
import android.support.v4.app.Fragment;
import android.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bestright.R;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class F5_Me extends Fragment {

    private ListView lv;
    private String[] arrText = new String[]{"审帖专区","我发表的帖子","我的收藏夹","我的评论","我顶过的帖子","浏览历史","夜间模式"};
    private int[] arrImg = new int[]{R.mipmap.p_my_1,R.mipmap.p_my_2,R.mipmap.p_my_3,R.mipmap.p_my_4,R.mipmap.p_my_5,R.mipmap.p_my_6,R.mipmap.p_my_7,};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f5_me, container, false);
        lv = (ListView) view.findViewById(R.id.lv_f5me);
        f5Adapter f5Adapter = new f5Adapter();
        lv.setAdapter(f5Adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(), arrText[position], Toast.LENGTH_SHORT).show();
            }
        });




    }

    class f5Adapter extends BaseAdapter{
        @Override
        public int getCount() {
            return arrImg.length;
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
            ViewHolder viewHolder;
            if(convertView==null){
                viewHolder = new ViewHolder();
                convertView = View.inflate(getContext(),R.layout.item_f5_contains,null);
                viewHolder.tvText = (TextView) convertView.findViewById(R.id.tv_itemf5);
                viewHolder.ivIcon = (ImageView) convertView.findViewById(R.id.iv_itemf5_icon);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.tvText.setText(arrText[position]);
            viewHolder.ivIcon.setImageResource(arrImg[position]);
            return convertView;
        }
    }

     static class ViewHolder{
        public ImageView ivIcon;
        public TextView tvText;
    }


}
