package com.hongdu.gupao.mebatis;

/**
 * @ClassName HdSqlSession
 * @Description sql绘画
 * @Author dudu
 * @Date 2019/8/4 15:40
 * @Version 1.0
 */
public class HdSqlSession {

    //配置类
    private HdConfiguration configuration;
    //执行器 : 具体的jdbc操作类
    private HdExecutor executor;

    //构造器初始化
    public HdSqlSession(HdConfiguration configuration, HdExecutor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    //至少需要执行的基本参数

    /**
     * 调用Executor的单条查询
     * @param statementId
     * @param parameter
     * @param <T>
     * @return
     */
    public <T> T selectOne(String statementId, Object parameter) {
        //获取执行的sql语句
        String sql = HdConfiguration.sqlMappings.getString(statementId);
        return executor.query(sql, parameter);
    }
    //获取某一个接口的代理对象

    /**
     * 获取一个代理对象
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T getMapper(Class<?> clazz) {
        return configuration.getMapper(clazz, this);
    }

}
