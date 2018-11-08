package com.imooc.po;

import com.baidu.aip.speech.AipSpeech;
import com.imooc.util.MessageUtil;
import com.sun.deploy.net.HttpRequest;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class DownloadVoice {
    public static final String APP_ID = "14724955";
    public static final String API_KEY = "7scZMZVVH6qVeMtEcyeMhmPU";
    public static final String SECRET_KEY = "9eaV3RvGkFC3PwActuqLefyCvv0F5jSf";
    public static AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        // 调用接口


    public static void getVoice(HttpServletRequest req,Map<String, String> map){
        System.out.println("================================================================");
        String format = map.get("Format");
        String mediaId = map.get("MediaId");
        String video_url = map.get("MsgID");
        System.out.println("格式"+format);
        System.out.println("消息id"+mediaId);
        System.out.println(video_url);

        System.out.println("================================================================");
    }

    public static String asr()
    {
        // 对本地语音文件进行识别
        String path = "F:\\IDEA_Java\\WeiXinX\\web\\resource\\10792 (1).wav";
        HashMap<String,Object> option = new HashMap<>();
        option.put("cuid","jsdahfjkasdhfkjafnjlad");
        option.put("dev_pid",1936);
        JSONObject asrRes = client.asr(path, "wav", 16000, null);
        JSONArray result = asrRes.getJSONArray("result");
        System.out.println(result.get(0));
//        System.out.println(asrRes.get("result"));
        // 对语音二进制数据进行识别
//        byte[] data = Util.readFileByBytes(path);     //readFileByBytes仅为获取二进制数据示例
//        JSONObject asrRes2 = client.asr(data, "pcm", 16000, null);
//        System.out.println(asrRes2);

        return result.get(0).toString();

    }
}
