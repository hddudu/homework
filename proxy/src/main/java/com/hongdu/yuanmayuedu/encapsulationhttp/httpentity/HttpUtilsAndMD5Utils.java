package com.hongdu.yuanmayuedu.encapsulationhttp.httpentity;

import com.hongdu.yuanmayuedu.encapsulationhttp.test.CloseAccountRequest;
import com.hongdu.yuanmayuedu.encapsulationhttp.test.OpenAccountRequest;
import com.hongdu.yuanmayuedu.encapsulationhttp.utils.MD5Util;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

/**
 *  https://www.cnblogs.com/xuenan/p/4770581.html
 *  https://www.cnblogs.com/xuenan/p/4770581.html
 *
 * @ClassName HttpUtils
 * @Description
 *  原生http请求封装工具类
 * @Author dudu
 * @Date 2019/6/24 10:17
 * @Version 1.0
 */
public class HttpUtilsAndMD5Utils {

    public static final int CONNECTION_TIMEOUT = 60 * 1000;

    public static final int SOCKET_TIMEOUT = 60 * 1000;

    public static final String USER_AGENT_HEADER = "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/47.0.2526.80 Safari/537.36";
    public static final String ACCEPT_HEADER = "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8";
    public static final String ACCEPT_LANGUAGE_HEADER = "zh-CN,zh;q=0.8,en;q=0.6";



    private  String defaultContentEncoding;

    public HttpUtilsAndMD5Utils() {
        this.defaultContentEncoding = Charset.defaultCharset().name();
    }

    //测试类只能有一个构造函数
    @Test
    public void test1() {
        System.out.println(Charset.defaultCharset().name());//UTF-8
    }
    /**
     * 默认的响应字符集
     */
    public String getDefaultContentEncoding() {
        return this.defaultContentEncoding;
    }
    //get请求
    //无参
    //带参数
    //带参数 带头部属性
    public RespEntity sendGet(String urlString) throws IOException {
        return this.send(urlString, "GET", null, null);
    }
    public RespEntity sendGet(String urlString, Map<String, String> params) throws IOException {
        return this.send(urlString, "GET", params, null);
    }
    public RespEntity sendGet(String urlString, Map<String, String> params,  Map<String, String> propertys) throws IOException {
        return this.send(urlString, "GET", params, propertys);
    }

    //post请求
    //无参
    //带参数
    //带参数 带头部属性
    public RespEntity sendPost(String urlString) throws IOException {
        return this.send(urlString, "POST", null, null);
    }
    public RespEntity sendPost(String urlString, Map<String, String> params) throws IOException {
        return this.send(urlString, "POST", params, null);
    }
    public RespEntity sendPost(String urlString,Map<String, String> params,  Map<String, String> propertys) throws IOException {
        return this.send(urlString, "POST", params, propertys);
    }
    private RespEntity send(String urlString, String method ,Map<String, String> parameters,
                                         Map<String, String> properties) throws IOException {
        HttpURLConnection urlConnection = null;
        if("GET".equalsIgnoreCase(method) && parameters != null) {
            //拼接参数
            StringBuffer paramSb = new StringBuffer();
            //遍历参数map : 返回集合 ： Map.Entry是类型 ： 引用数据类型
            Set<Map.Entry<String, String>> paramsEntrySet =  parameters.entrySet();
            int countParam = 0;
            for (Map.Entry<String, String> p : paramsEntrySet) {
                System.out.println("key = " + p.getKey() + ", value = " + p.getValue());
                if(countParam == 0) {
                    paramSb.append("?");
                } else {
                    paramSb.append("&").append(p.getKey()).append(p.getValue());
                }
           }
            urlString += paramSb;
            URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod(method);
            urlConnection.setDoOutput(true);//默认是false
            urlConnection.setDoInput(true);//默认是true
            urlConnection.setUseCaches(false);
            if(properties != null && properties.size() > 0) {
                for (Map.Entry<String, String> key : properties.entrySet()) {
                    // this.userHeaders.add(var1, var2) 设置头部信息
                    urlConnection.addRequestProperty(key.getKey() , key.getValue());
                }
            }
            if("POST".equalsIgnoreCase(method) && parameters != null) {
                StringBuffer paramsPost = new StringBuffer();
                for (Map.Entry<String, String> ppost : parameters.entrySet()) {
                    paramsPost.append("&").append(ppost.getKey()).append("=").append(ppost.getValue());
                }
                urlConnection.getOutputStream().write(paramsPost.toString().getBytes());//将请求参数写入连接的输出流 : 其实就是发起请求post
                urlConnection.getOutputStream().flush();
                urlConnection.getOutputStream().close();
            }
        }
        return getContent(urlString, urlConnection);
    }

    /**
     * 获取请求响应内容
     * @param urlString
     * @param urlConnection
     * @return
     */
    private RespEntity getContent(String urlString, HttpURLConnection urlConnection) throws IOException {
        RespEntity respEntity = new RespEntity();
        try {
            InputStream in = urlConnection.getInputStream();
            //读到内存 ： 读到程序
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));
            respEntity.setContentCollection(new Vector<String>());
            StringBuffer temp = new StringBuffer();
            String line = bufferedReader.readLine();
            while (line != null) {
                respEntity.getContentCollection().add(line);
                temp.append(line).append("\r\n");
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
            String ecode = urlConnection.getContentEncoding();
            if(ecode == null) {
                ecode = defaultContentEncoding;
            }
            respEntity.setUrlString(urlString);
            respEntity.setDefaultPort(urlConnection.getURL().getDefaultPort());
            respEntity.setFile(urlConnection.getURL().getFile());
            respEntity.setHost(urlConnection.getURL().getHost());
            respEntity.setPort(urlConnection.getURL().getPort());
            respEntity.setPath(urlConnection.getURL().getPath());

            respEntity.setProtocol(urlConnection.getURL().getProtocol());
            respEntity.setRef(urlConnection.getURL().getRef());
            respEntity.setQuery(urlConnection.getURL().getQuery());
            respEntity.setUserInfo(urlConnection.getURL().getUserInfo());

            //设置响应内容
            respEntity.setContent(new String(temp.toString().getBytes(), ecode));
            respEntity.setContentEncoding(ecode);
            //设置响应码
            respEntity.setCode(urlConnection.getResponseCode());
            respEntity.setMessage(urlConnection.getResponseMessage());

            respEntity.setContentType(urlConnection.getContentType());
            respEntity.setMethod(urlConnection.getRequestMethod());
            respEntity.setConnectionTimeout(urlConnection.getConnectTimeout());
            respEntity.setReadTimeout(urlConnection.getReadTimeout());
        } catch (IOException e) {
            throw e;
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();//断开连接
            }
        }
        return respEntity;
    }

    //输入流转字节数组 ： byteArrayOutputStream : 读取字节判断是 -1
    public static byte[] read2Bytes(InputStream inputStream) throws IOException {
        byte[] bytes = new byte[1024];//定义字节缓冲区
        //定义返回的字节数组输出流
        ByteArrayOutputStream toBytes = new ByteArrayOutputStream();
        //读输入流： 到内存上 ： 到缓冲区 ： 写到字节输出流上
        int len = 0;
        while ((len = inputStream.read(bytes)) != -1) {
            toBytes.write(bytes, 0, len);
        }
        toBytes.flush();
        toBytes.close();
        return bytes;
    }
    //输入流转字符串
    public static String read2String(InputStream inputStream) throws IOException {
        String inputStreamString = null;
        inputStreamString = new String(read2Bytes(inputStream), "UTF-8");
        return inputStreamString;
    }

    /**
     *
     * @param path url路径
     * @param xml 请求字符串的格式是xml文件
     * @param encoding 编码
     * @return
     */
    public static byte[] postXml(String path, String xml, String encoding) throws IOException {
        byte[] data = xml.getBytes();
        URL url = new URL(path);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //设置请求方式
        //URL 连接可用于输入和/或输出。如果打算使用 URL 连接进行输入，则将 DoInput 标志设置为 true；如果不打算使用，则设置为 false。默认值为 true。
        //URL 连接可用于输入和/或输出。如果打算使用 URL 连接进行输出，则将 DoOutput 标志设置为 true；如果不打算使用，则设置为 false。默认值为 false。
        //setDoOutput()和setDoInput()的作用主要是发送Post请求。
        //Post请求往往需要向服务器端发送数据参数，所以需要setDoInput(true)，总是要通过getInputStream()从服务端获得响应所以setDoInput()默认是true；
        //setDoOutput()默认是false，需要手动设置为true，完了就可以调用getOutputStream()方法从服务器端获得字节输出流。
        urlConnection.setRequestMethod("POST");
        urlConnection.setDoOutput(true);//可以从服务的OutputStream获取字节输出流
        //设置请求头属性 : content-type + content-length
        //超时时间
        urlConnection.setRequestProperty("Content-Type","text/xml;charset=" + encoding);
        urlConnection.setRequestProperty("Content-Length", String.valueOf(data.length));
        urlConnection.setConnectTimeout(5 * 1000);
        //获取输出流
        OutputStream outputStream = urlConnection.getOutputStream();
        outputStream.write(data);
        outputStream.flush();
        outputStream.close();
        if(urlConnection.getResponseCode() == 200) {
            return read2Bytes(urlConnection.getInputStream());//shu
        }
        return null;
    }

    public static String read2String2(InputStream inputStream) throws IOException {
        String inputStreamString = null;//字符串有一个方法： 将字节数组变成字符串的
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, len);
        }
        inputStreamString = new String(byteArrayOutputStream.toByteArray(), "UTF-8");
        return inputStreamString;
    }

    //xml转成字节数组
    //提交数据到服务器

    /**
     * 设置Get请求Request头
     * @param url
     * @param params
     * @param headers
     * @return
     */
    public static URLConnection setGetRequest(String url,
                                               Map<String, String> params, Map<String, String> headers) throws IOException {

//        StringBuilder
                StringBuffer buffer = new StringBuffer(url);
                Set<Map.Entry<String, String>> entries = null;
        // 如果是GET请求，则请求参数在URL中
        if(params != null && !params.isEmpty()) {
            buffer.append("?");
            entries = params.entrySet();
            for (Map.Entry<String, String> entry : entries) {
                buffer.append(entry.getKey()).append("=") //.append(entry.getValue())
                    .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                        .append("&");
            }
            buffer.deleteCharAt(buffer.length() - 1);

        }
        URL url1 = new URL(buffer.toString());
        HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
        connection.setRequestMethod("GET");
        //设置请求头
        if(headers != null && !headers.isEmpty()) {
            entries = headers.entrySet();
            for (Map.Entry<String,String> entry : entries) {
                connection.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        //为什么会有这个ResponseCode
        connection.getResponseCode();
        return connection;
    }

    public static URLConnection setPosetRequest(String url,
                                                Map<String, String> params, Map<String, String> headers)
            throws Exception {
        StringBuilder buf = new StringBuilder();
        Set<Map.Entry<String, String>> entrys = null;
        // 如果存在参数，则放在HTTP请求体，形如name=aaa&age=10
        if (params != null && !params.isEmpty()) {
            entrys = params.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                buf.append(entry.getKey()).append("=")
                        .append(URLEncoder.encode(entry.getValue(), "UTF-8"))
                        .append("&");
            }
            buf.deleteCharAt(buf.length() - 1);
        }
        URL url1 = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
        conn.setRequestMethod("POST");
        //设置DoOutput(true)
        conn.setDoOutput(true);
        //设置输出流信息
        OutputStream out = conn.getOutputStream();
        out.write(buf.toString().getBytes("UTF-8"));
        if (headers != null && !headers.isEmpty()) {
            entrys = headers.entrySet();
            for (Map.Entry<String, String> entry : entrys) {
                conn.setRequestProperty(entry.getKey(), entry.getValue());
            }
        }
        conn.getResponseCode(); // 为了发送成功
        return conn;
    }

    public static void main(String[] args) {

    }

    /**
     * 根据传入的json格式字符串获得实体类
     *
     * @time 2019-5-15 10:28:20
     * @author Lxm
     * @param data
     * @param cls
     * @return
     */
    private static Object getRequest(String data, Class<?> cls) {
        Object obj = null;
        try {
            JSONObject js = JSONObject.fromObject(data);
            System.out.println(js);
            obj = JSONObject.toBean(js, cls);
        } catch (Exception e) {
        }
        return obj;
    }

    /**
     * 生成签名码方法：
     */
    @Test
    public void getRequestTest() {
        String s = "{\n" +
                "\t\"accessCode\":\"TESTAC\",\n" +
                "\t\"requestId\":\"201711270001\",\n" +
                "\t\"cardNo\":\"6604546564\",\n" +
                "\t\"userName\":\"测试\",\n" +
                "\"userPhone\":\"96696021\",\n" +
                "\"userIdent\":\"522634199901010000\",\n" +
                "\"sign\":\"3D4157CDBEF1F2587B6D35EBA80E9A34\"\n" +
//                "\"sign\":\"0E10BDE0F44878BC51DDD7487BDC321A\"\n" +
                "}";
        OpenAccountRequest request = (OpenAccountRequest) getRequest(s, OpenAccountRequest.class);
        //获得了实体对象
        String sign = sign(request.getAccessCode(),
                request.getRequestId(), request.getCardNo(), request.getIdNo(),
//                request.getUserName(), request.getUserPhone(),
                request.getUserIdent());
        String sign2 = sign2(request.getAccessCode(),
                request.getRequestId(), request.getCardNo(), request.getIdNo(),
//                request.getUserName(), request.getUserPhone(),
                request.getUserIdent());
        System.out.println(request.toString());
        System.out.println(sign);//0E10BDE0F44878BC51DDD7487BDC321A
        System.out.println(sign2);//0E10BDE0F44878BC51DDD7487BDC321A
        //3D4157CDBEF1F2587B6D35EBA80E9A34
        System.out.println(request.getSign());


    }

    @Test
    public void test02() {
        System.out.println("--------------------------------------------------------------");
        //String newstring = "{\"accessCode\":\"FUv6TIquQ;<Rq#H~FJ~m%8%npdg6RMHe\",\"cardNo\":\"660454199202026564\",\"idNo\":\"660454199202026564\",\"requestId\":\"201711270001\",\"sign\":\"LiK8WRq8iG+3Yp6MrocjGA==\",\"userIdent\":\"522634199901010000\",\"userName\":\"测试\",\"userPhone\":\"96696021\"}";
//        String newstring = "{\n" +
//                "\t\"accessCode\":\"FUv6TIquQ;<Rq#H~FJ~m%8%npdg6RMHe\",\n" +
//                "\t\"requestId\":\"201906280001\",\n" +
//                "\t\"cardNo\":\"0202696064025421680\",\n" +
//                "\t\"idNo\":\"1702311496\",\n" +
//                "\t\"userIdent\":\"522101199403275422\"\n" +
//                "\t}";
//        String newstring = "{\n" +
//                "\t\"accessCode\":\"FUv6TIquQ;<Rq#H~FJ~m%8%npdg6RMHe\",\n" +
//                "\t\"requestId\":\"201906280001\",\n" +
//                "\t\"idNo\" : \"1702311496\",\n" +
//                "\t\"cardNo\":\"0202696064025421680\",\n" +
//                "\"userIdent\":\"522634199901010000\",\n" +
//                "\"sign\":\"3087F7745D5D27F4ACB8DA3ACA8C1B8D\"\n" +
//                "}\n";
        String newstring ="{\n" +
                "\t\"accessCode\":\"FUv6TIquQ;<Rq#H~FJ~m%8%npdg6RMHe\",\n" +
                "\t\"requestId\":\"201906280001\",\n" +
                "\t\"idNo\" : \"9969033642\",\n" +
                "\t\"cardNo\":\"0020506957135957151\",\n" +
                "\t\"userIdent\":\"522634199901010000\",\n" +
                "\t\"sign\":\"3087F7745D5D27F4ACB8DA3ACA8C1B8D\"\n" +
                "}\n";
        CloseAccountRequest request = (CloseAccountRequest)  getRequest(newstring, CloseAccountRequest.class);
//        OpenAccountRequest request = (OpenAccountRequest)  getRequest(newstring, OpenAccountRequest.class);
//        String sign2 = sign2(request.getAccessCode(),
//                request.getRequestId(), request.getCardNo(), request.getIdNo(),
//                request.getUserName(), request.getUserPhone(),
//                request.getUserIdent());
        String sign = sign(request.getAccessCode(),
                request.getRequestId(), request.getCardNo(), request.getIdNo(),
                request.getUserIdent());
        System.out.println(sign);
    }

    /**
     * 签名方法
     *
     * @param params
     * @return
     */
    public static String sign2(String... params) {
        String DEFAULT_SIGN_CODE = "ABCD1234";
        String s = "";
        for (String p : params) {
            if (StringUtils.isEmpty(p)) {
                s = s + DEFAULT_SIGN_CODE;
            } else {
                s = s + p + DEFAULT_SIGN_CODE;
            }
        }
        return MD5Util.MD5(s, "UTF-8").toUpperCase();
    }

    private String sign(String... params) {
        String DEFAULT_SIGN_CODE = "ABCD1234";
        StringBuffer s = new StringBuffer();
        for (String p : params) {
            if (StringUtils.isEmpty(p)) {
                s.append(DEFAULT_SIGN_CODE);
            } else {
                s.append(p).append(DEFAULT_SIGN_CODE);
            }
        }
        return MD5Util.MD5(s.toString(), "UTF-8").toUpperCase();
    }


    @Test
    public void testjq() {
        String s = "user_102_123456789.json";
        System.out.println(s.substring(s.lastIndexOf("_") + 1, s.lastIndexOf(".")));
    }
}
