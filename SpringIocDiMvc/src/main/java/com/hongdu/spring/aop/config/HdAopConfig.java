package com.hongdu.spring.aop.config;

import lombok.Data;

/**
 * @ClassName HdAopConfig
 * @Description
 * @Author dudu
 * @Date 2019/7/18 20:25
 * @Version 1.0
 */
@Data
public class HdAopConfig {

    private String pointCut;
    private String aspectBefore;
    private String aspectAfter;
    private String aspectClass;
    private String aspectAfterThrow;
    private String aspectAfterThrowingName;

}
