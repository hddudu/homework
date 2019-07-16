package com.hongdu.gupao.spring.spring.webmvc.servlet;

import com.hongdu.gupao.spring.annotation.HdRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HdHandlerAdapter
 * @Description 处理器 适配器
 * @Author dudu
 * @Date 2019/7/15 18:43
 * @Version 1.0
 */
public class HdHandlerAdapter {

    /**
     * 判断是否支持 处理器的类型
     * @param handler
     * @return
     */
    public boolean supports(Object handler) {
        return handler instanceof HdHandlerMapping;
    }

    /**
     * 处理 成为一个 模型视图
     * @param request
     * @param response
     * @param hanlder
     * @return
     */
    HdModelAndView handle(HttpServletRequest request, HttpServletResponse response,
                            Object hanlder ) throws InvocationTargetException, IllegalAccessException {
        HdHandlerMapping handlerMapping = (HdHandlerMapping) hanlder;

        //把方法的形参列表和request的参数列表所在顺序进行一一对应
        Map<String, Integer> paramIndexMapping = new HashMap<>();

        //提取方法中加了注解的参数
        //把方法上的注解拿到，得到的是一个二维数组
        //因为一个参数可以有多个注解，而一个方法又有多个参数
        Annotation[][] annotations = handlerMapping.getMethod().getParameterAnnotations();
        for (int i = 0; i < annotations.length; i ++) {
            for (Annotation j : annotations[i]) {
                if(j instanceof HdRequestParam) {
                    String paramName = ((HdRequestParam) j).value();
                    if(!"".equals(paramName.trim())) {
                        paramIndexMapping.put(paramName, i);
                    }
                }
            }
        }

        //提取方法中的request和response参数
        Class<?>[] paramsTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < paramsTypes.length ; i ++) {
            Class<?> type = paramsTypes[i];
            if(type == HttpServletRequest.class ||
                    type == HttpServletResponse.class){
                paramIndexMapping.put(type.getName(),i);
            }
        }

        //获得方法的形参列表
        Map<String, String[]> params = request.getParameterMap();

        //实参列表
        Object[] paramValues = new Object[paramsTypes.length];

        //参数
        for (Map.Entry<String, String[]> parm : params.entrySet()) {
            //name=hogndu&name=hongdu ==> name=[hongd,hongdu]
            String value = Arrays.toString(parm.getValue()).replaceAll("\\[|\\]","")
                    .replaceAll("\\s",",");
            if(!paramIndexMapping.containsKey(parm.getKey())){continue;}

            //paramIndexMapping : 已经将参数类型 和 参数顺序进行了对应
            // HttpServletRequest 0
            // HttpServletResponse 1
            // String name 2
            int index = paramIndexMapping.get(parm.getKey());
            paramValues[index] = caseStringValue(value,paramsTypes[index]);
        }
        //参数 的 HttpServletRequest 的位置
        if(paramIndexMapping.containsKey(HttpServletRequest.class.getName())) {
            int reqIndex = paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = request;
        }

        //参数的 ： HttpServletResponse 的位置
        if(paramIndexMapping.containsKey(HttpServletResponse.class.getName())) {
            int respIndex = paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = response;
        }

        //反射调用方法
        Object result = handlerMapping.getMethod().invoke(handlerMapping.getController(),paramValues);
        if(result == null || result instanceof Void){ return null; }

        boolean isModelAndView = handlerMapping.getMethod().getReturnType() == HdModelAndView.class;
        if(isModelAndView){
            return (HdModelAndView) result;
        }
        return null;


    }

    private Object caseStringValue(String value, Class<?> paramsType) {
        if(String.class == paramsType){
            return value;
        }
        //如果是int
        if(Integer.class == paramsType){
            return Integer.valueOf(value);
        }
        else if(Double.class == paramsType){
            return Double.valueOf(value);
        }else {
            if(value != null){
                return value;
            }
            return null;
        }
        //如果还有double或者其他类型，继续加if
        //这时候，我们应该想到策略模式了
        //在这里暂时不实现，希望小伙伴自己来实现

    }


}
