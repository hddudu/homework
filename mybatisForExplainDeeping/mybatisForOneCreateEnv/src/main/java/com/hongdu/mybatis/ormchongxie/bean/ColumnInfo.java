package com.hongdu.mybatis.ormchongxie.bean;

/**
 * @ClassName ColumnInfo
 * @Description 字段信息
 *  用于封装数据表中一个字段的信息
 *  *      字段名
 *  *      字段类型
 *  *      字段的键类型 0 普通键 1 主键 2 外键
 * @Author dudu
 * @Date 2019/6/30 9:37
 * @Version 1.0
 */
public class ColumnInfo {
    /**
     * 字段名
     */
    private String name;
    /**
     字段类型
     */
    private String dataType;
    /**
     * 字段键类型
     */
    private int keyType;

    public String getName() {
        return name;
    }

    public ColumnInfo() {
    }

    public ColumnInfo(String name, String dataType, int keyType) {
        this.name = name;
        this.dataType = dataType;
        this.keyType = keyType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public int getKeyType() {
        return keyType;
    }

    public void setKeyType(int keyType) {
        this.keyType = keyType;
    }

    @Override
    public String toString() {
        return "ColumnInfo{" +
                "name='" + name + '\'' +
                ", dataType='" + dataType + '\'' +
                ", keyType=" + keyType +
                '}';
    }
}
