package weizexu.baway.com.weizexu20170825.fragment;

import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.os.health.PackageHealthStats;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.XListView;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;
import weizexu.baway.com.weizexu20170825.DataMonth;
import weizexu.baway.com.weizexu20170825.Datas;
import weizexu.baway.com.weizexu20170825.Httputils;
import weizexu.baway.com.weizexu20170825.R;

/**
 * Created by asus on 2017/8/25.
 */

public class Fragment01  extends Fragment implements XListView.IXListViewListener{
    private View view;
    private XListView xlist;
    private   Mybase adapter;
    private String url="http://139.196.140.118:8080/get/%7B%22%5B%5D%22:%7B%22page%22:0,%22count%22:10,%22Moment%22:%7B%22content$%22:%22%2525a%2525%22%7D,%22User%22:%7B%22id@%22:%22%252FMoment%252FuserId%22,%22@column%22:%22id,name,head%22%7D,%22Comment%5B%5D%22:%7B%22count%22:2,%22Comment%22:%7B%22momentId@%22:%22%5B%5D%252FMoment%252Fid%22%7D%7D%7D%7D";
    private List<Datas> list = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.f01,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       xlist =  view.findViewById(R.id.xlist);
        getData(url);
        adapter = new Mybase();
        xlist.setAdapter(adapter);
        xlist.setPullRefreshEnable(true);
        xlist.setXListViewListener(this);
        xlist.setPullLoadEnable(true);

    }

    //刷新的方法；
    @Override
    public void onRefresh() {
        list.clear();
        getData(url);
        xlist.stopRefresh();
    }
    //加载的方法
    @Override
    public void onLoadMore() {
//        支持滑动到底部自动分页加载
        getData(url);
        adapter.notifyDataSetChanged();
        xlist.stopLoadMore();
    }

    class Mybase extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            Viewholder holder;
            if(view==null)
            {
                view = View.inflate(getContext(),R.layout.mybase,null);
                holder = new Viewholder();
                holder.image = view.findViewById(R.id.image);
                holder.image2 = view.findViewById(R.id.image2);
                holder.tv = view.findViewById(R.id.tv);
                holder.tv2 = view.findViewById(R.id.tv2);
                holder.tv3 = view.findViewById(R.id.tv3);
                view.setTag(holder);
            }else {
                 holder= (Viewholder) view.getTag();
            }
            holder.tv.setText(list.get(i).getName()+"");
            holder.tv2.setText(list.get(i).getDate()+""+"          "+"点赞"+"        "+"评论：    ");
            holder.tv3.setText(list.get(i).getName()+":"+list.get(i).getCommotList().get(i).getContent());
            Glide.with(getContext()).load(list.get(i).getHead()).into(holder.image);
            Glide.with(getContext()).load(list.get(i).getImage()).into(holder.image2);
            return view;
        }

        class Viewholder{
            ImageView image,image2;
            TextView tv,tv2,tv3;
        }
    }
    private void getData(String url) {
        Httputils httputils = new Httputils();
        httputils.sentRest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                try {
                    JSONObject object = new JSONObject(s);
                    JSONArray array = object.getJSONArray("[]");
                    List<Datas.Commot> commotList = new ArrayList<Datas.Commot>();
                    for (int i=0;i<array.length();i++){
                        Datas datas = new Datas();
                        JSONObject jsonObject = array.optJSONObject(i);
                        JSONObject user = jsonObject.optJSONObject("User");
                        JSONObject moment = jsonObject.optJSONObject("Moment");
                        JSONArray pictureList = moment.optJSONArray("pictureList");
                        for (int i2=0;i2<pictureList.length();i2++){
                            String string = pictureList.getString(i2);
                            datas.setImage(string);
                        }
                        String date = moment.getString("date");
                        String content = moment.getString("content");
                        String head = user.getString("head");
                        JSONArray array1 = jsonObject.optJSONArray("Comment[]");
                        for (int i1=0;i1<array1.length();i1++){
                            JSONObject object1 = array1.optJSONObject(i1);
                            String content1 = object1.getString("content");
                            int userId = object1.getInt("userId");
                            Datas.Commot commot = new  Datas.Commot();
                            commot.setContent(content1);
                            commot.setUserid(userId);
                            commotList.add(commot);
                        }
                        datas.setCommotList(commotList);
                        int id = user.getInt("id");
                        String name = user.getString("name");
                        datas.setName(name);
                        datas.setContent(content);
                        datas.setDate(date);
                        datas.setHead(head);
                        datas.setId(id);
                        list.add(datas);
                    }
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter.notifyDataSetChanged();
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }

}
