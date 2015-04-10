package com.company.project.service;

import com.company.project.model.resp.Music;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by qince on 2015/4/7.
 */
public class MusicSearchService {
    /**
     * 参数：
     word: "歌曲名", //歌曲名 encodeURI
     format: "json", //返回数据格式，xml | json，默认xml
     callback: "Pub.music.searchResult", //固定值，返回jsonp格式
     如 xml格式：
     http://mp3.baidu.com/dev/api/?tn=getinfo&ct=0&word=%E6%B5%81%E6%B5%AA%E8%AE%B0&ie=utf-8&format=xml
     */
    public final static String BAIDU_SEARCH_URL = "http://mp3.baidu.com/dev/api/?tn=getinfo&ct=0&ie=utf-8&word=<word>&format=<format>";

    public static Music searchMusicByBaidu(String author,String title) throws IOException, DocumentException {
        Map<String,String> map = parseXMl(author, title);
        if (null != map  && !map.isEmpty()) {
            Music music = new Music();
            music.setTitle(title);
            music.setDescription("来自百度音乐");
            music.setMusicUrl(map.get("url"));
            music.setHQMusicUrl(map.get("url"));
            return music;
        }

        return null;
    }

    public static Map<String,String> parseXMl(String author,String title) throws IOException, DocumentException {
        Map<String,String> map = new HashMap<String, String>();
        String songinfo = title;
        if (null != author && !"".equals(author)) {
            songinfo += "/" + author;
        }

        String url = BAIDU_SEARCH_URL.replace("<word>",songinfo).replace("<format>", "json");
        URL _url = new URL(url);
        HttpURLConnection connection = (HttpURLConnection) _url.openConnection();
        connection.setDoInput(true);
        connection.connect();

        InputStream inputStream = connection.getInputStream();
        String rootContent = readContentFromInputStream(inputStream);
//        System.out.println(rootContent);
//        System.out.println("\n\n\n");
        connection.disconnect();

        JSONArray jsonObject = JSONArray.fromObject(rootContent);
        if (null != jsonObject && jsonObject.size() > 0) {
            JSONObject resObject = (JSONObject) jsonObject.get(0);
            Object songId = resObject.get("song_id");
//            System.out.println("songId:" + songId);
            String musicUrl = getMusicUrlBySongId(songId.toString());
            System.out.println(musicUrl);
            map.put("url",musicUrl);
        }


        return map;
    }

    public static String encodeUTF8(String content) {
        if (content == null || "".equals(content)) return "";
        try {
            return URLEncoder.encode(content, "utf8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static String getMusicUrlBySongId(String songId) throws IOException {
        if (null != songId) {
            // 通过歌曲id (song_id) 获取歌曲下载地址：
            /**
             * GET 方式获取：
             参数：
             songIds: "38233821" //歌曲id，从第1个列表中得到的歌曲id
             callback: "callback" //不为空时，返回jsonp格式数据
             */
            String url2 = "http://ting.baidu.com/data/music/links?songIds=<songIds>";
            url2 = url2.replace("<songIds>",songId.toString());
            System.out.println(url2);
            URL _url2 = new URL(url2);
            HttpURLConnection connection2 = (HttpURLConnection) _url2.openConnection();
            connection2.setDoInput(true);
            connection2.setRequestMethod("GET");
            connection2.connect();
            InputStream inputStream2 = connection2.getInputStream();
            String songInfo = readContentFromInputStream(inputStream2);
            connection2.disconnect();
            System.out.println("songinfo:");
            System.out.println(songInfo);
            JSONObject songInfoObject = JSONObject.fromObject(songInfo);
            songInfoObject = (JSONObject) songInfoObject.get("data");
            JSONArray jsonArray = (JSONArray) songInfoObject.get("songList");
            System.out.println("===============");
            System.out.println(songInfoObject.toString());
            //tring xcode = songInfoObject.get("xcode").toString();
            String songLink = ((JSONObject)jsonArray.get(0)).get("songLink").toString();
            String musicUrl = songLink;// + xcode;
            System.out.println(musicUrl);
            return  musicUrl;
        }

        return null;
    }

    public static String readContentFromInputStream(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"utf8"));
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        while ((temp = bufferedReader.readLine()) != null) {
            stringBuffer.append(temp);
        }
        bufferedReader.close();

        return stringBuffer.toString();
    }

    /**
     * 歌曲点播使用指南
     *
     * @return
     */
    public static String getUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("歌曲点播操作指南").append("\n\n");
        buffer.append("回复：歌曲+歌名").append("\n");
        buffer.append("例如：歌曲存在").append("\n");
        buffer.append("或者：歌曲存在@汪峰").append("\n\n");
        buffer.append("回复“?”显示主菜单");
        return buffer.toString();
    }
}
