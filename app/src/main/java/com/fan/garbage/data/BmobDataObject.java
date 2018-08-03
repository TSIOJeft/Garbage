package com.fan.garbage.data;

import cn.bmob.v3.BmobObject;

public class BmobDataObject extends BmobObject {
    private String title;
    private String content;
    private String bs;
    private String pic;
    private String url;
    private String money;
    private int type;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public String getTitle() {
        return title;
    }

    public String getBs() {
        return bs;
    }

    public String getContent() {
        return content;
    }

    public String getPic() {
        return pic;
    }

    public String getUrl() {
        return url;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
