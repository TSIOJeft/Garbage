package com.fan.garbage.page;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.fan.garbage.R;
import com.fan.garbage.adpater.RecyclerViewAdapter;
import com.fan.garbage.data.BmobDataObject;
import com.fan.garbage.data.Data;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

import static com.fan.garbage.data.ImgLoad.loadPic;

public class MainPageFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;
    private ArrayList<Data> datas = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        datas.clear();
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerViewAdapter(getContext(), datas);
        recyclerView.setAdapter(adapter);
        addData(30, 0);
        return view;
    }

    public void addData(int l, int s) {
        BmobQuery<BmobDataObject> query = new BmobQuery<>();
// 分类获取       query.addWhereEqualTo("type", 1);
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
