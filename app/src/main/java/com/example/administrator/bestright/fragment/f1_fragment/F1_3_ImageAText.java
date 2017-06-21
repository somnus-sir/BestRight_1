package com.example.administrator.bestright.fragment.f1_fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bestright.R;
import com.example.administrator.bestright.bean.ReceivedInfo_f13;
import com.example.administrator.bestright.helper.HttpHelper;
import com.example.administrator.bestright.utils.SPUtils;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class F1_3_ImageAText extends Fragment {
    public ReceivedInfo_f13 receivedInfo;
    private int page = 1;
    private PullToRefreshListView plistView;
    private F1_3_ImageAText.lvAdapter lvAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1_3_imageatext, container, false);
        plistView = (PullToRefreshListView) view.findViewById(R.id.ptfListView_f1_3);
        lvAdapter = new lvAdapter();
        plistView.setAdapter(lvAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        page = SPUtils.getInt(getContext(), "page_f1_3", 1);

        //网络请求数据
        requestData();

        //监听下拉上拉
        plistView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                page++;//更改条件
                requestData();//重新请求数据
                Toast.makeText(getContext(), "刷新成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {


            }
        });
    }

    /**
     * 网络请求数据
     */
    private void requestData() {
        String url = "http://japi.juhe.cn/joke/content/text.from?key=e50b7ff4c54d3becd336cf07b0e5eb33&page="+page+"&pagesize=10";
        HttpHelper httpHelper = new HttpHelper();
        httpHelper.execGet(url, new HttpHelper.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                //可以更新UI
                Gson gson = new Gson();

                //数据请求成功，数据解析
                receivedInfo = gson.fromJson(data, ReceivedInfo_f13.class);
                int error_code = receivedInfo.error_code;
                String content = receivedInfo.result.data.get(0).content;
                lvAdapter.notifyDataSetChanged();
                plistView.onRefreshComplete();
            }

            @Override
            public void onFail(Exception e) {
                e.printStackTrace();
                plistView.onRefreshComplete();
                Toast.makeText(getContext(), "网络异常", Toast.LENGTH_SHORT).show();
            }
        });
    }


    class lvAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            if (receivedInfo == null) {
                return 0;
            } else {
                return receivedInfo.result.data.size();
            }
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
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = View.inflate(getContext(), R.layout.item_f13_contains, null);
                viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_f13_contents);
                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //获取数据
            ReceivedInfo_f13.EntityResult.DataEntity dataEntity = receivedInfo.result.data.get(position);
            String content = dataEntity.content;
            //设置显示
            viewHolder.tvContent.setText(content);
            return convertView;
        }
    }

    public static class ViewHolder {
        public TextView tvContent;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SPUtils.putInt(getContext(),"page_f1_3",page);
    }
}
