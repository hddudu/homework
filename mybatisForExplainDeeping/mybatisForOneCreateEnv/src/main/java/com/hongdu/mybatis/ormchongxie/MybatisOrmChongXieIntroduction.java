package com.hongdu.mybatis.ormchongxie;

/**
 * @ClassName MybatisOrmChongXieIntroduction
 * @Description
 *      手写mybatis orm映射框架
 *      1： dom4j 读取配置文件
 *      2： 反射
 *      3： 包名
 *      4： sql映射， 结果映射
 *      5： 命名空间唯一性
 *     手写ORM-----------------------》 功能
 *  1.可以根据表结构自动生成实体类
 * 2.可直接插入，修改删除对象，不需要写sql语句
 * 3.在查询时候需要自己写SQL语句，支持多行，单行，某一个字段的查询
 * 4.支持自定义实体类查询，当在进行连表查询的时候，得到的数据是自定义的，可自定义一个实体类进行接收
 * 5.连接使用了连接池，效率相对高一点
 *
 * 1.接口设计
 * Query接口：符合查询，对外提供服务的核心类
 *
 * QueryFactory类：管理query对象
 *
 * TypeConvertor接口：负责数据类型转换
 *
 * TableContext类：负责获取数据库的表结构和类结构的关系，并可以根据表结构生成类结构，
 *
 * DBManager类：根据配置信息，维持连接对象的管理
 * 工具类
 * JdbcUtils  用来封装通用的jdbc操作
 * StringUtils 用来封装常用的字符串操作
 * ReflectUtils  用来封装反射相关的操作
 * PathUtils  用来操作本地文件路径相关的操作
 * JavaFileUtils
 * 核心bean
 *
 * COlumnInfo 封装表中一个字段的信息
 *
 * Configuration 封装整个项目的配置信息
 *
 * TableInfo： 封装一张表的信息
 *
 * JavaFeildInfo:用于封装一个属性和get set方法的数据（在自动生成java文件的时候用）
 *
 *
 * 作者：ABOUTYOU
 * 链接：https://www.imooc.com/article/47338
 * 来源：慕课网
 *
 * @Author dudu
 * @Date 2019/6/30 0:45
 * @Version 1.0
 */
public class MybatisOrmChongXieIntroduction {

}
