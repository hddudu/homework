package com.hongdu.yuanmayuedu.encapsulationhttp.httpentity;

import java.io.File;
import java.io.InputStream;

/**
 * @ClassName FormFile
 * @Description 上传文件需要的数据
 * @Author dudu
 * @Date 2019/6/24 10:12
 * @Version 1.0
 */
public class FormFile {

    /**
     * 上传文件的数据 : 字节数据
     */
    private byte[] data;

    /**
     * 上传文件需要的方式 ： 流传输
     */
    private InputStream inputStream;

    /**
     * 上传的文件来源 哪个文件
     */
    private File file;

    private String fileName;

    private String paramName;

    private String contentType = "application/octet-stream";//字节流传输

    /**
     * 传输小文件
     * @param data
     * @param file
     * @param paramName
     * @param contentType
     */
    public FormFile(byte[] data, File file, String paramName, String contentType) {
        this.data = data;
        this.file = file;
        this.paramName = paramName;
        if(contentType != null) {
            this.contentType = contentType;
        }
    }

    /**
     * 传输大文件
     * @param file
     * @param fileName
     * @param paramName
     * @param contentType
     */
    public FormFile(File file, String fileName, String paramName, String contentType) {
        this.file = file;
        this.fileName = fileName;
        this.paramName = paramName;
        if(contentType != null) {
            this.contentType = contentType;
        }
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}

