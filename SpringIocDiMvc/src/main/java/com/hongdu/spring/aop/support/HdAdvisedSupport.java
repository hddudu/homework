package com.hongdu.spring.aop.support;

import com.hongdu.spring.aop.aspect.HdMethodAfterAdvice;
import com.hongdu.spring.aop.aspect.HdMethodAfterThrowAdvice;
import com.hongdu.spring.aop.aspect.HdMethodBeforeAdvice;
import com.hongdu.spring.aop.config.HdAopConfig;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName HdAdvisedSupport
 * @Description 代理的工具类： 加载配置文件
 * @Author dudu
 * @Date 2019/7/18 15:51
 * @Version 1.0
 */
public class HdAdvisedSupport {

    /**
     * 代理的目标类
     *  Class<?> sss = Class.forName(beanClassName);
     */
    private Class<?> targetClass;
    /**
     * 代理的目标实例 instance = clazz.newInstance;
     */
    private Object target;
    /**
     * 切面配置类对象：
     *  包含 ：
     *      ponitcut
     *      before
     *      after
     *      aspectClass
     *      afterThrow
     *      aspectAfterThrowingName
     */
    private HdAopConfig aopConfig;

    private Pattern pointCutClassPatter;

    /**
     * 每一个方法对应一个i拦截器链 ：
     * 然后 list那边就不用add 当前方法了
     */
    private transient Map<Method, List<Object>> methodCache;

    public HdAdvisedSupport() {
    }

    public HdAdvisedSupport(HdAopConfig config) {
        this.aopConfig = config;
    }


    public Class<?> getTargetClass() {
        return this.targetClass;
    }

    public Object getTarget() {
        return this.target;
    }

    /**
     * 解析配置文件 ： 获取配置的切入方法 ： 就是要添加的方法
     * : 这个获取的是 ： 执行的上下文 ： 执行的方法链 ： 就是
     *      before
     *      add
     *      after
     *      这样的list的方法条
     *
     *      这个就是拿到拦截器链：
     * @param method
     * @param targetClass
     * @return
     */
    public List<Object> getInterceptorsAndDynamicInterceptionAdvice(Method method, Class<?> targetClass) throws NoSuchMethodException {
        //获取到拦截器链 : 先从拦截器链中取出来，
        List<Object> cached = this.methodCache.get(method);
        //第一次的时候， 需要存储
        if(cached == null || cached.size() == 0) {
            Method m = targetClass.getMethod(method.getName(), method.getParameterTypes());
            this.methodCache.put(m, cached);
        }

        return cached;
    }

    public void setTargetClass(Class<?> targetClass) {
        this.targetClass = targetClass;
        //在初始化成代理对象的时候就准备解析
        //暂时本地先注释
        parse();
    }
    //pointCut=public .* com.hongdu.spring.demo.service..*Service..*(.*) 这个最后的括号内的表示方法
    private void parse() {
        String pointCut = aopConfig.getPointCut()
                    .replaceAll("\\.", "\\\\.")//将 . 替换成 \.
                    .replaceAll("\\\\.\\*", ".*")//将 \.* 替换成 .*
                    .replaceAll("\\(", "\\\\(")
                    .replaceAll("\\)", "\\\\)")
                    ;
        System.out.println("转义后的正则： " + pointCut);
        String pointCutForClassRegex= pointCut.substring(0, pointCut.lastIndexOf("\\(") - 4);
        System.out.println("service包下面的路径 " + pointCutForClassRegex);
        //这里只匹配包路径
        this.pointCutClassPatter = Pattern.compile("class " + pointCutForClassRegex.substring(
                        pointCutForClassRegex.lastIndexOf(" ") + 1));

        methodCache = new HashMap<>();
        Pattern pattern = Pattern.compile(pointCut);

        try {

            //拿到切入的类 : 并且拿到所有的方法， 放入到方法中
            Class<?> aspectClass = Class.forName(this.aopConfig.getAspectClass());
            Map<String, Method> aspectMethods = new HashMap<>();
            for (Method m : aspectClass.getMethods()) {
                //方法名 = 方法
                aspectMethods.put(m.getName(),m);
            }

            for (Method m : this.getTargetClass().getMethods()) {
                String methodString = m.toString();
                if(methodString.contains("throw")) {
                    //获得方法 :完整的方法名
                    methodString = methodString.substring(0, methodString.lastIndexOf("throw")).trim();
                    System.out.println("方法字符串： " + methodString);
                }
                //匹配的话就包装成拦截器 就是方法拦截器
                Matcher matcher = pattern.matcher(methodString);
                if(matcher.matches()) {
                    //把每一个方法包装成 MethodInterceptor
                    //1: before
                    //2: after
                    //3: afterThrow
                    //保证有序性
                    List<Object> advices = new LinkedList<>();
                    //before ： 把前置通知 后置通知 都变成了拦截器
                    //多例 模式
                    if(!(null == aopConfig.getAspectBefore() || "".equals(aopConfig.getAspectBefore()))) {
                        //创建一个advice对象
                        //拿到切入的的类
                        String keyMethod = aopConfig.getAspectBefore();
                        advices.add(new HdMethodBeforeAdvice(aspectMethods.get(keyMethod), aspectClass.newInstance()));
                    }
                    //当前方法 : 这个不用add了，因为在 methodCache中做了配置，method做为key，默认顺序在befor后面
                    //advices.add(methodString);
                    //After
                    if(!(null == aopConfig.getAspectAfter() || "".equals(aopConfig.getAspectAfter()))) {
                        //创建一个advice对象
                        String keyMethod = aopConfig.getAspectAfter();
                        advices.add(new HdMethodAfterAdvice(aspectMethods.get(keyMethod), aspectClass.newInstance()));
                    }
                    //AfterThrowing
                    if(!(null == aopConfig.getAspectAfterThrow() || "".equals(aopConfig.getAspectAfterThrow()))) {
                        //创建一个advice对象
                        String keyMethod = aopConfig.getAspectAfterThrow();
                        HdMethodAfterThrowAdvice afterThrowAdvice = new HdMethodAfterThrowAdvice(
                                    aspectMethods.get(keyMethod), aspectClass.newInstance());
                        //抛出异常的名字 : 从配置中解析而来
                        afterThrowAdvice.setThrowName(this.aopConfig.getAspectAfterThrowingName());
                        advices.add(afterThrowAdvice);
                    }
                    //在匹配玩后进行一个容器装载
                    methodCache.put(m, advices);
                }
            }
        } catch (Exception e) {
            System.out.println("包装拦截器失败！");
        }

    }

    public void setTarget(Object instance) {
        this.target = instance;
    }

    /**
     * 根据正则表达式 是否匹配到有需要代理的类 ： 有点话，先生成代理类， 然后再包装 实例化;
     *  其实是根据类名去匹配正则
     * @return
     */
    public boolean pointCutMatch() {
        //class com.hongdu.spring.aop.support.HdAdvisedSupport
        //上面是目标类的正则表达式
        return this.pointCutClassPatter.matcher(this.targetClass.toString()).matches();
    }

    public static void main(String[] args) {
        //public class com.hongdu.spring.aop.support.HdAdvisedSupport
        System.out.println(HdAdvisedSupport.class.toGenericString());
        //        class com.hongdu.spring.aop.support.HdAdvisedSupport
        System.out.println(HdAdvisedSupport.class.toString());
    }

    public void setAopConfig(HdAopConfig config) {
        this.aopConfig = config;
    }
}
