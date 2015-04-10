package com.company.project.test;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by qince on 2015/4/7.
 */
public class BaiduMusicSearchAPITest {
    public final static String BAIDU_MUSIC_SEARCH_URL = "http://box.zhangmen.baidu.com/x?op=12&count=1&title={TITLE}$${AUTHOR}$$$$";
    public static void main(String[] args) {
        System.out.println("百度音乐搜索API");

        try {
           handleHttp("刘德华","今天");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据作者和歌曲名称搜索歌曲
     * @param author
     * @param title
     * @return
     */
    public static void handleHttp(String author,String title) throws IOException, DocumentException, ParserConfigurationException, SAXException {
        String searchUrl = BAIDU_MUSIC_SEARCH_URL.replace("{TITLE}", encodeUTF8(title)).replace("{AUTHOR}", encodeUTF8(author));
        URL url = new URL(searchUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();
        parseXML(connection.getInputStream());
    }

    public static String encodeUTF8(String content) {
        try {
            return URLEncoder.encode(content,"utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static Map<String,String> parseXML(InputStream inputStream) throws DocumentException, ParserConfigurationException, IOException, SAXException {
        Map<String,String> map = new HashMap<String, String>();
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        Element rootElement = document.getRootElement();
        String url = null;
        String durl = null;

        // 查找到的音乐数量
        Integer count = Integer.valueOf(rootElement.element("count").getText());
        if (count > 0) {
            // 普通音质URL
            Element urlElement = rootElement.element("url");
            Element encodeUrlElement = urlElement.element("encode");
            Element decodeUrlElement = urlElement.element("decode");
            url = encodeUrlElement.getText().substring(0,encodeUrlElement.getText().lastIndexOf("/") + 1) + decodeUrlElement.getText();
            System.out.println(url);

            // 高品质URL
            Element durlElement = rootElement.element("durl");
            Element encodeDurlElement = durlElement.element("encode");
            Element decodeDurlElement = durlElement.element("decode");
            durl = encodeDurlElement.getText().substring(0,encodeDurlElement.getText().lastIndexOf("/") + 1) + decodeDurlElement.getText();
            System.out.println(durl);
        }

        map.put("url",url);
        map.put("durl",durl);
        return map;
    }
}
