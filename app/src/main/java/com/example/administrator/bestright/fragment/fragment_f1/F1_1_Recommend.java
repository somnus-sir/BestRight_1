package com.example.administrator.bestright.fragment.fragment_f1;

import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.bestright.bean.ReceivedInfo;
import com.example.administrator.bestright.helper.HttpHelper;
import com.example.administrator.bestright.utils.SPUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.google.gson.Gson;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.example.administrator.bestright.R;

/**
 * Created by Administrator on 2017/6/10 0010.
 */

public class F1_1_Recommend extends Fragment {

    private PullToRefreshListView ptflv;
    private ListView lv;
//    private TextView tv;
    public ReceivedInfo receivedInfo;
    private F1_1_Recommend.lvAdapter lvAdapter;
    private int page = 1;
    private PullToRefreshListView plistView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.f1_1_recommend, container, false);
        plistView = (PullToRefreshListView) view.findViewById(R.id.ptfListView);
        lvAdapter = new lvAdapter();
        plistView.setAdapter(lvAdapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //初始化数据
        page = SPUtils.getInt(getContext(), "page_f1_1", 1);

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
        String url = "http://japi.juhe.cn/joke/img/list.from?key=e50b7ff4c54d3becd336cf07b0e5eb33&page="+page+"&pagesize=10&sort=asc&time=1418745237";
        HttpHelper httpHelper = new HttpHelper();
        httpHelper.execGet(url, new HttpHelper.HttpCallback() {
            @Override
            public void onSuccess(String data) {
                //可以更新UI
                Gson gson = new Gson();

                //数据请求成功，数据解析
                receivedInfo = gson.fromJson(data, ReceivedInfo.class);
                int error_code = receivedInfo.error_code;
                String content = receivedInfo.result.data.get(0).content;
                String url1 = receivedInfo.result.data.get(0).url;
//                tv.setText(url1);
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
                convertView = View.inflate(getContext(), R.layout.item_f11_contains, null);
                viewHolder.tvContent = (TextView) convertView.findViewById(R.id.tv_f11_contents);
                viewHolder.sdv  = (SimpleDraweeView) convertView.findViewById(R.id.sdv_f11_gif);

                convertView.setTag(viewHolder);
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            //获取数据
            ReceivedInfo.EntityResult.DataEntity dataEntity = receivedInfo.result.data.get(position);
            String url = dataEntity.url;

            //设置显示
            viewHolder.tvContent.setText(dataEntity.content);

            //gif类型的动态图
            Uri aniImageUri2 = Uri.parse("http://images.cnfol.com//file//201603//mp35337118_1444701483338_2_201603250855265951.gif");
            //普通图片
            Uri aniImageUri1 = Uri.parse("http://images.cnfol.com//file//201603//fang_201603240925207041.jpg");
            Uri uri = Uri.parse(url);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .build();
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setImageRequest(request)
                    .setAutoPlayAnimations(true)
                    .build();
            viewHolder.sdv.setController(controller);
            return convertView;
        }
    }

    public static class ViewHolder {
        public TextView tvContent;
        public SimpleDraweeView sdv;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        SPUtils.putInt(getContext(),"page_f1_1",page);
    }
}
