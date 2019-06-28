package com.hongdu.yuanmayuedu.encapsulationhttp.httpentity;

import java.util.Date;

/**
 * @ClassName RespHeader
 * @Description
 *  响应头
 * @Author dudu
 * @Date 2019/6/24 9:34
 * @Version 1.0
 */
public class RespHeader extends Header {
//    HTTP 响应消息头部实例：
//    Status：OK - 200                <-- 响应状态码，表示 web 服务器处理的结果。
//    Date：Sun, 01 Jun 2008 12:35:47 GMT
//    Server：Apache/2.0.61 (Unix)
//    Last-Modified：Sun, 01 Jun 2008 12:35:30 GMT
//    Accept-Ranges：bytes
//    Content-Length：18616
//    Cache-Control：max-age=120
//    Expires：Sun, 01 Jun 2008 12:37:47 GMT
//    Content-Type：application/xml
//    Age：2
//    X-Cache：HIT from 236-41.D07071951.sina.com.cn  <--反向代理服务器使用的 HTTP 头部
//    Via：1.0 236-41.D07071951.sina.com.cn:80 (squid/2.6.STABLE13)
//    Connection：close

    //响应头的特殊部分
    //Accept-Ranges：WEB服务器表明自己是否接受获取其某个实体的一部分（比如文件的一部分）的请求。bytes：表示接受，none：表示不接受
    //Content-Encoding：WEB服务器表明自己使用了什么压缩方法（gzip，deflate）压缩响应中的对象。
    private String contentEncoding;
    //Content-Language：WEB 服务器告诉浏览器自己响应的对象的语言
    private String contentLanguage;
    //Content-Length： WEB 服务器告诉浏览器自己响应的对象的长度。例如：Content-Length: 26012
    // Content-Range： WEB 服务器表明该响应包含的部分对象为整个对象的哪个部分 例如：Content-Range: bytes 21010-47021/47022
    private String contentRange;
    //  Content-Type： WEB 服务器告诉浏览器自己响应的对象的类型。例如：Content-Type：application/xml
    //Expired：WEB服务器表明该实体将在什么时候过期，对于过期了的对象，只有在跟WEB服务器验证了其有效性后，才能用来响应客户请求。是 HTTP/1.0 的头部
    //Last-Modified：WEB 服务器认为对象的最后修改时间，比如文件的最后修改时间，动态页面的最后产生时间等等
    //Location：WEB 服务器告诉浏览器，试图访问的对象已经被移到别的位置了，到该头部指定的位置去取。
    private String location;
    //Server: WEB 服务器表明自己是什么软件及版本等信息。 例如：Server：Apache/2.0.61 (Unix)
    private String server;
    // 响应状态码，表示 web 服务器处理的结果。
    private String status;
    private Date date;
    private String lastModified;
    private String cacheControl;
    private String acceptRanges;
    private String contentLength;
    private String expires;
    private String contentType;
    private String age;
    //X-Cache：HIT from 236-41.D07071951.sina.com.cn  <--反向代理服务器使用的 HTTP 头部
    private String xCache;
    private String via;
    private String connection;


    public String getContentEncoding() {
        return contentEncoding;
    }

    public void setContentEncoding(String contentEncoding) {
        this.contentEncoding = contentEncoding;
    }

    public String getContentLanguage() {
        return contentLanguage;
    }

    public void setContentLanguage(String contentLanguage) {
        this.contentLanguage = contentLanguage;
    }

    public String getContentRange() {
        return contentRange;
    }

    public void setContentRange(String contentRange) {
        this.contentRange = contentRange;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getServer() {
        return server;
    }

    public void setServer(String server) {
        this.server = server;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getLastModified() {
        return lastModified;
    }

    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }

    public String getCacheControl() {
        return cacheControl;
    }

    public void setCacheControl(String cacheControl) {
        this.cacheControl = cacheControl;
    }

    public String getAcceptRanges() {
        return acceptRanges;
    }

    public void setAcceptRanges(String acceptRanges) {
        this.acceptRanges = acceptRanges;
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

    public String getExpires() {
        return expires;
    }

    public void setExpires(String expires) {
        this.expires = expires;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getxCache() {
        return xCache;
    }

    public void setxCache(String xCache) {
        this.xCache = xCache;
    }

    public String getVia() {
        return via;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public String getConnection() {
        return connection;
    }

    public void setConnection(String connection) {
        this.connection = connection;
    }
}
