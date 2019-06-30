package com.hongdu.mybatis.ormchongxie.utils;

import org.junit.Test;

import java.io.File;
import java.io.IOException;

/**
 * @ClassName PathUtils
 * @Description 用来根据包名生成文件夹
 * @Author dudu
 * @Date 2019/6/30 17:18
 * @Version 1.0
 */
public class PathUtils {

    //根据包名，生成包目录

    /**
     * 根据包名，生成包目录
     * @param packageName
     * @return 包所在目录
     */
    public static String getFilePathFromPackage(String packageName) {

        String str = packageName;//com.hongdu.mybatis.ormchongxie
        str = str.replace(".", "\\");//com\\hongdu\\mybatis\\ormchongxie
        File file = new File("");//当前目录
        String cononicalPath = null;
        try {
            //G:\my_maven_workspace_git\javaSpaceForGupao\homework\mybatisForExplainDeeping\mybatisForOneCreateEnv
            cononicalPath = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //拼接生成文件路径
        cononicalPath += "\\src\\" + str;
        File packageFile = new File(cononicalPath);
        if(!packageFile.exists()) {
            packageFile.mkdirs();// 和 mkdir有什么差别
        }
        return cononicalPath;
    }

    @Test
    public void test01() {
        File file = new File("");//当前目录
        String cononicalPath = null;
        try {
            cononicalPath = file.getCanonicalPath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(cononicalPath);
    }

    @Test
    public void getFilePathFromPackageTest() {
        String s = "";
        String s1 = getFilePathFromPackage("com.hongdu.mybatis.ormchongxie.generation");
        System.out.println(s1);
    }

}
