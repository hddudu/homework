package com.hongdu.gupao.spring.implinterface;

/**
 * @ClassName AnnotationInterfaceImpl1
 * @Description
 * @Author dudu
 * @Date 2019/7/8 9:40
 * @Version 1.0
 */
public class AnnotationInterfaceImpl3 implements AnnotationInterface, AnnotationInterface2 {

    @Override
    public String getType(int type) {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }

    @Override
    public String getDoc() {
        return null;
    }
}
