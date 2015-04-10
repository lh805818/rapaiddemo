package com.company.project.service;

import com.company.project.util.FileUtil;
import com.company.project.util.PropertyUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by qince on 2015/4/8.
 */
public class WeatherSearchService {
    public static final String WEATHER_SEARCH_URL = "http://api.map.baidu.com/telematics/v3/weather?location=[CITY_NAME]&output=json&ak=ia6HfFL660Bvh43exmH9LrI6";
    // 天气查询使用说明
    public static String getUsage() {
        StringBuffer buffer = new StringBuffer();
        buffer.append("天气查询操作指南").append("\n\n");
        buffer.append("回复：城市+天气").append("\n");
        buffer.append("例如：深圳天气").append("\n");
        buffer.append("回复“?”显示主菜单");
        return buffer.toString();
    }

    public static String getWeatherByCityName(String cityName) throws IOException {
        String _url = WEATHER_SEARCH_URL.replace("[CITY_NAME]", FileUtil.encode(cityName));
        URL url = new URL(_url);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.setUseCaches(true);
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        String content = FileUtil.readContentFromStream(inputStream);
//        System.out.println(content);

        JSONObject weatherinfo = JSONObject.fromObject(content);
        Integer errorCode = (Integer) weatherinfo.get("error");
        if (0 == errorCode) {
            StringBuffer result = new StringBuffer();
            result.append("【"+cityName+"天气预报】\n");
            String queryDate = (String) weatherinfo.get("date");
            result.append(queryDate + "发布\n\n");

            JSONArray results = (JSONArray) weatherinfo.get("results");
            JSONObject detail = (JSONObject) results.get(0);
            String pm25 = (String) detail.get("pm25");
            result.append("PM2.5指数：" + pm25).append("\n\n");

            // 穿衣指数,洗车指数,旅游指数,感冒指数,运动指数,紫外线强度
            JSONArray index = (JSONArray) detail.get("index");
            // 穿衣指数
            JSONObject index0 = (JSONObject) index.get(0);
            // 洗车指数
            JSONObject index1 = (JSONObject) index.get(1);
            // 旅游指数
            JSONObject index2 = (JSONObject) index.get(2);
            // 感冒指数
            JSONObject index3 = (JSONObject) index.get(3);
            // 运动指数
            JSONObject index4 = (JSONObject) index.get(4);
            // 紫外线强度
            JSONObject index5 = (JSONObject) index.get(5);

            result.append(index0.get("tipt") + ":\n" + index0.get("des") + "\n\n");
            result.append(index1.get("tipt") + ":\n" + index1.get("des") + "\n\n");
            result.append(index2.get("tipt") + ":\n" + index2.get("des") + "\n\n");
            result.append(index3.get("tipt") + ":\n" + index3.get("des") + "\n\n");
            result.append(index4.get("tipt") + ":\n" + index4.get("des") + "\n\n");
            result.append(index5.get("tipt") + ":\n" + index5.get("des") + "\n\n");

            // 天气信息
            JSONArray wealtherdetail = (JSONArray) detail.get("weather_data");
            for (int i=0;i<wealtherdetail.size();i++) {
                JSONObject object = (JSONObject) wealtherdetail.get(i);
                result.append(object.get("date") + "\n");
                result.append(object.get("weather") + "  ");
                result.append(object.get("wind") + "  ");
                result.append(object.get("temperature") + "\n\n");
            }

            return result.toString();
        }
        return "";
    }

    public static void main(String[] args) {
        System.out.println("天气查询API");
        try {
            String content = getWeatherByCityName("深圳");
            System.out.println("\n\n" + content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
