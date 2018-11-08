package com.imooc.util;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/*
微信工具类
 */
public class WeiXinUtil {
    public static final String appid = "wx72473653441c25d6";
    public static final String appsecret = "d8a28b197283b088efb34da4a91daa39";

    public static JSONObject doGetStr(String url){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(url);
        JSONObject jsonObject = null;
        try {
            HttpResponse resp = httpClient.execute(httpGet);
            HttpEntity entity = resp.getEntity();
            if(entity != null){
                String result = EntityUtils.toString(entity,"utf-8");
                jsonObject = JSONObject.fromObject(result);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public static JSONObject doPostStr(String url,String outStr){
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);
        JSONObject jsonObject = null;
        httpPost.setEntity(new StringEntity(outStr,"utf-8"));
        try {
            HttpResponse resp = httpClient.execute(httpPost);
            String result = EntityUtils.toString(resp.getEntity(),"utf-8");
            jsonObject = jsonObject.fromObject(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }
}
