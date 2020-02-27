package com.hongdu.test.jicheng;

import java.util.List;

/**
 * @ClassName ForegoundScanningRequest
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/26 14:46
 * @Version 1.0
 */
public class ForegoundScanningRequest extends BaseProcessRequest {

    /**
     * 上传的影像信息
     */
    private List<EsbImgVo> esbImgVoList;

    //前台补扫 新增 影像数据字段
    private String docId;//影像批次号
    private String docDt;//影像上传日期
    private String docSeq;//影像上传元系统流水号；
    private  String ecmModel;//影像平台模型;
    private  String ecmIp;//影像平台ip;
    private  String ecmUserName;//影像平台ip;
    private  String ecmPort;//影像平台ip;
    private  String ecmPassWord;//影像平台ip;
    private  String ecmFilePart;//影像平台ip;

    public ForegoundScanningRequest() {
    }

    public List<EsbImgVo> getEsbImgVoList() {
        return esbImgVoList;
    }

    public void setEsbImgVoList(List<EsbImgVo> esbImgVoList) {
        this.esbImgVoList = esbImgVoList;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getDocDt() {
        return docDt;
    }

    public void setDocDt(String docDt) {
        this.docDt = docDt;
    }

    public String getDocSeq() {
        return docSeq;
    }

    public void setDocSeq(String docSeq) {
        this.docSeq = docSeq;
    }

    public String getEcmModel() {
        return ecmModel;
    }

    public void setEcmModel(String ecmModel) {
        this.ecmModel = ecmModel;
    }

    public String getEcmIp() {
        return ecmIp;
    }

    public void setEcmIp(String ecmIp) {
        this.ecmIp = ecmIp;
    }

    public String getEcmUserName() {
        return ecmUserName;
    }

    public void setEcmUserName(String ecmUserName) {
        this.ecmUserName = ecmUserName;
    }

    public String getEcmPort() {
        return ecmPort;
    }

    public void setEcmPort(String ecmPort) {
        this.ecmPort = ecmPort;
    }

    public String getEcmPassWord() {
        return ecmPassWord;
    }

    public void setEcmPassWord(String ecmPassWord) {
        this.ecmPassWord = ecmPassWord;
    }

    public String getEcmFilePart() {
        return ecmFilePart;
    }

    public void setEcmFilePart(String ecmFilePart) {
        this.ecmFilePart = ecmFilePart;
    }

    @Override
    public String toString() {
        return "ForegoundScanningRequest{" +
                "esbImgVoList=" + esbImgVoList +
                ", docId='" + docId + '\'' +
                ", docDt='" + docDt + '\'' +
                ", docSeq='" + docSeq + '\'' +
                ", ecmModel='" + ecmModel + '\'' +
                ", ecmIp='" + ecmIp + '\'' +
                ", ecmUserName='" + ecmUserName + '\'' +
                ", ecmPort='" + ecmPort + '\'' +
                ", ecmPassWord='" + ecmPassWord + '\'' +
                ", ecmFilePart='" + ecmFilePart + '\'' +
                '}';
    }
}
