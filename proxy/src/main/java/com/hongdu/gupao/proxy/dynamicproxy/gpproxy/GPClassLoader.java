package com.hongdu.gupao.proxy.dynamicproxy.gpproxy;

import java.io.*;

/**
 * 沽泡类加载器
 *  首先：  ①类路径文件 ： 就是一个文件 $Proxy0.java
 *          Ⅱ加载文件到内存中成为对象
 *              需要找到对应的类
 *              然后defineClass
 */
public class GPClassLoader extends ClassLoader{

    private File classPathFile;

    public GPClassLoader() {
        String classPath = GPClassLoader.class.getResource("").getPath();
        this.classPathFile = new File(classPath);
    }

    /**
     * 有文件时的构造器
     * @param classPathFile
     */
    public GPClassLoader(File classPathFile) {
        this.classPathFile = classPathFile;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        String className = GPClassLoader.class.getPackage().getName() + "." + name;//找到当前类加载器所在的包名 没有package

        if(classPathFile != null) {
            //找到文件
            File classFile = new File(classPathFile, name.replaceAll("\\.",".") + ".class");
            if(classFile.exists()) {
                FileInputStream fis = null;
                ByteArrayOutputStream bos = null;
                try {
                    fis = new FileInputStream(classFile);
                    bos = new ByteArrayOutputStream();//默认32 new byte[32]
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = fis.read(buffer)) != -1) {
                        bos.write(buffer, 0, len);//将读到的len字节写到bos流中
                    }
                    return defineClass(className, bos.toByteArray(), 0, bos.size());
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }

            }
        }
        return null;
 }

    public static void main(String[] args) throws Exception{
        String className = GPClassLoader.class.getPackage().getName() + "." + args;
        System.out.println("className: " + className );

        System.out.println("getPath： "+GPClassLoader.class.getResource("").getPath());
        ///G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/classes/com/hongdu/gupao/proxy/dynamicproxy/gpproxy/
        System.out.println("getAuthority： "+GPClassLoader.class.getResource("").getAuthority());
        //getAuthority：
        System.out.println("getContent： "+GPClassLoader.class.getResource("").getContent());
        //getContent： sun.net.www.content.text.PlainTextInputStream@2503dbd3
        System.out.println("getDefaultPort： "+GPClassLoader.class.getResource("").getDefaultPort());
        //getDefaultPort： -1
        System.out.println("getFile： "+GPClassLoader.class.getResource("").getFile());
        //getFile： /G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/classes/com/hongdu/gupao/proxy/dynamicproxy/gpproxy/
        System.out.println("getProtocol： "+GPClassLoader.class.getResource("").getProtocol());
        //getProtocol： file
        System.out.println("getQuery： "+GPClassLoader.class.getResource("").getQuery());
        //getQuery： null
        System.out.println("getRef： "+GPClassLoader.class.getResource("").getRef());
        //getRef： null
        System.out.println("getUserInfo： "+GPClassLoader.class.getResource("").getUserInfo());
        //getUserInfo： null
    }
}
