package api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
 
public class ltpAPI {
    public static void main(String[] args)throws IOException{
        String api_key = "r2Q089S228yNtZ8jHYCArViv1UFYRUPCRaKMgpla";//api_key,申请账号后生成，这个账户每月有19G流量
        String pattern = "dp";//ws表示只分词，除此还有pos词性标注、ner命名实体识别、dp依存句法分词、srl语义角色标注、all全部
        String format  = "plain";//指定结果格式类型，plain表示简洁文本格式
        String result = "";
        String text = "我是南京大学的研究生";
        text = URLEncoder.encode(text, "utf-8");
        URL url = new URL("https://api.ltp-cloud.com/analysis/?"
                + "api_key=" + api_key + "&"
                + "text="    + text    + "&"
                + "format="  + format  + "&"
                + "pattern=" + pattern);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.connect();
        BufferedReader innet = new BufferedReader(new InputStreamReader(conn.getInputStream(),"utf-8"));
        String line;
        while ((line = innet.readLine())!=null){
            System.out.println(line);
        }
        innet.close();
    }
}
