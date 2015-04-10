package com.company.project.util;

import java.io.*;
import java.net.URLEncoder;

/**
 * Created by qince on 2015/4/8.
 */
public class FileUtil {
    public static String readContentFromStream(InputStream inputStream,String... encoding) throws IOException {
        String _encoding = "utf8";
        if (null != encoding && encoding.length == 1) {
            _encoding = encoding[0];
        }

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, _encoding));
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        while ((temp = bufferedReader.readLine()) != null) {
            stringBuffer.append(temp);
        }

        bufferedReader.close();

        return stringBuffer.toString();
    }

    /**
     * 以指定的编码格式编码文本。默认utf8
     * @param text
     * @param encoding
     * @return
     */
    public static String encode(String text,String...encoding) {
        String _encoding = "utf8";
        if (null != encoding && encoding.length == 1) {
            _encoding = encoding[0];
        }

        try {
            return URLEncoder.encode(text,_encoding);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
