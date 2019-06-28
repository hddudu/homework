package com.hongdu.yuanmayuedu.encapsulationhttp.httpentity;

/**
 * @ClassName Header
 * @Description
 *  http的请求头和响应头部分
 * @Author dudu
 * @Date 2019/6/24 9:27
 * @Version 1.0
 */
public class Header {

    //请求方式 ： post get  put delete
    private String reqMethod;
    //采用组成方式 : 请求协议 请求域名 端口号 uri（） 请求资源路径
    //private String url;
    private String protocol;
    //Accept：告诉WEB服务器自己接受什么介质类型，*/* 表示任何类型，type/* 表示该类型下的所有子类型，type/sub-type
    //接收文件类型： 从服务端响应的文件在浏览器端进行对应的解析 : 指定客户端能够接收的内容格式类型
    private String accept;
    //连接方式 ： http 1.1默认都是持久连接 (keep-alive) ; else : close
    private String connectionType;
    //浏览器与操作系统的相关信息
    private String userAgent;

    //接收解析的语言 : 指定客户端能够接受的语言类型
    private String acceptLanguage;
    //缓存方式
    private String cacheType;
    //接收的字符集
    private String acceptCharset;

    //Age：当代理服务器用自己缓存的实体去响应请求时，用该头部表明该实体从产生到现在经过多长时间了。
    private String age;

    //Authorization：当客户端接收到来自WEB服务器的 WWW-Authenticate 响应时，该头部来回应自己的身份验证信息给WEB服务器。
    private String authorization;
}
