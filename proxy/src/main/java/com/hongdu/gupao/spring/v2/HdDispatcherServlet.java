package com.hongdu.gupao.spring.v2;

import com.hongdu.gupao.spring.annotation.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

/**
 * @ClassName HdDispatcherServlet
 * @Description hd的 dispatcherservlet
 *      版本1： 问题：
 *      只是用另一个mapping 存放了扫描包的路径名
 *      后面又存放了 url和方法
 *      然后又存放了 接口及接口实现类的实例--》 存在多个类对应了一个实例
 *      -----------改善：
 *      判断key是否是类； 不是类， continue；
 *              虽然已经做了这个处理， 但是仍然出现了
 *              ： java.util.ConcurrentModificationException
 * 	    java.util.HashMap$HashIterator.nextNode(HashMap.java:1442)
 *  	java.util.HashMap$KeyIterator.next(HashMap.java:1466)
 * 	    com.hongdu.gupao.spring.v1.HdDispatcherServlet.init(HdDispatcherServlet.java:85)
 *
 * 	    原因： 出现的情况一直是 di 失败：
 * 	    java.util.ConcurrentModificationException
 * 	java.util.HashMap$HashIterator.nextNode(HashMap.java:1442)
 * 	java.util.HashMap$KeyIterator.next(HashMap.java:1466)
 * 	com.hongdu.gupao.spring.v1.HdDispatcherServlet.init(HdDispatcherServlet.java:106)
 * 	org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:505)
 * 	org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:103)
 * @Author dudu
 * @Date 2019/7/3 21:07
 * @Version 1.0
 */
public class HdDispatcherServlet extends HttpServlet {

    /**
     * 装初始化的类的包路径的容器: 未实例化
     *  com.hongdu.  newInstance ::: 类全路径名 = 实例
     *  /hongdu/get  getList :::     url = 请求方法
     */
    //存储aplication.properties的配置内容
    private Properties contextConfig = new Properties();
    //存储所有扫描到的类
    private List<String> classNames = new ArrayList<String>();
    //IOC容器，保存所有实例化对象
    //注册式单例模式
    private Map<String,Object> ioc = new HashMap<String,Object>();
    //保存Contrller中所有Mapping的对应关系

    private Map<String,Method> handlerMapping = new HashMap<String,Method>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception!" + Arrays.toString(e.getStackTrace()));
        }
    }


//    private void doDispatch(HttpServletRequest req, HttpServletResponse resp)throws Exception {
//
//        String url = req.getRequestURI();
//        String contextPath = req.getContextPath();
//        url = url.replaceAll(contextPath,"").replaceAll("/+","/");
//
//        if(!this.handlerMapping.containsKey(url)){
//            resp.getWriter().write("404 Not Found!!");
//            return;
//        }
//
//        Method method = this.handlerMapping.get(url);
//        //第一个参数：方法所在的实例
//        //第二个参数：调用时所需要的实参
//
//        Map<String,String[]> params = req.getParameterMap();
//
//        //投机取巧的方式
//        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
//        method.invoke(ioc.get(beanName),new Object[]{req,resp,params.get("name")[0]});
//        //System.out.println(method);
//    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp)throws Exception {
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replaceAll(contextPath,"").replaceAll("/+","/");
        if(!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 Not Found!!");
            return;
        }

        Method method = this.handlerMapping.get(url);
        //第一个参数：方法所在的实例
        //第二个参数：调用时所需要的实参
        Map<String,String[]> params = req.getParameterMap();
        //获取方法的形参列表
        Class<?> [] parameterTypes = method.getParameterTypes();
        //保存请求的url参数列表
        Map<String,String[]> parameterMap = req.getParameterMap();
        //保存赋值参数的位置
        Object [] paramValues = new Object[parameterTypes.length];
        //按根据参数位置动态赋值
        for (int i = 0; i < parameterTypes.length; i ++){
            Class parameterType = parameterTypes[i];
            if(parameterType == HttpServletRequest.class){
                paramValues[i] = req;
                continue;
            }else if(parameterType == HttpServletResponse.class){
                paramValues[i] = resp;
                continue;
            }else if(parameterType == String.class){

                //提取方法中加了注解的参数
                Annotation[] [] pa = method.getParameterAnnotations();
                for (int j = 0; j < pa.length ; j ++) {
                    for(Annotation a : pa[i]){
                        if(a instanceof HdRequestParam){
                            String paramName = ((HdRequestParam) a).value();
                            if(!"".equals(paramName.trim())){
                                String value = Arrays.toString(parameterMap.get(paramName))
                                        //去掉 [ 或者 ] 将数组转字符串 后去掉中括号
                                        .replaceAll("\\[|\\]","")
                                        /**
                                         * 一、两种表达方式表达意义的区别：
                                         *
                                         * 1、\s代表正则表达式中的一个空白字符（可能是空格、制表符、其他空白）。
                                         *
                                         * 2、\\s代表字符\和字符s，因为\在正则中有特殊意义，所有需要转义，写成了\\ 。
                                         *
                                         * 二、表达的作用的区别：
                                         *
                                         * 1、\s用于匹配空白字符。
                                         *
                                         * 2、\\s用于匹配字符串中的\和s，两个字符。
                                         */
                                        .replaceAll("\\s",",");
                                paramValues[i] = value;
                            }
                        }
                    }
                }

            }
        }
        //投机取巧的方式
        //通过反射拿到method所在class，拿到class之后还是拿到class的名称
        //再调用toLowerFirstCase获得beanName
        String beanName = toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        method.invoke(ioc.get(beanName),new Object[]{req,resp,params.get("name")[0]});
    }

//    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
//        HdJavaEveUtils.getInstance().printMap(mapping);
//        String url = req.getRequestURI();
//        String contextPath = req.getContextPath();
//        url = url.replace(contextPath, "").replaceAll("/+", "/");
//        if(!this.mapping.containsKey(url)){resp.getWriter().write("404 Not Found!!");return;}
//        Method method = (Method) this.mapping.get(url);
//        Map<String,String[]> params = req.getParameterMap();
//        method.invoke(this.mapping.get(method.getDeclaringClass().getName()),new Object[]{req,resp,params.get("name")[0]});
//    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        //模板模式

        //1、加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //2、扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
        //3、初始化所有相关的类的实例，并且放入到IOC容器之中
        doInstance();
        //4、完成依赖注入
        doAutowired();
        //5、初始化HandlerMapping
        initHandlerMapping();

        System.out.println("GP Spring framework is init.");
    }
    private void initHandlerMapping() {
        if(ioc.isEmpty()){ return; }

        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(HdController.class)){ continue; }

            String baseUrl = "";
            //获取Controller的url配置
            if(clazz.isAnnotationPresent(HdRequestMapping.class)){
                HdRequestMapping requestMapping = clazz.getAnnotation(HdRequestMapping.class);
                baseUrl = requestMapping.value();
            }

            //获取Method的url配置
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {

                //没有加RequestMapping注解的直接忽略
                if(!method.isAnnotationPresent(HdRequestMapping.class)){ continue; }

                //映射URL
                HdRequestMapping requestMapping = method.getAnnotation(HdRequestMapping.class);
                //  /demo/query

                //  (//demo//query)

                String url = ("/" + baseUrl + "/" + requestMapping.value())
                        .replaceAll("/+", "/");
                handlerMapping.put(url,method);
                System.out.println("Mapped " + url + "," + method);
            }
        }


    }

    private void doAutowired() {
        if(ioc.isEmpty()){ return; }
        for (Map.Entry<String, Object> entry : ioc.entrySet()) {
            //拿到实例对象中的所有属性
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if(!field.isAnnotationPresent(HdAutowired.class)){ continue; }
                HdAutowired autowired = field.getAnnotation(HdAutowired.class);
                String beanName = autowired.value().trim();
                if("".equals(beanName)){
                    beanName = field.getType().getName();
                }
                //不管你愿不愿意，强吻
                field.setAccessible(true); //设置私有属性的访问权限
                try {
                    //执行注入动作
                    field.set(entry.getValue(), ioc.get(beanName));
                } catch (Exception e) {
                    e.printStackTrace();
                    continue ;
                }
            }
        }
    }

    //控制反转过程
    //工厂模式来实现的
    private void doInstance() {
        if(classNames.isEmpty()){return;}

        try {
            for (String className : classNames) {
                Class<?> clazz = Class.forName(className);

                if(clazz.isAnnotationPresent(HdController.class)) {
                    Object instance = clazz.newInstance();
                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName, instance);
                }else if(clazz.isAnnotationPresent(HdService.class)){
                    //1、默认的类名首字母小写

                    String beanName = toLowerFirstCase(clazz.getSimpleName());
                    //2、自定义命名
                    HdService service = clazz.getAnnotation(HdService.class);
                    if(!"".equals(service.value())){
                        beanName = service.value();
                    }
                    Object instance = clazz.newInstance();
                    ioc.put(beanName, instance);
                    //3、根据类型注入实现类，投机取巧的方式
                    for (Class<?> i : clazz.getInterfaces()) {
                        if(ioc.containsKey(i.getName())){
                            throw new Exception("The beanName is exists!!");
                        }
                        ioc.put(i.getName(),instance);
                    }
                }else {
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 扫描包类文件到文件中
     * 并且扫描包内类到内存容器中
     * @param scanPackageName
     */
    private void doScanner(String scanPackageName) {
        URL url = this.getClass().getClassLoader().getResource(scanPackageName.replaceAll("\\.","/"));
        File file = new File(url.getFile());
        File[] files = file.listFiles();
        //遍历文件夹
        for (File f : files) {
            if(f.isDirectory()) {
                doScanner(scanPackageName + "." + f.getName());
            } else {
                if(!f.getName().endsWith(".class")) {
                    continue;
                } else {
                    String clazzName = scanPackageName + "." + f.getName().replaceAll(".class", "");
                    //等待初始化 ： 在di的时候进行初始化
                    classNames.add(clazzName);
                }
            }
        }
    }
    private String toLowerFirstCase(String simpleName) {
        char [] chars = simpleName.toCharArray();
        chars[0] += 32;
        return  String.valueOf(chars);
    }
    private void doLoadConfig(String contextConfigLocation) {
        InputStream fis = null;
        try {
            fis = this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
            //1、读取配置文件
            contextConfig.load(fis);
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try {
                if(null != fis){fis.close();}
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}