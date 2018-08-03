package com.fan.garbage.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fan.garbage.R;
import com.fan.garbage.adpater.RecyclerViewAdapter;
import com.fan.garbage.data.BmobDataObject;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Main_page extends Fragment {
    private ArrayList<String> titles = new ArrayList<>();
    private ArrayList<String> contents = new ArrayList<>();
    private ArrayList<String> pics = new ArrayList<>();
    private ArrayList<String> bs = new ArrayList<>();
    private ArrayList<String> urls = new ArrayList<>();
    private ArrayList<String>moneys=new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(manager);
        titles.clear();
        contents.clear();
        pics.clear();
        bs.clear();
        adapter = new RecyclerViewAdapter(getContext(), titles, contents, pics, bs,moneys);
        adapter.setOnRecyclerViewClick(new RecyclerViewAdapter.OnRecyclerViewClick() {
            @Override
            public void itemClick(View v, int p) {
                Snackbar.make(v, "你点击了布局" + p, Snackbar.LENGTH_SHORT).show();
            }

            @Override
            public void buttonClick(View v, int p) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urls.get(p)));
                startActivity(intent);
                Snackbar.make(v, "选择一个东西打开这个链接吧(｡･∀･)ﾉﾞ" + p, Snackbar.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        addData(10, 0);
        return view;
    }

    private void addData(int l, int s) {
        BmobQuery<BmobDataObject> query = new BmobQuery<>();
        final ArrayList<String> data = new ArrayList<>();
        query.setLimit(l).setSkip(s).order("-updatedAt").findObjects(new FindListener<BmobDataObject>() {
            @Override
            public void done(List<BmobDataObject> list, BmobException e) {
                if (e != null) {
                    Log.e("data>----------", e.getErrorCode() + e.getMessage());
                }
                for (BmobDataObject dataObject : list) {
                    titles.add(dataObject.getTitle());
                    contents.add(dataObject.getContent());
                    pics.add(dataObject.getPic());
                    bs.add(dataObject.getBs());
                    urls.add(dataObject.getUrl());
                    moneys.add(dataObject.getMoney());
                }
                adapter.notifyDataSetChanged();
            }
        });
    }
//    public void test(){
//        Log.e("data",">------start");
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    Document doc= Jsoup.connect("https://uland.taobao.com/coupon/edetail?e=Mqiv9eArwucGQASttHIRqbRsxpJAB9hg1U8ALpQTU9sQzxWZZqjo%2BfVX3zvIuL7KZVST3oq4cbYdJP3Xafk2z79fwBwwUiqlshjd%2F6slxGTwYnPZvb1Jvm7PVn13QcLN%2FfsIcQx%2BbXUxcPPc3IMH9g%3D%3D&traceId=0be5bd3c15329820303235231e&activityId=8c49c128623443ef8e253997d39b22d0").get();
//                    Elements elements=doc.getElementById("J_root").select("body-wrap");
//                    Log.e("data",elements.toString());
//                } catch (IOException e) {
//                    Log.e("data","error>---"+e.getMessage());
//                }
//            }
//        }).start();
//    }
}
