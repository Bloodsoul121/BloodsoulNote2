package com.example.cgz.bloodsoulnote2.net.httpurlconnection;

import android.os.Bundle;
import android.util.Log;

import com.example.cgz.bloodsoulnote2.R;
import com.example.cgz.bloodsoulnote2.base.BaseActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 在Android 2.2版本以及之前的版本使用HttpClient是较好的选择，而在Android 2.3版本及以后，
 * HttpURLConnection则是最佳的选择，它的API简单，体积较小，因而非常适用于Android项目。
 */
public class HttpUrlConnectionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_http_url_connection);
    }

    private void testPost(String urlAdress) {
        try {
            URL url = new URL(urlAdress);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //设置链接超时时间
            conn.setConnectTimeout(15000);
            //设置读取超时时间
            conn.setReadTimeout(15000);
            //设置请求参数
            conn.setRequestMethod("POST");
            //添加Header
            conn.setRequestProperty("Connection","Keep-Alive");
            //接收输入流
            conn.setDoInput(true);
            //传递参数时需要开启
            conn.setDoOutput(true);

            conn.connect();

            InputStream in = conn.getInputStream();
            int responseCode = conn.getResponseCode();
            String response = converStreamToString(in);
            Log.i("HttpUrlConnection", "请求状态码:" + responseCode + "\n请求结果:" + response);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String converStreamToString(InputStream is) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        StringBuffer sb = new StringBuffer();
        String line = null;
        while ((line = reader.readLine()) != null) {
            sb.append(line + "\n");
        }
        return sb.toString();
    }

}
