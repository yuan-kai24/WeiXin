import com.baidu.translate.demo.TransApi;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Main {

    private static final String APP_ID = "20181110000232429";
    private static final String SECURITY_KEY = "Nm9CAAUr7E5883Qt8VDc";

    public static void main(String[] args) {
        TransApi api = new TransApi(APP_ID, SECURITY_KEY);

        String query = "你好世界";
        JSONObject jsonObject = JSONObject.fromObject(api.getTransResult(query, "auto", "en"));
        JSONArray trans_result = jsonObject.getJSONArray("trans_result");
        System.out.println(trans_result.getJSONObject(0).get("dst"));
    }

}
