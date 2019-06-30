package com.hongdu.mybatis.ormchongxie.core;

import com.hongdu.mybatis.ormchongxie.bean.ColumnInfo;
import com.hongdu.mybatis.ormchongxie.bean.TableInfo;
import com.hongdu.mybatis.ormchongxie.utils.JavaFileUtils;
import com.hongdu.mybatis.ormchongxie.utils.StringUtils;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TableContext
 * @Description 负责获取数据库的表结构和类结构的关系，并可以根据表结构生成类结构，
 *  根据配置文件，负责管理 IQuery对象
 * @Author dudu
 * @Date 2019/6/30 17:41
 * @Version 1.0
 */
public class TableContext {

    /**
     * 表名为key，表信息对象为value
     */
    public static Map<String, TableInfo> tables = new HashMap<>();

    /**
     * 将po的
     *              Class对象 : 表信息对象
     *  关联起来，便于重用！
     *  类对和表信息管关联做什么用呢？
     */
    public static Map<Class, TableInfo> poClassTableMap = new HashMap<>();

    /**
     * 私有构造化
     */
    private TableContext() {}

    static {
        //初始化获得表的信息
        try {
            Connection connection = DBManager.getConnection();
            /**
             * 数据库元数据：
             */
            DatabaseMetaData dbma = connection.getMetaData();
            ResultSet tableRet = dbma.getTables(null, "%", "%", new String[]{"TABLE"});
            while (tableRet.next()) {
                String tableName = (String) tableRet.getObject("TABLE_NAME");
                TableInfo ti = new TableInfo(tableName
                    , new HashMap<String, ColumnInfo>(), new ArrayList<ColumnInfo>());//表名 + 字段名 + 联合主键
                /**
                 * 表名 + 表信息
                 */
                tables.put(tableName, ti);
                /**
                 * 查询表中的所有字段
                 */
                ResultSet set = dbma.getColumns(null, "%", tableName ,"%");
                while (set.next()) {
                    ColumnInfo ci = new ColumnInfo(set.getString("COLUMN_NAME")
                            ,set.getString("TYPE_NAME"), 0);
                    ti.getColumns().put(set.getString("COLUMN_NAME"), ci);//列名 + 列信息（字段名+数据类型+主键类型）
                }
                /**
                 * 查询t_user表中的主键
                 */
                ResultSet set2 = dbma.getPrimaryKeys(null, "%", tableName);
                while (set2.next()) {
                    ColumnInfo ci2 = (ColumnInfo) ti.getColumns().get(set2.getObject("COLUMN_NAME"));
                    ci2.setKeyType(1);//设置为主键类型
                    ti.getPriKeys().add(ci2);//表信息新增主键
                }
                /**
                 * //取唯一主键。。方便使用。如果是联合主键。则为空！
                 */
                if(ti.getPriKeys().size() > 0) {
                    ti.setOnlyPriKey(ti.getPriKeys().get(0));
                }
            }
        } catch (Exception e) {
            System.out.println("初始化信息失败！");
        }

    }

    //根据数据库表结构生成pojo类
    /**
     *  根据数据库表结构生成pojo类
     */
    public static void updatePoFile(){
        JavaFileUtils.createJavaFileToPackage();
    }
    //加载完数据库中的表，生成pojo之后，把对应的类和对应的表关联起来
    public static void loadTablePoToMap() {
        for (TableInfo tableInfo : tables.values()) {
            //实体类
            Class<?> clazz = null;
            try {
                clazz = Class.forName(DBManager.getDbConfiguration().getPackageName()
                        + "." + StringUtils.upFirstString(tableInfo.getName()));
            } catch (ClassNotFoundException e) {
                System.out.println("加载实体类和表关系失败" + e.getMessage());
            }
            poClassTableMap.put(clazz, tableInfo);
        }
    }

}
