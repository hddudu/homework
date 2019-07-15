package com.hongdu.gupao.wrapper;

/**
 * @ClassName BaseWrapperTest
 * @Description 测试
 * @Author dudu
 * @Date 2019/7/15 13:36
 * @Version 1.0
 */
public class BaseWrapperTest {

    public static void main(String[] args) throws Exception {
        BaseSpring baseSpring = new BaseSpring();
        baseSpring.setAop("aop");
        baseSpring.setIoc("ioc");
        baseSpring.setDi("di");
        baseSpring.setOop("oop");

        System.out.println(baseSpring);
        System.out.println("=================包装后：：：：=================================");
        BaseSpringProcessor processor = new BaseSpringProcessor();
        Object object = processor.postProcessAfterInitialization(baseSpring);
        System.out.println(object.toString());
        /**
         * 包装前:
         * BaseSpring(oop=oop, ioc=ioc, di=di, aop=aop)
         * =================包装后：：：：=================================
         * BaseWrapperWithAttr(soa=soa, nio=nio, baseSpring=BaseSpring(oop=oop, ioc=ioc, di=di, aop=aop))
         */
    }
}
