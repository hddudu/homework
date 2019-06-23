package com.hongdu.filescaozuo;

import com.alibaba.fastjson.JSONObject;
import com.hongdu.filescaozuo.bean.GZCustomerCpu;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName CreatFile
 * @Description
 * @Author dudu
 * @Date 2019/6/23 23:12
 * @Version 1.0
 */
public class CreatFile {

    @Test
    public void testCreateFile1() {

    }

    /**
     * F:\\generateFiles\\upload\\1561083934275\\
     * {"brand":103,"enableTime":"2019-05-17T10:13:30","expireTime":"2029-05-17","id":"5202191402017452","model":"电子标签","netId":"5202","userId":"52010117011320064","vehicleId":"贵A3U21W_0","vehicleType":1}
     * @param path
     * @param modulecontent
     * @return
     * @throws IOException
     */
    public static boolean genModuleTpl(String path, String modulecontent)  throws IOException
    {
        path = getUNIXfilePath(path);
        String[] patharray = path.split("\\/");
        String modulepath = "";
        for (int i = 0; i < patharray.length - 1; i++) {
            modulepath += System.getProperty("file.separator") + patharray[i];
        }
        System.out.println("File -=========== modulepath"+modulepath);
        File d = new File(modulepath.substring(1));
        if (!d.exists()) {
//	       if (!pathValidate(modulepath.substring(1))) {
//	         return false;
//	       }
            d.mkdirs();
        }
        try {
            System.out.println("File -=========== path"+path);
            Writer writer = new BufferedWriter(
                    new OutputStreamWriter(
                            new FileOutputStream(path), "UTF-8"));
            writer.write(modulecontent);
            writer.flush();
            writer.close();
	     /*  FileWriter fw = new FileWriter(path); //建立FileWriter对象，并实例化fw
	       //将字符串写入文件
	       fw.write(modulecontent);
	       fw.close();*/
        }
        catch (IOException e) {
            throw e;
        }
        return true;
    }

    /**
     * 从文件名得到UNIX风格的文件绝对路径。
     * @param fileName 文件名
     * @return 对应的UNIX风格的文件路径
     * @since  1.0
     * @see #toUNIXpath(String filePath) toUNIXpath
     */
    public static String getUNIXfilePath(String fileName) {
        File file = new File(fileName);
        return toUNIXpath(file.getAbsolutePath());
    }

    /**
     * 将DOS/Windows格式的路径转换为UNIX/Linux格式的路径。
     * 其实就是将路径中的"\"全部换为"/"，因为在某些情况下我们转换为这种方式比较方便，
     * 某中程度上说"/"比"\"更适合作为路径分隔符，而且DOS/Windows也将它当作路径分隔符。
     * @param filePath 转换前的路径
     * @return 转换后的路径
     * @since  1.0
     */
    public static String toUNIXpath(String filePath) {
        return filePath.replace('\\', '/');
    }

    /**
     * 根据对象实例 和 对象参数生成文件
     * @param object
     * @param paramList
     * @return
     */
    private static JSONObject generateFileByInstanceAndJsonObject(Object object, List<String> paramList) {
        JSONObject retJsonObject = new JSONObject();
        //获取类对象
        Class<?> objClazz = object.getClass();
        //遍历参数
        for (String s : paramList
             ) {
            //s表示字段
            //获取类对象的字段对象
            try {
                Field sFieldObject = objClazz.getDeclaredField(s);
                //private字段设置成可以访问
                sFieldObject.setAccessible(true);
                try {
                    retJsonObject.put(s, sFieldObject.get(object));
                } catch (IllegalAccessException e) {
                    System.out.println( sFieldObject + "字段没有设置访问权限！");
                }
                //获取实例对象的这个字段的值，将它设置到JsonObject中
            } catch (NoSuchFieldException e) {
                System.out.println("没有该实体字段！");
            }
        }
        return retJsonObject;
    }

    private static void printAlibabaJsonObject(JSONObject jsonObject) {
        System.out.println("jsonObject.toString() : " + jsonObject.toString());
        System.out.println("jsonObject.toJSONString() : " + jsonObject.toJSONString());
    }

    @Test
    public void generateFileByInstanceAndJsonObjectTest() {
        GZCustomerCpu cpu = new GZCustomerCpu("123","1223","贵123");
        List<String> paramList = new ArrayList<>();
        paramList.add("netId");
        paramList.add("cpuNum");
        paramList.add("vehicleId");
        JSONObject test = generateFileByInstanceAndJsonObject(cpu, paramList);
        printAlibabaJsonObject(test);
    }

}
