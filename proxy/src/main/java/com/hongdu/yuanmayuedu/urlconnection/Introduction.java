package com.hongdu.yuanmayuedu.urlconnection;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * @ClassName Introduction
 * @Description UrlConnection Test
 * @Author dudu
 * @Date 2019/6/21 10:09
 * @Version 1.0
 */
public class Introduction {

    public static void main(String[] args) throws Exception {
        URL url = new URL("");
        HttpURLConnection urlConnection = new HttpURLConnection(url) {
            @Override
            public void disconnect() {

            }

            @Override
            public boolean usingProxy() {
                return false;
            }

            @Override
            public void connect() throws IOException {

            }
        };

        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();

    }

    @Test
    public void getUrlFileNameTest0() throws Exception {
        String encoding = "UTF-8";
        String params="{\"vehId\":\"贵A3U21W_0\"}";
        byte[] data = params.getBytes(encoding);
//		URL url = new URL("http://222.85.144.65:2005/gzReview/orderV2/fetchIssueResult");
//        URL url = new URL("http://222.85.144.65:2005/gzReview/orderV2/fetchIssueResult?vehId=贵A3U21W_0");
//        URL url = new URL("http://192.168.124.71:8484/orderV2/fetchIssueResult?vehId=" + base64Encoding("贵A3U21W_0"));
        URL url = new URL("http://222.85.144.65:2005/gzReview/orderV2/fetchIssueResult?vehId=" + base64Encoding("贵A3U21W_0"));
//        URL url = new URL("http://192.168.124.71:8484/orderV2/fetchIssueResult?vehId=贵A3U21W_0");
//        URL url = new URL("http://222.85.144.65:2005/gzReview//orderV2/fetchIssueResult?vehId=" +  gbEncoding("贵A3U21W_0") );
//        URL url = new URL("http://222.85.144.65:2005/gzReview/orderV2/fetchIssueResult?vehId=" + new String("贵A3U21W_0".getBytes(), "UTF-8"));
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod("GET");
        urlConnection.setDoOutput(true);
        //application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
        urlConnection.setRequestProperty("Content-Type", "application/x-javascript; charset="+ encoding);
        urlConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
        Map headers = urlConnection.getHeaderFields();
        String fileNameString = headers.get("Content-Disposition").toString();
        System.out.println(fileNameString.substring(fileNameString.lastIndexOf("=") + 1, fileNameString.lastIndexOf("]")));
//        System.out.println(headers.get("Content-Disposition").toString().substring());
        System.out.println((headers.get("Content-Disposition")));
        Set<String> keys = headers.keySet();
        System.out.println(keys.toString());
//        System.out.println(headers.get("Content-Disposition"));
//        System.out.println(urlConnection.getHeaderField("Content-Disposition"));
        for(String key : keys) {
            System.out.println(key + " ----------------------------- " + urlConnection.getHeaderField(key));
        }
        //[3]设置常见的请求参数
        //[请求连接超时时间]
        urlConnection.setConnectTimeout(2000);
        //[读取超时时间]
        urlConnection.setReadTimeout(2000);
        //[请求方式--默认就是get]
//        OutputStream outStream = urlConnection.getOutputStream();
//        outStream.write(data);//
//        outStream.flush();
//        outStream.close();
        if(urlConnection.getResponseCode()==200){
            InputStream inStream = urlConnection.getInputStream();
            String result=new String(readInputStream(inStream), "UTF-8");
        }
        //[4]获取响应码
        int responseCode = urlConnection.getResponseCode();
        if(responseCode == 200){
            System.out.println("success!");
        }else{
            System.out.println("failed!");
        }
    }

    public static byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outSteam = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        int len;
        while((len = inStream.read(buffer)) != -1) {
            outSteam.write(buffer, 0, len);
        }

        outSteam.close();
        inStream.close();
        return outSteam.toByteArray();
    }

    /*
     * 中文转unicode编码
     */
    public static String gbEncoding(final String gbString) {
        char[] utfBytes = gbString.toCharArray();
        String unicodeBytes = "";
        for (int i = 0; i < utfBytes.length; i++) {
            String hexB = Integer.toHexString(utfBytes[i]);
            if (hexB.length() <= 2) {
                hexB = "00" + hexB;
            }
            unicodeBytes = unicodeBytes + "\\u" + hexB;
        }
        return unicodeBytes;
    }

    @Test
    public void gbEncodingTest() {
        String ga = "贵A3U21W_0";
        System.out.println("贵A3U21W_0 : " + gbEncoding(ga));// \u8d35\u0041\u0033\u0055\u0032\u0031\u0057\u005f\u0030
    }
    @Test
    public void decodeUnicodeTest() {
        String da = "\\u8d35\\u0041\\u0033\\u0055\\u0032\\u0031\\u0057\\u005f\\u0030";
        System.out.println("\\\\u8d35\\\\u0041\\\\u0033\\\\u0055\\\\u0032\\\\u0031\\\\u0057\\\\u005f\\\\u0030 : 解码后 : " + decodeUnicode(da));
    }

    /*
     * unicode编码转中文
     */
    public static String decodeUnicode(final String dataStr) {
        int start = 0;
        int end = 0;
        final StringBuffer buffer = new StringBuffer();
        while (start > -1) {
            end = dataStr.indexOf("\\u", start + 2);
            String charStr = "";
            if (end == -1) {
                charStr = dataStr.substring(start + 2, dataStr.length());
            } else {
                charStr = dataStr.substring(start + 2, end);
            }
            char letter = (char) Integer.parseInt(charStr, 16); // 16进制parse整形字符串。
            buffer.append(new Character(letter).toString());
            start = end;
        }
        return buffer.toString();
    }

    public static String base64Encoding(final String paramString) {
        String asB64 = null;
        try {
            asB64 = Base64.getEncoder().encodeToString(paramString.getBytes("utf-8"));
        } catch (UnsupportedEncodingException e) {
        }
        return asB64;
    }
    @Test
    public void base64Encoding() {
        String s = "贵A3U21W_0";
        System.out.println(base64Encoding(s));
    }

    public static String base64Decoding(String paramString) {
        byte[] asBytes = Base64.getDecoder().decode(paramString);
        try {
            return new String(asBytes, "utf-8");
        } catch (UnsupportedEncodingException e) {
        }
        return null;
    }
    @Test
    public void base64DecodingTest() {
        String s = "6LS1QTNVMjFXXzA=";
        System.out.println(base64Decoding(s));
    }

}
