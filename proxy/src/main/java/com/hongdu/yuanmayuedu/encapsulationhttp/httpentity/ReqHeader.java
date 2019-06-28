package com.hongdu.yuanmayuedu.encapsulationhttp.httpentity;

/**
 * @ClassName ReqHeader
 * @Description
 *  请求头
 * @Author dudu
 * @Date 2019/6/24 9:34
 * @Version 1.0
 */
public class ReqHeader {

//    HTTP 请求消息头部实例：
//    Host：rss.sina.com.cn
//    User-Agent：Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.8.1.14) Gecko/20080404 Firefox/2.0.0.14
//    Accept：text/xml,application/xml,application/xhtml+xml,text/html;q=0.9,text/plain;q=0.8,image/png,*/*;q=0.5
//    Accept-Language：zh-cn,zh;q=0.5
//    Accept-Encoding：gzip,deflate
//    Accept-Charset：gb2312,utf-8;q=0.7,*;q=0.7
//    Keep-Alive：300
//    Connection：keep-alive
//    Cookie：userId=C5bYpXrimdmsiQmsBPnE1Vn8ZQmdWSm3WRlEB3vRwTnRtW   <-- Cookie
//    If-Modified-Since：Sun, 01 Jun 2008 12:05:30 GMT
//    Cache-Control：max-age=0

    //请求头的特殊部分
    //通常指定压缩方法，是否支持压缩，支持什么压缩方法 （gzip，deflate）
    //Host：客户端指定自己想访问的WEB服务器的域名/IP 地址和端口号。
    private String host;
    //Referer：浏览器向 WEB 服务器表明自己是从哪个网页/URL 获得/点击当前请求中的网址/URL。 Referer：http://www.sina.com/
    private String referer;
    private String usreAgent;
    private String accept;
    private String acceptLanguage;
    private String acceptEncoding;
    private String acceptCharset;
    private String connectionType;//keep-alive
    private String cookie;
    private String ifModifiedSince;
    private String cacheControl;//

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public String getUsreAgent() {
        return usreAgent;
    }

    public void setUsreAgent(String usreAgent) {
        this.usreAgent = usreAgent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getAcceptLanguage() {
        return acceptLanguage;
    }

    public void setAcceptLanguage(String acceptLanguage) {
        this.acceptLanguage = acceptLanguage;
    }

    public String getAcceptEncoding() {
        return acceptEncoding;
    }

    public void setAcceptEncoding(String acceptEncoding) {
        this.acceptEncoding = acceptEncoding;
    }

    public String getAcceptCharset() {
        return acceptCharset;
    }

    public void setAcceptCharset(String acceptCharset) {
        this.acceptCharset = acceptCharset;
    }

    public String getConnectionType() {
        return connectionType;
    }

    public void setConnectionType(String connectionType) {
        this.connectionType = connectionType;
    }

    public String getCookie() {
        return cookie;
    }

    public void setCookie(String cookie) {
        this.cookie = cookie;
    }

    public String getIfModifiedSince() {
        return ifModifiedSince;
    }

    public void setIfModifiedSince(String ifModifiedSince) {
        this.ifModifiedSince = ifModifiedSince;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }
}
