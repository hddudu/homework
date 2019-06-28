package com.hongdu.yuanmayuedu.encapsulationhttp.test;

/**
 * @ClassName CloseAccountRequest
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/27 13:06
 * @Version 1.0
 */
public class CloseAccountRequest {
    private String accessCode;// 接入码
    private String requestId;// 请求id
    private String cardNo;// 从业资格证物理卡号
    private String idNo;// 从业资格证号
    private String userIdent;// 客户证件号
    private String sign;// 数据签名

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getUserIdent() {
        return userIdent;
    }

    public void setUserIdent(String userIdent) {
        this.userIdent = userIdent;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

}


