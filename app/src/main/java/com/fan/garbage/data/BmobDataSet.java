package com.fan.garbage.data;

import android.util.Log;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class BmobDataSet {
    public static void DataSet(String t, String c, String p, String b, String u, String m, int type) {
        BmobDataObject dataObject = new BmobDataObject();
        dataObject.setBs(b);
        dataObject.setPic(p);
        dataObject.setContent(c);
        dataObject.setTitle(t);
        dataObject.setUrl(u);
        dataObject.setMoney(m);
        dataObject.setType(type);
        dataObject.save(new SaveListener<String>() {
            @Override
            public void done(String s, BmobException e) {
                if (e != null) {
                    Log.e("data>>------", e.getErrorCode() + e.getMessage());
                }
            }
        });
    }
}
