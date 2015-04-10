package com.company.project.service;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;
import org.apache.commons.lang.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by qince on 2015/3/25.
 */
public class TodayInHistoryService {
    public final static String url = "http://www.rijiben.com/";

    /**
     * 读取网页内容
     * @param url
     * @return
     */
    public static String getContent() throws IOException {
        URL _url = new URL(url);
        URLConnection connection =_url.openConnection();
        connection.connect();
        InputStream inputStream = connection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"UTF-8"));
        StringBuffer stringBuffer = new StringBuffer();
        String line = null;

        while ((null != (line = bufferedReader.readLine()))) {
            stringBuffer.append(line);
        }

        bufferedReader.close();;

        return getContentFromUrlContent(stringBuffer.toString());
    }

    private static String getContentFromUrlContent(String urlContent) {
        Map<String,String> result = new HashMap<String, String>();
        StringBuffer stringBuffer = new StringBuffer();
        if (StringUtils.isNotEmpty(urlContent)) {
            String regex = "(.*)(<div class=\\\"listren\\\">)(.*?)(</div>)(.*)";
            regex = "<div class=\"listren\">(.*?)</div>";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(urlContent);
            if (matcher.find()) {
                String content = matcher.group(1);
//                System.out.println(content);

               String[] cons = content.split("<li>");
                regex = "<a[^<]*?>([^<]*?)</a>";
                Pattern p = Pattern.compile(regex);
                String today = getToday();
               for (String con : cons) {
                   Matcher m = p.matcher(con);
                   if (m.find()) {
                       String finalContent = m.group(1).replace(today,"");
                       stringBuffer.append(finalContent).append("\n\n");
//                       System.out.println(finalContent);
                   }
//                   System.out.println(con);
               }
            }

        }

        return stringBuffer.toString();
    }

    public static String getToday() {
        SimpleDateFormat sdf = new SimpleDateFormat("M月d日");
        return sdf.format(new Date());
    }
}
