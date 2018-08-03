package com.fan.garbage.page;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fan.garbage.R;
import com.fan.garbage.adpater.RecyclerViewAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainPageFragment extends Fragment {
    private RecyclerView recyclerView;
    private RecyclerViewAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_page, container, false);
        recyclerView = view.findViewById(R.id.recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        adapter = new RecyclerViewAdapter(getContext());
        recyclerView.setAdapter(adapter);
//        addData(10, 0);
        adapter.addData(10, 0);
        return view;
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
