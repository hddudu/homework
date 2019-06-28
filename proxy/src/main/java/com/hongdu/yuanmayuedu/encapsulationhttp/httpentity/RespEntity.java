package com.hongdu.yuanmayuedu.encapsulationhttp.httpentity;

import java.util.Vector;

/**
 * @ClassName RespEntity
 * @Description
 *  响应实体
 * @Author dudu
 * @Date 2019/6/24 10:04
 * @Version 1.0
 */
public class RespEntity extends RespHeader {

    private String host;
    private String urlString;
    private Integer defaultPort;
    private String file;
    private String path;
    private Integer port;
    private String protocol;
    private String query;
    private String ref;
    private String userInfo;
    private String content;//响应内容
    private Integer code;//响应码 同 status
    private String message;//响应信息
    private String method;
    private Integer connectionTimeout;
    private Integer readTimeout;
    private Vector<String> contentCollection;

    public String getUrlString() {
        return urlString;
    }

    public void setUrlString(String urlString) {
        this.urlString = urlString;
    }

    public Integer getDefaultPort() {
        return defaultPort;
    }

    public void setDefaultPort(Integer defaultPort) {
        this.defaultPort = defaultPort;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Integer getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Integer connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public Integer getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Integer readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Vector<String> getContentCollection() {
        return contentCollection;
    }

    public void setContentCollection(Vector<String> contentCollection) {
        this.contentCollection = contentCollection;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @Override
    public String toString() {
        return "RespEntity{" +
                "urlString='" + urlString + '\'' +
                ", defaultPort=" + defaultPort +
                ", file='" + file + '\'' +
                ", path='" + path + '\'' +
                ", port=" + port +
                ", protocol='" + protocol + '\'' +
                ", query='" + query + '\'' +
                ", ref='" + ref + '\'' +
                ", userInfo='" + userInfo + '\'' +
                ", content='" + content + '\'' +
                ", code=" + code +
                ", message='" + message + '\'' +
                ", method='" + method + '\'' +
                ", connectionTimeout=" + connectionTimeout +
                ", readTimeout=" + readTimeout +
                ", contentCollection=" + contentCollection +
                '}';
    }
}
