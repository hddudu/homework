package com.hongdu.gupao.wrapper;

import lombok.Data;

/**
 * @ClassName BaseObject
 * @Description 原始的对象 ： 待包装扩展的对象 ： 万事万物的对象 ： 对世界进行抽象属性化
 * @Author dudu
 * @Date 2019/7/15 11:56
 * @Version 1.0
 */
@Data
public class BaseObject {
    //万事万物 都是 对象 那么对象本身就是一个属性 成员
    private Object object;
    //
    private String name;
}
