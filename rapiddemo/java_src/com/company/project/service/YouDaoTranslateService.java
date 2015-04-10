package com.company.project.service;

import com.company.project.util.FileUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 使用有道翻译API翻译文本
 * Created by qince on 2015/4/8.
 */
public class YouDaoTranslateService {
    /**
     * 有道翻译API：
     * http://fanyi.youdao.com/openapi?path=data-mode
     */
    public final static String YOUDAO_TRANSLATE_URL = " http://fanyi.youdao.com/openapi.do?keyfrom=qcd-github-blog&key=607934831&type=data&doctype=json&version=1.1&q=<text>";

    /**
     * 翻译文本
     * @param text
     * @return
     */
    public static String translate(String text) throws IOException {
        // 一次翻译的文本长度不能超过200个字符，需要UTF8编码
        if (null != text && text.length() > 200) {
            return "文本过长，不能超过200字符";
        }

        String _url = YOUDAO_TRANSLATE_URL.replace("<text>",encodeUTF8(text));
        URL url = new URL(_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setUseCaches(true);
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        String content = FileUtil.readContentFromStream(inputStream,"utf8");
        System.out.println(content);

        JSONObject result = JSONObject.fromObject(content);
        if (result != null) {
            Integer errorCode = (Integer) result.get("errorCode");
            if (0 == errorCode) { // OK
                JSONArray translationArr = ((JSONArray)result.get("translation"));
                String translation = ((String)translationArr.get(0));
                System.out.println(text + ":\n" + translation);
                return translation;
            }
        }

        return content;
    }

    public static String encodeUTF8(String text) {
        try {
            return URLEncoder.encode(text,"utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    // 有道翻译使用说明
    public static String getUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("有道翻译使用说明").append("\n\n");
        buffer.append("回复:翻译+要翻译的文本即可").append("\n");
        buffer.append("例如：翻译你好").append("\n");
        buffer.append("回复“?”显示主菜单");
        return buffer.toString();
    }

//    public static void main(String[] args) {
//        System.out.println("有道翻译API");
//        try {
//            translate("我们来自同一个地方");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}
