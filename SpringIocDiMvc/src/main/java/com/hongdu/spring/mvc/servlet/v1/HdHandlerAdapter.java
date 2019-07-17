package com.hongdu.spring.mvc.servlet.v1;

import com.hongdu.spring.annotation.HdRequestParam;
import com.hongdu.spring.mvc.servlet.HdModelAndViewV1;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HdHandlerAdapter
 * @Description
 * @Author dudu
 * @Date 2019/7/17 10:19
 * @Version 1.0
 */
public class HdHandlerAdapter {

    public boolean supports(Object handler) {
        return handler instanceof HdHandlerMapping;
    }

    /**
     *  这个 handler 里面包装是一个method ： 根据method可以获取形参 和 实参的对应==》 可以完成反射调用
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public HdModelAndViewV1 handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //形参处理
        HdHandlerMapping handlerMapping = (HdHandlerMapping) handler;

        //用一个map ： 存储 ： 形参类型 以及 对应的参数位置
        Map<String, Integer> paramIndexMapping = new HashMap<>(64);

        //获得参数列表
        Annotation[][] pa = handlerMapping.getMethod().getParameterAnnotations();
        //i : 表示的是 注解参数的位置 ：
        //a ： 表示的是 注解参数的形参值
        for (int i = 0; i < pa.length; i ++) {
            for (Annotation a : pa[i]) {
                String paramName = ((HdRequestParam)a).value();
                if(!"".equals(paramName)) {
                    paramIndexMapping.put(paramName, i);
                }
            }
        }
        //提取方法中的request和response参数
        Class<?>[] paramsTypes = handlerMapping.getMethod().getParameterTypes();
        for (int i = 0; i < paramsTypes.length ; i ++) {
            Class<?> type = paramsTypes[i];
            if (type == HttpServletRequest.class ||
                    type == HttpServletResponse.class) {
                paramIndexMapping.put(type.getName(), i);
            }
        }

        //实参处理
        //获得方法的形参列表
        Map<String, String[]> params = request.getParameterMap();
        //再定义一个
        //实参列表 ： 用来 对应形参数据
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

        //获取方法的返回值类型
        boolean isMv = handlerMapping.getMethod().getReturnType() == HdModelAndViewV1.class;
        if(isMv) {
            return (HdModelAndViewV1) result;
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
