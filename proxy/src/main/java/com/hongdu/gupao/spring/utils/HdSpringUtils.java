package com.hongdu.gupao.spring.utils;

import com.hongdu.filescaozuo.FileSuffixType;
import com.hongdu.hdutil.HdJavaEveUtils;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 *   https://www.cnblogs.com/liuzhipeng/p/7816507.html
 *
 * @ClassName HdSpringUtils
 * @Description spring涉及的工具类
 * (1).Test.class.getResource("")
 * 得到的是当前类FileTest.class文件的URI目录。不包括自己！
 * (2).Test.class.getResource("/")
 * 得到的是当前的classpath的绝对URI路径。
 * (3).Thread.currentThread().getContextClassLoader().getResource("")
 * 得到的也是当前ClassPath的绝对URI路径。
 * (4).Test.class.getClassLoader().getResource("")
 * 得到的也是当前ClassPath的绝对URI路径。
 * (5).ClassLoader.getSystemResource("")
 * 得到的也是当前ClassPath的绝对URI路径。
 * 尽量不要使用相对于System.getProperty("user.dir")当前用户目录的相对路径，后面可以看出得出结果五花八门。
 * (6) new File("").getAbsolutePath()也可用
 * @Author dudu
 * @Date 2019/7/3 18:32
 * @Version 1.0
 */
public class HdSpringUtils {

    private static HdSpringUtils instance = null;

    //根据包路径 读取文件 : 将文件存入到容器中
    //容器可以是list或者map

    public static Map<String, String> classMap = new HashMap<>(128);

    /**
     * 扫描类文件到map容器中：
     *  根据包路径 + 文件名 ===》 get 什么
     * @param packageName
     */
    public void doScan2Map(String packageName) {
        String temp = packageName.replaceAll("\\.", "/");
        URL url = this.getClass().getClassLoader().getResource(temp);
        /**
         * 文件路径
         */
        //System.out.println(url);//
        File file = new File(url.getFile());
        for (File f : file.listFiles()) {
            if(f.isDirectory()) {
                doScan2Map(packageName + "." + f.getName());
            } else {
                if(!f.getName().endsWith(".class")) {
                    continue;
                } else {
                    String clazzName = packageName + "." + f.getName().replaceAll(".class","");
                    classMap.put(clazzName, null);
                }
            }
        }
    }
    private void doScanner(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.","/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if(file.isDirectory()){ doScanner(scanPackage + "." +  file.getName());}else {
                if(!file.getName().endsWith(".class")){continue;}
                String clazzName = (scanPackage + "." + file.getName().replace(".class",""));
                classMap.put(clazzName,null);
            }
        }
    }
    private void doScanner2(String scanPackage) {
        URL url = this.getClass().getClassLoader().getResource(scanPackage.replaceAll("\\.","/"));
        File classDir = new File(url.getFile());
        for (File file : classDir.listFiles()) {
            if(file.isDirectory()){ doScanner(scanPackage + "." +  file.getName());}else {
                if(!file.getName().endsWith(".class")){continue;}
                String clazzName = (scanPackage + "." + file.getName().replace(".class",""));
                classMap.put(clazzName,null);
            }
        }
    }


    public static void main(String[] args) throws Exception {
        String s = "G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/classes/com/hongdu";
        URL url = HdSpringUtils.class.getClassLoader().getResource("");
        System.out.println(url);
        //file:/G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/classes/
        URL url1 = HdSpringUtils.class.getResource("");
        System.out.println(url1);
        //file:/G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/classes/com/hongdu/gupao/spring/utils/
        URL url2 = HdSpringUtils.class.getResource("/");
        System.out.println(url2);
        //file:/G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/classes/
        URL url3 = HdSpringUtils.class.getResource("/com/hongdu");
        System.out.println(url3);
        System.out.println(url3.getFile());
        File file = new File(url3.getFile());
        System.out.println("文件遍历开始---------------------------------------");
        HdJavaEveUtils.printFiles(file);
        System.out.println("文件遍历结束---------------------------------------");
        //file:/G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/classes/com/hongdu
        URL url4 = HdSpringUtils.class.getResource("/com/hongdu");
        System.out.println(url4);

        HdSpringUtils instance = new HdSpringUtils();
        System.out.println("----------------------------------ClassLoader加载得到的路径开始----------");
        URL url5 = instance.getThisUrl();
        System.out.println(url5);
//        HdSpringUtils.getInstance().doScan2Map("com.hongdu");
//        HdJavaEveUtils.printMap(classMap);
    }

    /**
     *
     * @return
     */
    private  URL getThisUrl() {
        /**
         * classLoader ： 相对路径 前面不能加斜杠
         */
        URL url5 = this.getClass().getClassLoader().getResource("com/hongdu" );
        return url5;
    }

    public static HdSpringUtils getInstance() {
        if(instance == null) {
            instance = new HdSpringUtils();
            return  instance;
        }
        return instance;
    }

    public HdSpringUtils() {}

    /**
     * 扫描到 list中 不太合适， 因为要用到的时候查找太慢了！
     * @param packageName
     */
    public static void doScan2List(String packageName) {

    }
    @Test
    public void test() {
        System.out.println(this.getClass().getClassLoader().getResource(""));
        String packageName = "com.hongdu";
        System.out.println(packageName.replaceAll("\\.", "/"));
        System.out.println(packageName);
        doScan2Map(packageName);
        HdJavaEveUtils.printMap(classMap);
    }
    @Test
    public void scanPackageTest() {
        System.out.println(this.getClass().getClassLoader().getResource(""));
        String packageName = "com.hongdu";
        System.out.println(packageName.replaceAll("\\.", "/"));
        System.out.println(packageName);
        doScanner(packageName);
        HdJavaEveUtils.printMap(classMap);
    }
    @Test
    public void scanPackage2Test() {
        System.out.println(this.getClass().getClassLoader().getResource(""));
        String packageName = "com.hongdu";
        System.out.println(packageName.replaceAll("\\.", "/"));
        System.out.println(packageName);
        doScanner2(packageName);
        HdJavaEveUtils.printMap(classMap);
    }

    @Test
    public void test03() {
        System.out.println(this.getClass().getClassLoader().getResource(""));
        doScan2Map("com.hongdu");
        HdJavaEveUtils.printMap(classMap);
    }

    @Test
    public void enumTest() {
        System.out.println(FileSuffixType.valueOf(FileSuffixType.SQL.getSuffix()));
    }


}
