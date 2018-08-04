package com.fan.garbage.page;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fan.garbage.R;
import com.fan.garbage.adpater.RecyclerViewAdapter;
import com.fan.garbage.data.BmobDataObject;
import com.fan.garbage.data.Data;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class Page3 extends Fragment {
    private RecyclerViewAdapter adapter;
    private RecyclerView recyclerView;
    private ArrayList<Data> datas = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        datas.clear();
        adapter = new RecyclerViewAdapter(getContext(), datas);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        addData(20,0);
        return view;
    }
    public void addData(int l, int s) {
        BmobQuery<BmobDataObject> query = new BmobQuery<>();
        query.addWhereEqualTo("type", 2);
        query.setLimit(l).setSkip(s).order("-updatedAt").findObjects(new FindListener<BmobDataObject>() {
            @Override
            public void done(List<BmobDataObject> list, BmobException e) {
                if (e != null) {
                    Log.e("data>----------", e.getErrorCode() + e.getMessage());
                }
                Data data;
                for (BmobDataObject dataObject : list) {
                    data = new Data();
                    data.title = dataObject.getTitle();
                    data.content = dataObject.getContent();
                    data.pic = dataObject.getPic();
                    data.bs = dataObject.getBs();
                    data.money = dataObject.getMoney();
                    data.url = dataObject.getUrl();
                    datas.add(data);
                }

                adapter.notifyDataSetChanged();
            }
        });
    }
}
