package com.hongdu.mybatis.ormchongxie.bean;

import java.util.List;
import java.util.Map;

/**
 * @ClassName TableInfo
 * @Description
 *  表信息：
 *   表名
 *   字段信息
 *   主键信息
 *   联合主键
 *
 * @Author dudu
 * @Date 2019/6/30 9:56
 * @Version 1.0
 */
public class TableInfo {

    /**
     * 表名
     */
    private String name;

    /**
     * 字段信息 ： 多个
     * 字段名和字段信息 :
     */
    private Map<String, ColumnInfo> columns;

    /**
     * 唯一主键 ： 也是字段 ： 特别性
     *
     */
    private ColumnInfo onlyPriKey;

    /**
     * 联合主键
     */
    private List<ColumnInfo> priKeys;

    public TableInfo(String name, Map<String, ColumnInfo> columns, ColumnInfo onlyPriKey) {
        this.name = name;
        this.columns = columns;
        this.onlyPriKey = onlyPriKey;
    }

    public TableInfo(String name, Map<String, ColumnInfo> columns, List<ColumnInfo> priKeys) {
        this.name = name;
        this.columns = columns;
        this.priKeys = priKeys;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, ColumnInfo> getColumns() {
        return columns;
    }

    public void setColumns(Map<String, ColumnInfo> columns) {
        this.columns = columns;
    }

    public ColumnInfo getOnlyPriKey() {
        return onlyPriKey;
    }

    public void setOnlyPriKey(ColumnInfo onlyPriKey) {
        this.onlyPriKey = onlyPriKey;
    }

    public List<ColumnInfo> getPriKeys() {
        return priKeys;
    }

    public void setPriKeys(List<ColumnInfo> priKeys) {
        this.priKeys = priKeys;
    }
}
