package com.hongdu.gupao.chain;

import java.io.File;
import java.io.FileInputStream;

/**
 * @ClassName DynamicLoaderClass
 * @Description 动态类加载器 :
 *
提供一个自定义的类加载器工具，用于动态的加载给定目录下的class文件，默认从项目的classpath路径下查找；
 * @Author dudu
 * @Date 2019/7/18 17:21
 * @Version 1.0
 */
public class DynamicLoaderClass extends ClassLoader{

    private String path;

    public DynamicLoaderClass() {
        this(Thread.currentThread().getContextClassLoader().getResource("").getPath());
        System.out.println("加载路径" + Thread.currentThread().getContextClassLoader().getResource("").getPath());
    }

    public DynamicLoaderClass(String path) {
        this.path = path;
    }

    @Override
    public Class<?> loadClass(String className) throws ClassNotFoundException {

        Class<?> cls = loadClassByParent(className);
        if(cls == null)
            cls = loadClassBySelf(className);
        return cls;
    }

    @Override
    protected Class<?> findClass(String className) throws ClassNotFoundException {
        byte[] bs =  getResourceForCurrentPath(className);
        //定义class
        return defineClass(className, bs, 0, bs.length);
    }

    private byte[] getResourceForCurrentPath(String className) throws ClassNotFoundException {
        String classBytePath = this.path + "/" + className.replace(".", "/") +".class";
        return getResourceForPath(classBytePath);
    }

    private byte[] getResourceForPath(String classBytePath) throws ClassNotFoundException {
        checkResourceIsExist(classBytePath);
        return readResource(classBytePath);
    }

    /**
     * 读取资源
     * @param classBytePath
     * @return
     */
    private byte[] readResource(String classBytePath) {
        byte[] result = null;
        File file = new File(classBytePath);
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            result = new byte[fis.available()];
            fis.read(result);
            fis.close();
        } catch (Exception e) {

        }
        return result;
    }

    private void checkResourceIsExist(String resourcePath) throws ClassNotFoundException {
        File file = new File(resourcePath);
        if(!file.exists())
            throw new ClassNotFoundException();
    }

    private Class<?> loadClassBySelf(String className) throws ClassNotFoundException {
        return findClass(className);
    }

    private Class<?> loadClassByParent(String className) {
        Class<?> cls = null;
        try {
            cls = super.loadClass(className);
        }catch (Exception e) {
            System.out.println("加载类失败!");
        }
        return cls;
    }
}
