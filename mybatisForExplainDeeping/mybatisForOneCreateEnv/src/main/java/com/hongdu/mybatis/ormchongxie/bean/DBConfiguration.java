package com.hongdu.mybatis.ormchongxie.bean;

/**
 * @ClassName DBConfiguration
 * @Description
 *  数据库的完整配置
 *  核心配置类，用于存储配置文件的信息
 *  数据库类型 ： mysql oracle sqlserver
 *  驱动包
 *  用户名
 *  密码
 *  访问数据库的url
 *  生成类的包路径
 *  连接池的大小： 最小连接数
 *          最大连接数
 *
 * @Author dudu
 * @Date 2019/6/30 10:06
 * @Version 1.0
 */
public class DBConfiguration {

    private String driver;
    private String userName;
    private String password;
    private String useDB;
    /**
     *  // jdbc:mysql://localhost:3306/test
     */
    private String url;
    /**
     * //jk.zmn.auto.pojo
     */
    private String packageName;

    private int poolMinSize;

    private int poolMaxSize;

    public DBConfiguration() {
    }

    public DBConfiguration(String driver, String userName, String password, String useDB, String url, String packageName, int poolMinSize, int poolMaxSize) {
        this.driver = driver;
        this.userName = userName;
        this.password = password;
        this.useDB = useDB;
        this.url = url;
        this.packageName = packageName;
        this.poolMinSize = poolMinSize;
        this.poolMaxSize = poolMaxSize;
    }

    public DBConfiguration(String driver, String userName, String password, String useDB, String url, String packageName) {
        this.driver = driver;
        this.userName = userName;
        this.password = password;
        this.useDB = useDB;
        this.url = url;
        this.packageName = packageName;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUseDB() {
        return useDB;
    }

    public void setUseDB(String useDB) {
        this.useDB = useDB;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public int getPoolMinSize() {
        return poolMinSize;
    }

    public void setPoolMinSize(int poolMinSize) {
        this.poolMinSize = poolMinSize;
    }

    public int getPoolMaxSize() {
        return poolMaxSize;
    }

    public void setPoolMaxSize(int poolMaxSize) {
        this.poolMaxSize = poolMaxSize;
    }
}
