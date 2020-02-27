package com.hongdu.test.jicheng;

import java.util.Objects;

/**
 * @ClassName BaseProcessRequest
 * @Description TODO
 * @Author dudu
 * @Date 2020/2/26 14:40
 * @Version 1.0
 */
public class BaseProcessRequest {

    private Class curClass;//当前的请求对象 类 反射很重要综合任务提交试用；；
    /**
     * ESB传入的  审核类型 1、授权通过；2、挂起；3、授权不通过；4、超时 ----》》》》》传入的参数
     */
    private String esbOperType;
    /**
     * ESB传入的 授权审核柜员号
     */
    private String esbAuthUser;

    public BaseProcessRequest() {
    }

    public Class getCurClass() {
        return curClass;
    }

    public void setCurClass(Class curClass) {
        this.curClass = curClass;
    }

    public String getEsbOperType() {
        return esbOperType;
    }

    public void setEsbOperType(String esbOperType) {
        this.esbOperType = esbOperType;
    }

    public String getEsbAuthUser() {
        return esbAuthUser;
    }

    public void setEsbAuthUser(String esbAuthUser) {
        this.esbAuthUser = esbAuthUser;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BaseProcessRequest)) return false;
        BaseProcessRequest that = (BaseProcessRequest) o;
        return curClass.equals(that.curClass) &&
                esbOperType.equals(that.esbOperType) &&
                esbAuthUser.equals(that.esbAuthUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(curClass, esbOperType, esbAuthUser);
    }
}
