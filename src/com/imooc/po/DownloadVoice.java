package com.imooc.po;

import com.baidu.aip.speech.AipSpeech;
import com.imooc.util.WeiXinUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;

public class DownloadVoice {
    public static final String APP_ID = "14724955";
    public static final String API_KEY = "7scZMZVVH6qVeMtEcyeMhmPU";
    public static final String SECRET_KEY = "9eaV3RvGkFC3PwActuqLefyCvv0F5jSf";
    public static AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

    // 调用接口
    public static void main(String[] args) {
        DownloadVoice.asr();
    }

    public static byte[] SendGET(String mediaId) {
        byte[] addby = new byte[1024 * 8];
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=" + WeiXinUtil.getAccessToken().getToken() + "&media_id=" + mediaId;
            URL realurl = new URL(url);
            //打开连接
            URLConnection connection = realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
//            Map<String, List<String>> map = connection.getHeaderFields();
//            // 遍历所有的响应头字段，获取到cookies等
//            for (String s : map.keySet()) {
//                System.out.println(s + "-->" + map.get(s));
//            }
            // 定义 BufferedReader输入流来读取URL的响应
            InputStream stream = connection.getInputStream();
            byte[] bytes = new byte[1024 * 8];
            int len = 0;

            int read = stream.read(bytes);
            System.arraycopy(bytes, 0, addby, len, read);
            while (read != -1) {
                read = stream.read(bytes);
                if (read != -1) {
                    len = addby.length;
                    addby = Arrays.copyOf(addby, addby.length + read);
                    System.arraycopy(bytes, 0, addby, len, read);
                }

            }
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return addby;
    }


    public static String asr() {
        // 对本地语音文件进行识别
        String path = "F:\\IDEA_Java\\WeiXinX\\web\\resource\\5.amr";
        HashMap<String, Object> option = new HashMap<>();
        option.put("cuid", "kaikaizhizuo");
        option.put("dev_pid", 1537);
        JSONObject asrRes = client.asr(path, "amr", 8000, option);
        JSONArray result = asrRes.getJSONArray("result");


        return result.get(0).toString();

    }

    public static String asr(byte[] bytes) {
        String lan = "不识别！";
        HashMap<String, Object> option = new HashMap<>();
        option.put("cuid", "kaikaizhizuo");
        option.put("dev_pid", 1536);
        JSONObject asrRes2 = client.asr(bytes, "amr", 8000, null);
        return asrRes2.getJSONArray("result").get(0).toString();

    }
}
