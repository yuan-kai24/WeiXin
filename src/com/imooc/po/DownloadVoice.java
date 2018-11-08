package com.imooc.po;

import com.baidu.aip.speech.AipSpeech;
import com.imooc.util.CheckUtil;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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

    public static void getVoice(HttpServletRequest req,Map<String, String> map){
        System.out.println("================================================================");
        String format = map.get("Format");
        String mediaId = map.get("MediaId");
        String stUrl = "https://api.weixin.qq.com/cgi-bin/media/get?access_token="+CheckUtil.token+"&media_id="+mediaId;
        System.out.println(SendGET(mediaId));
        try {
//            HttpClient aNew = new HttpClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("================================================================");

    }
    public static String SendGET(String mediaId){
        String result="";//访问返回结果
        BufferedReader read=null;//读取访问结果

        try {
            //创建url
//            URL realurl=new URL(url+"?"+param);
            URL realurl=new URL("https://api.weixin.qq.com/cgi-bin/media/get?access_token="+CheckUtil.token+"&media_id="+mediaId);
            System.out.println("地址:"+realurl.getAuthority());
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
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            // 定义 BufferedReader输入流来读取URL的响应
            read = new BufferedReader(new InputStreamReader(
                    connection.getInputStream(),"UTF-8"));
            String line;//循环读取
            while ((line = read.readLine()) != null) {
                result += line;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }  finally{
            if(read!=null){//关闭流
                try {
                    read.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

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
        // 对语音二进制数据进行识别
//        byte[] data = Util.readFileByBytes(path);     //readFileByBytes仅为获取二进制数据示例
//        JSONObject asrRes2 = client.asr(data, "pcm", 16000, null);
//        System.out.println(asrRes2);

        return result.get(0).toString();

    }
}
