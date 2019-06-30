package com.hongdu.mybatis.ormchongxie.bean;

/**
 * @ClassName JavaFieldInfo
 * @Description
 *  生成的java类的信息：
 *      属性信息
 *      set方法
 *      get方法
 *
 * @Author dudu
 * @Date 2019/6/30 9:57
 * @Version 1.0
 */
public class JavaFieldInfo {
    /**
     * java属性 成员属性信息
     */
    private String filedInfo;

    /**
     * javaget方法 信息
     */
    private String getFieldInfo;

    /**
     * javaset方法信息
     */
    private String setFieldInf;

    public String getFiledInfo() {
        return filedInfo;
    }

    public void setFiledInfo(String filedInfo) {
        this.filedInfo = filedInfo;
    }

    public String getGetFieldInfo() {
        return getFieldInfo;
    }


    public JavaFieldInfo() {
    }

    public void setGetFieldInfo(String getFieldInfo) {
        this.getFieldInfo = getFieldInfo;
    }

    public String getSetFieldInf() {
        return setFieldInf;
    }

    public void setSetFieldInf(String setFieldInf) {
        this.setFieldInf = setFieldInf;
    }

    public JavaFieldInfo(String filedInfo, String getFieldInfo, String setFieldInf) {
        this.filedInfo = filedInfo;
        this.getFieldInfo = getFieldInfo;
        this.setFieldInf = setFieldInf;
    }
}
