package com.imooc.po;

public class TextImgeNews {
    private String Title = "默认";
    private String Description = "暂无描述";
    private String PicUrl = "http://yuankai1024.ngrok.xiaomiqiu.cn/img/ewm_xzh.png";
    private String Url = "http://www.baidu.com";

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getPicUrl() {
        return PicUrl;
    }

    public void setPicUrl(String picUrl) {
        PicUrl = picUrl;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }
}
