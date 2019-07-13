package com.hongdu.hdutil;

import com.hongdu.filescaozuo.FileSuffixType;
import com.hongdu.gupao.spring.annotation.HdRequestParam;
import com.hongdu.gupao.spring.demo.mvc.DemoAction;
import com.hongdu.yuanmayuedu.encapsulationhttp.httpentity.UUIDUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @ClassName HdJavaEveUtils
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/3 18:45
 * @Version 1.0
 */
public class HdJavaEveUtils {

    //volatile : 阻止重排序
    private static volatile HdJavaEveUtils instance = null;

    //写入文件 ： 将list 或者 map写入文件
    public  void listMapWrite2File(Object object) throws IOException {
        if(object instanceof List) {
            list2WriteFile((List)object);return;
        }
        if(object instanceof Map) {
            map2WriteFile((Map)object);return;
        }
        if(object instanceof String) {
            stringWriteFile((String)object);return;
        }

    }

    /**
     * 字符串写入文件
     * @param object
     * @throws IOException
     */
    private  void stringWriteFile(String object) throws IOException {
        URL url = this.getClass().getResource("");
        String fileName = UUIDUtil.createUUID().toString();
        String tempFile = url.getFile() + fileName + FileSuffixType.TXT.getSuffix();
        File file = new File(tempFile);
        if(!file.exists()) {
            file.createNewFile();
        }
        //读取数据写入 : 从内存到外不文件--》 输出流 和输入流
        //reader和wirte==》 写入到文件， 从文件中读出来
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        System.out.println("------------------------------------------开始写入文件--------------------------------------");
        writer.write(object + "\r\n");
        System.out.println("------------------------------------------结束写入文件--------------------------------------");
        writer.close();
        System.out.println("------------------------------------------开始读取文件--------------------------------------");
        BufferedReader  reader = new BufferedReader(new FileReader(file));
        String line = null;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        reader.close();
        System.out.println("------------------------------------------结束读取文件--------------------------------------");
    }

    /**
     * 将map中的值输出到文件中
     * @param object
     */
    private  void map2WriteFile(Map object) throws IOException {
        File file = createCurrentPathFile();
        if(object instanceof Map) {
            //开始写入到空文件中 ： 相对于文件， 那就是直接写入
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            System.out.println("------------------------------------------开始写入文件--------------------------------------");
            writer.write("{");
            Set<Map.Entry<String, String>> entries = object.entrySet();
            for (Map.Entry<String,String> entry : entries) {
                writer.write(entry.getKey() + " = " + entry.getValue() + ",");
            }
            writer.write("}");
            System.out.println("------------------------------------------结束写入文件--------------------------------------");
        } else {
            throw new IllegalArgumentException("参数不合法！不是Map类型！");
        }

    }

    /**
     * 将list中的值输出到文件中
     * @param object
     * @throws IOException
     */
    private  void list2WriteFile(List object) throws IOException {
        File file = createCurrentPathFile();
        if (object instanceof List) {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            System.out.println("------------------------------------------开始写入文件--------------------------------------");
            writer.write("{");
            for (Object o : object) {
                writer.write((String)o + "\r\n");
            }
            writer.write("}");
            System.out.println("------------------------------------------结束写入文件--------------------------------------");
        } else {
            throw new IllegalArgumentException("参数不合法！不是List类型！");
        }
    }



    //遍历map集合 并打印
    public void printMap(Map<String, Object> map) {
        Set<Map.Entry<String, Object>> omaps = map.entrySet();
        for (Map.Entry<String, Object> o : omaps) {
            System.out.println(o.getKey() + " : " + o.getValue());
        }
    }
    public void printList(List list) {
        Iterator iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println("list集合: " + iterator.next());
        }
    }

    //遍历Files:
    public static void printFiles(File directory) throws Exception {
        if(!directory.exists()) {
            throw new IllegalArgumentException("目录："+directory+"不存在.");
        }
        if(!directory.isDirectory()){
            throw new IllegalArgumentException(directory+"不是目录。");
        }
        File[] dirs = directory.listFiles();
        if(dirs != null && dirs.length > 0) {
            for (File f : dirs) {
                if(f.isDirectory()) {
                    printFiles(f);
                } else {
                    System.out.println(f.getCanonicalPath() + " : " + f.getName());
                }
            }
        }
    }




    /**
     * 创建当前路径下的空文件
     * @return
     */
    public  File createCurrentPathFile() throws IOException {
        URL url = this.getClass().getResource("");
        String fileName = UUIDUtil.createUUID().toString();
        String tempFile = url.getFile() + fileName + FileSuffixType.TXT.getSuffix();
        File file = new File(tempFile);
        if(!file.exists()) {
            file.createNewFile();
        }
        return file;
    }

    /**
     * 双重检查锁机制
     * @return
     */
    public static HdJavaEveUtils getInstance() {
//        if(null == instance) {
//            synchronized (HdJavaEveUtils.class) {
//                if(instance == null) {
//                    instance = new HdJavaEveUtils();
//                }
//            }
//        }
        if(null == instance) {
            instance = new HdJavaEveUtils();
        }
        return instance;
    }

    private HdJavaEveUtils() {
    }

    /**
     * 将流读成字符串 ： 其实就是输出这个输入流
     * @param inputStream
     * @return
     */
    public  String readInputStream2String(InputStream inputStream) throws IOException {
        int len;
        byte[] bytes = new byte[1024];
        //捕获内存缓冲区的数据转换为字节数组
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        while ((len = inputStream.read(bytes)) != -1) {
            //循环读取内容,将输入流的内容放进缓冲区中
            bo.write(bytes, 0, len);
        }
        return new String(bo.toByteArray(), "UTF-8");
    }

    /**
     * 将文件读取成为字符串
     * @param file
     * @return
     * @throws IOException
     */
    public  String readInputStream2String(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            sb.append(line).append("\n");
        }
        return sb.toString();
    }
    /**
     * 一次将文本文件读取出来
     * @param fileName
     */
    public String read2String(String fileName) throws IOException {
        String encoding = "UTF-8";
        File file = new File(fileName);
        Long len = file.length();
        InputStream in = new FileInputStream(file);
        byte[] fileContent = new byte[len.intValue()];
        byte[] fileContent2 = new byte[in.available()];
        in.read(fileContent2);
        in.close();
        return new String(fileContent, encoding);
    }

    /**
     * 解析所有的 --》 必定返回的是 List<Map> 类型
     * @param clazz 解析的类
     * @param methodName 解析的方法名
     * @return
     */
    public Map parseMethodParamToMap(Class<?> clazz, String methodName) throws NoSuchMethodException {
        Map map = new HashMap(32);
        Method method = clazz.getMethod(methodName);
        //提取方法中加了注解的参数
        //把方法上的注解拿到， 得到一个二维数组
        //y因为一个字段可以有多个注解，
        //而且一个方法又有多个参数
        Annotation[][] pa = method.getParameterAnnotations();
        for (int i = 0; i < pa.length; i++) {
            for (Annotation a : pa[i]) {
                if(a instanceof HdRequestParam) {
                    String paramName = ((HdRequestParam) a) .value();
                    if(!"".equals(paramName.trim())) {
                        map.put(paramName, i);
                    }
                }
            }
        }
        return map;
    }

//    @Test
    public void parseMethodParamToMapTest() throws NoSuchMethodException {
        Map map = parseMethodParamToMap(DemoAction.class, "query");
        printMap(map);
    }

    /**
     * 解析的是 ： 方法中的注解参数名 以及对应的参数位置
     *  name : 2
     * @param method
     * @return
     * @throws NoSuchMethodException
     */
    public Map parseMethodParamToMap(Method method) throws NoSuchMethodException {
        Map map = new HashMap(32);
        //获取注解参数
        Annotation[][] pa = method.getParameterAnnotations();
        for (int i = 0; i < pa.length; i++) {
            for (Annotation a : pa[i]) {
                if(a instanceof HdRequestParam) {
                    String paramName = ((HdRequestParam) a) .value();
                    if(!"".equals(paramName.trim())) {
                        map.put(paramName, i);
                    }
                }
            }
        }
        //提取其参数以及位置
        Class<?>[] paramsTypes = method.getParameterTypes();
        for (int i = 0; i < paramsTypes.length; i++) {
            Class<?> type = paramsTypes[i];
            if(type == HttpServletRequest.class
                    || type == HttpServletResponse.class) {
                map.put(type.getName(), i);
            }
        }
        return map;
    }


    /**
     * 方法普通参数类型以及位置：javax.servlet.http.HttpServletResponse : 1
     * 方法普通参数类型以及位置： javax.servlet.http.HttpServletRequest : 0
     * 注解参数名以及位置 ： name : 2 （应该是有一种默认是java.lnag.String类型的感觉）
     * @param args
     * @throws NoSuchMethodException
     */
    public static void main(String[] args) throws NoSuchMethodException {
//        System.out.println(FileSuffixType.values());//[Lcom.hongdu.filescaozuo.FileSuffixType;@4a574795
//        System.out.println(FileSuffixType.valueOf(FileSuffixType.SQL.getSuffix()));
//        System.out.println(FileSuffixType.SQL.getSuffix());//.sql
        HdJavaEveUtils javaEveUtils = new HdJavaEveUtils();
//      parseMethodParamToMapTest();
        Method method = DemoAction.class.getMethod("query", HttpServletRequest.class, HttpServletResponse.class, String.class);
        Map map = javaEveUtils.parseMethodParamToMap(method);
        javaEveUtils.printMap(map);
    }
}
