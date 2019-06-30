package com.hongdu.mybatis.ormchongxie.utils;

import com.hongdu.mybatis.ormchongxie.bean.ColumnInfo;
import com.hongdu.mybatis.ormchongxie.bean.DBConfiguration;
import com.hongdu.mybatis.ormchongxie.bean.JavaFieldInfo;
import com.hongdu.mybatis.ormchongxie.bean.TableInfo;
import com.hongdu.mybatis.ormchongxie.core.DBManager;
import com.hongdu.mybatis.ormchongxie.core.TableContext;
import com.hongdu.mybatis.ormchongxie.core.service.TypeConvertorHandler;
import com.hongdu.mybatis.ormchongxie.core.service.impl.MysqlConvertorHandlerImpl;
import org.junit.Test;

import java.io.*;
import java.util.*;

/**
 * @ClassName JavaFileUtils
 * @Description
 *  用于生成java源文件的工具类
 * @Author dudu
 * @Date 2019/6/30 17:31
 * @Version 1.0
 */
public class JavaFileUtils {

    /**
     * 数据库配置
     */
    private static DBConfiguration dbConfiguration = DBManager.getDbConfiguration();

    /**
     * java字段信息实体：
     * 将每一个java字段生成对应的 成员属性以及setter和getter方法
     * @param columnInfo
     * @param typeConvertorHandler
     * @return
     */
    public static JavaFieldInfo createJavaFeild(ColumnInfo columnInfo, TypeConvertorHandler typeConvertorHandler) {
        /**
         * 将字段数据类型转换成java数据类型
          */
        String javaType = typeConvertorHandler.jdbcType2JavaType(columnInfo.getDataType());
        String columnName = columnInfo.getName().toLowerCase();

        JavaFieldInfo javaFieldInfo = new JavaFieldInfo();
        //生成属性语句 : 制表符
        javaFieldInfo.setFiledInfo("\tprivate " + javaType + " " + StringUtils.trimUnderLine(columnName) + ";\n");
        StringBuilder sb = new StringBuilder();
        sb.append("\tpublic " + javaType + " " + "get" + StringUtils.upFirstString(columnName) + "() {\n");

        sb.append("\t\treturn " + columnName + ";\n");

        sb.append("\t}\n");

        javaFieldInfo.setGetFieldInfo(sb.toString());

        StringBuilder sb1 = new StringBuilder();
        sb1.append("\tpublic void " + "set" + StringUtils.upFirstString(columnName) + "(" + javaType + " " + columnName + ") {\n");
        sb1.append("\t\t this." + columnName + " = " + columnName + ";\n");
        sb1.append("\t}\n");
        javaFieldInfo.setSetFieldInf(sb1.toString());
        return javaFieldInfo;
    }

    /**
     * 创建java文件
     * @param tableInfo
     * @param typeConvertorHandler
     */
    public static void createJavaFile(TableInfo tableInfo, TypeConvertorHandler typeConvertorHandler) {
        //得到所有的列信息
        Map<String, ColumnInfo> columnInfoMap = tableInfo.getColumns();

        List<JavaFieldInfo> javaFieldInfos = new ArrayList<>();
        Collection<ColumnInfo> values = columnInfoMap.values();//返回的是map的 value的集合 list
        //生成所有的java属性信息和get set方法
        for (ColumnInfo columnInfo : values) {
            JavaFieldInfo javaFieldInfo = createJavaField(columnInfo, typeConvertorHandler);
            javaFieldInfos.add(javaFieldInfo);
        }
        StringBuilder sb = new StringBuilder();
        sb.append("package " + dbConfiguration.getPackageName() + ";\n");
        sb.append("import java.sql.*;\n");
        sb.append("import java.util.*;\n\n");

        sb.append("public class " + StringUtils.upFirstString(tableInfo.getName()) + " {\n\n");

        //拼接属性字段
        for (JavaFieldInfo field : javaFieldInfos) {
            sb.append(field.getFiledInfo());
        }
        sb.append("\n");
        //拼接get方法 : 已经成为了get方法名字字符串
        for (JavaFieldInfo getField : javaFieldInfos) {
            sb.append(getField.getGetFieldInfo());
        }
        //拼接set方法 ： 已经成为了set方法名字字符串
        for (JavaFieldInfo setField : javaFieldInfos) {
            sb.append(setField.getSetFieldInf());
        }
        sb.append("}\n");
        System.out.println("生成类信息如下： ");
        System.out.println(sb.toString());
        System.out.println();
        //将生成的字符串 内容输出到文件中
        String classInfo = sb.toString();

        /**
         * 生成包名文件夹
         */
        String filePathFromPackage = PathUtils.getFilePathFromPackage(dbConfiguration.getPackageName());
        /**
         * 生成包路径下面的java空文件
         */
        File file = new File(filePathFromPackage, StringUtils.upFirstString(tableInfo.getName()) + ".java");
        /**
         * 输出字符串到文件中 : 将数据持久化到磁盘上: 输出流（相对于内存）
         */
        BufferedOutputStream bufferedOutputStream = null;
        try {
            bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            /**
             * 将字符串直接字节数组化写入
             * write : 写入的字节来源 ： 写入的起始位置， 将要写入的字节长度
             */
            bufferedOutputStream.write(classInfo.getBytes(), 0, classInfo.getBytes().length);
            /**
             * 刷新内容
             */
            bufferedOutputStream.flush();
        } catch (FileNotFoundException e) {
            System.out.println("文件生成失败！");
        } catch (IOException e) {
            System.out.println("写入文件失败！");
        }finally {
            try {
                bufferedOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("表"+tableInfo.getName()+"对应的类"+StringUtils.upFirstString(tableInfo.getName())+"已自动生成..");

    }

    private static JavaFieldInfo createJavaField(ColumnInfo columnInfo, TypeConvertorHandler typeConvertorHandler) {

        return null;
    }

    public static void createJavaFileToPackage() {
        Map<String, TableInfo> tables = TableContext.tables;
        TypeConvertorHandler convertorHandler = null;
        if(dbConfiguration.getUseDB().equalsIgnoreCase("mysql")) {
            convertorHandler = new MysqlConvertorHandlerImpl();
        }

        /**
         * 遍历map的values
         */
        for (TableInfo tableInfo :tables.values()) {
            createJavaFile(tableInfo, convertorHandler);
        }

    }

    @Test
    public void collectionValuesTest() {
        Map<String, String> stringMap = new HashMap<>();
        stringMap.put("1","11");
        stringMap.put("2","22");
        stringMap.put("3","33");
        stringMap.put("4","44");
        System.out.println(stringMap.values());//[11, 22, 33, 44]
    }
}
