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

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Page4 extends Fragment {
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    ArrayList<String> titles=new ArrayList<>();
    ArrayList<String>contents=new ArrayList<>();
    ArrayList<String>bs=new ArrayList<>();
    ArrayList<String>moneys=new ArrayList<>();
    ArrayList<String>urls=new ArrayList<>();
    ArrayList<String>pics=new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_page,container,false);  recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        titles.clear();
        contents.clear();
        pics.clear();
        bs.clear();
        urls.clear();
        moneys.clear();
        addData(10,0);
        adapter.setOnRecyclerViewClick(new RecyclerViewAdapter.OnRecyclerViewClick() {
            @Override
            public void itemClick(View v, int p) {
            }

            @Override
            public void buttonClick(View v, int p) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(urls.get(p)));
                startActivity(intent);
                Snackbar.make(v, "选择一个东西打开这个链接吧(｡･∀･)ﾉﾞ" + p, Snackbar.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    public void addData(int l,int s){
        BmobQuery<BmobDataObject> query = new BmobQuery<>();
        query.addWhereEqualTo("type",3);
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
}
