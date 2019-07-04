package com.hongdu.hdutil;

import com.hongdu.filescaozuo.FileSuffixType;
import com.hongdu.yuanmayuedu.encapsulationhttp.httpentity.UUIDUtil;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName HdJavaEveUtils
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/3 18:45
 * @Version 1.0
 */
public class HdJavaEveUtils {

    //写入文件 ： 将list 或者 map写入文件
    public  void listMapWrite2File(Object object) throws IOException {
        if(object instanceof List) {
            list2WriteFile((Map)object);return;
        }
        if(object instanceof Map) {
            map2WriteFile((List)object);return;
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

    private  void map2WriteFile(List object) {
        URL url = this.getClass().getResource("");
    }

    private  void list2WriteFile(Map object) {
        URL url = this.getClass().getResource("");
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

    //遍历map集合 并打印
    public static void printMap(Map<String, String> map) {
        Set<Map.Entry<String, String>> omaps = map.entrySet();
        for (Map.Entry<String, String> o : omaps) {
            System.out.println(o.getKey() + " : " + o.getValue());
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


    public static void main(String[] args) {
        System.out.println(FileSuffixType.values());//[Lcom.hongdu.filescaozuo.FileSuffixType;@4a574795
//        System.out.println(FileSuffixType.valueOf(FileSuffixType.SQL.getSuffix()));
        System.out.println(FileSuffixType.SQL.getSuffix());//.sql
    }
}
