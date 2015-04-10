package com.company.project.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.ByteBuffer;

/**
 * Created by qince on 2015/4/7.
 */
public class BaiduTranslateTest {
    public static void main(String[] args) {
        System.out.println("百度翻译服务测试");
        String appKey = "AAAAAAAAAAAAAAAAAAAAAAAA";
        String str = "你好，中国！";
        String url = "http://openapi.baidu.com/public/2.0/bmt/translate?client_id=APP_KEY&q=CONTENT&from=auto&to=auto";
        try {
            url = url.replace("APP_KEY",appKey).replace("CONTENT",urlEncodedUTF8(str));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.println(url);
        try {
            URL _url = new URL(url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) _url.openConnection();
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setUseCaches(true);
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();

            BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(inputStream,"UTF-8")));
            String temp = null;
            StringBuffer content = new StringBuffer();
            while ((temp = bufferedReader.readLine()) != null) {
                content.append(temp);
            }

            bufferedReader.close();
            inputStream.close();

            System.out.println(content.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String urlEncodedUTF8(String str) throws UnsupportedEncodingException {
       return URLEncoder.encode(str,"UTF-8");
    }
}
