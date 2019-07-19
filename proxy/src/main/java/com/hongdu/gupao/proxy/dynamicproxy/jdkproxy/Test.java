package com.hongdu.gupao.proxy.dynamicproxy.jdkproxy;

/**
 * @ClassName Test
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/19 2:16
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        //需要代理的目标类
        Class<?> clz = Class.forName("com.hongdu.gupao.proxy.dynamicproxy.jdkproxy.QueryService");
        Object instanceClass = clz.newInstance();

        ProxyQuery proxyQuery = new ProxyQuery(clz);
        IQueryService queryService = (IQueryService) proxyQuery.getProxy();
        queryService.query("哈哈哈");
        //        ProxyQuery proxyQuery = new ProxyQuery(instanceClass);
//        IQueryService queryService = (IQueryService) proxyQuery.getProxy(new QueryService());
//        IQueryService queryService = (IQueryService) proxyQuery.getProxy(clz.newInstance());
    }
}
