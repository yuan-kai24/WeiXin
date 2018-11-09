package com.imooc.po;

import com.baidu.aip.speech.AipSpeech;
import com.imooc.util.WeiXinUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DownloadVoice {
    public static final String APP_ID = "14724955";
    public static final String API_KEY = "7scZMZVVH6qVeMtEcyeMhmPU";
    public static final String SECRET_KEY = "9eaV3RvGkFC3PwActuqLefyCvv0F5jSf";
    public static AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        // 调用接口
        public static void main(String[] args) {
            DownloadVoice.asr();
        }

    public static byte [] getVoice(Map<String, String> map){
        System.out.println("================================================================");
        String mediaId = map.get("MediaId");
        byte[] bytes = new byte[0];
        try {
            bytes = SendGET(mediaId).getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return bytes;

    }
    public static String SendGET(String mediaId){
        String result="";//访问返回结果
        try {
            String url = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+WeiXinUtil.getAccessToken().getToken()+"&media_id="+mediaId;
            URL realurl=new URL(url);
            System.out.println("地址:"+url);
            //打开连接
            URLConnection connection=  realurl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            //建立连接
            connection.connect();
            // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段，获取到cookies等
            // -----------------------------------------------------------------------------
            List<String> strings = map.get("Content-disposition");
            String s = strings.get(0);
            String info = s.substring(s.indexOf("\"")+1,s.lastIndexOf("\""));
            // -----------------------------------------------------------------------------
            // 定义 BufferedReader输入流来读取URL的响应
            InputStream stream = connection.getInputStream();
            byte [] bytes = new byte[1024*1024*8];

            int read = stream.read(bytes);
            while (read != -1) {
                System.out.println(bytes);
                stream.read(bytes);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(result);
        return result;
    }



    public static String asr()
    {
        // 对本地语音文件进行识别
        String path = "F:\\IDEA_Java\\WeiXinX\\web\\resource\\5.amr";
        HashMap<String,Object> option = new HashMap<>();
        option.put("cuid","jsdahfjkasdhfkjafnjlad");
        option.put("dev_pid",1537);
        JSONObject asrRes = client.asr(path, "amr", 8000, option);
        JSONArray result = asrRes.getJSONArray("result");


        return result.get(0).toString();

    }

    public static String asr(byte [] bytes){
            String lan = "语音占位";
        HashMap<String,Object> option = new HashMap<>();
        option.put("cuid","jsdahfjkasdhfkjafnjlad");
        option.put("dev_pid",1837);
        JSONObject asrRes2 = client.asr(bytes, "amr", 8000,null);

            return lan;

    }
}
