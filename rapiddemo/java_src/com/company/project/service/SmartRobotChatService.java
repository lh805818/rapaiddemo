package com.company.project.service;

import com.company.project.util.FileUtil;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * 赛科智能机器人（http://dev.skjqr.com/web/member/）
 * Created by qince on 2015/4/8.
 */
public class SmartRobotChatService {
    public final static String SMART_ROBOT_CHAT_URL = "http://dev.skjqr.com/api/weixin.php?email=qincidong@qq.com&appkey=88a905f936a74ae0d42f450011fc0258&msg=[msg]";

    /**
     *
     * @param text
     * @return
     */
    public static String chat(String text) throws IOException {
        if (StringUtils.isNotEmpty(text)) {
            String _url = SMART_ROBOT_CHAT_URL.replace("[msg]",encode(text,"gbk"));
            URL url = new URL(_url);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.setUseCaches(true);
            String content = FileUtil.readContentFromStream(connection.getInputStream());
//            System.out.println(content);

            content = content.replace("[msg]","").replace("[/msg]","");
            return content;
        }

        return text;
    }

    public static String encode(String text,String...encoding) {
        String charset = "utf8";
        if (null != encoding && encoding.length == 1) {
            charset = encoding[0];
        }

        try {
            return URLEncoder.encode(text,charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

//    public static void main(String[] args) {
//        System.out.println("智能机器人。");
//        try {
//            String content = chat("今天深圳什么天气?");
//            System.out.println(content);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
}


