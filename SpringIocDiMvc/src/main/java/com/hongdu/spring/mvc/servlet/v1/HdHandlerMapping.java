package com.hongdu.spring.mvc.servlet.v1;

import java.lang.reflect.Method;
import java.util.Comparator;
import java.util.regex.Pattern;

/**
 * @ClassName HdHandlerMapping
 * @Description 映射器对象
 *  对url进行去重处理
 * @Author dudu
 * @Date 2019/7/17 10:01
 * @Version 1.0
 */
public class HdHandlerMapping  implements Comparator {
    //控制器
    private Object controller;
    //方法
    private Method method;
    //url路径匹配
    private Pattern pattern;

    public HdHandlerMapping(Object controller, Method method, Pattern pattern) {
        this.controller = controller;
        this.method = method;
        this.pattern = pattern;
    }

    public Object getController() {
        return controller;
    }

    public Method getMethod() {
        return method;
    }

    public Pattern getPattern() {
        return pattern;
    }



    @Override
    public int compare(Object o1, Object o2) {
        //必须都都是 handlerMapping对象

        return 0;
    }
}
