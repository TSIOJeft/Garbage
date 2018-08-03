package com.fan.garbage.data;

import android.util.Log;

import com.fan.garbage.adpater.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import rx.Subscription;

public class BmobDataGet {
    //暂时弃用
    public ArrayList getBmobData(final RecyclerViewAdapter adapter, int l, int s, final int type) {
        final ArrayList<String> datas = new ArrayList<>();
        BmobQuery<BmobDataObject> data = new BmobQuery<>();
        data.setLimit(l).setSkip(s).order("-updatedAt")
                .findObjects(new FindListener<BmobDataObject>() {
                    @Override
                    public void done(List<BmobDataObject> list, BmobException e) {
                        if (e != null) {
                            Log.e("data>----------", e.getErrorCode() + e.getMessage());
                            return;
                        }
                        for (BmobDataObject dataObject : list) {
                            if (type == 0) datas.add(dataObject.getTitle());
                            if (type == 1) datas.add(dataObject.getContent());
                            if (type == 2) datas.add(dataObject.getPic());
                            if (type == 3) datas.add(dataObject.getBs());
                            if (type == 4) datas.add(dataObject.getUrl());
                        }
                        //经过测试不更新不会添加数据
                        adapter.notifyDataSetChanged();
                    }
                });
        return datas;
    }
}
