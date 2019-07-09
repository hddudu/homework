package com.hongdu.gupao.spring.v1;

import com.hongdu.gupao.spring.annotation.HdAutowired;
import com.hongdu.gupao.spring.annotation.HdController;
import com.hongdu.gupao.spring.annotation.HdRequestMapping;
import com.hongdu.gupao.spring.annotation.HdService;
import com.hongdu.hdutil.HdJavaEveUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
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
//    private Map<String, Object> mapping = new HashMap<>();
    private  Map<String, Object> mapping = Collections.synchronizedMap(new HashMap<>());

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

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws IOException, InvocationTargetException, IllegalAccessException {
        HdJavaEveUtils.getInstance().printMap(mapping);
        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        if(!this.mapping.containsKey(url)){resp.getWriter().write("404 Not Found!!");return;}
        Method method = (Method) this.mapping.get(url);
        Map<String,String[]> params = req.getParameterMap();
        method.invoke(this.mapping.get(method.getDeclaringClass().getName()),new Object[]{req,resp,params.get("name")[0]});
    }

//    @Override
//    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        super.service(req, resp);
//    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream inputStream = null;
        //1: 加载配置文件 从web.xml中加载初始化属性值 ： initParam
        Properties configContext = new Properties();
        String conf = config.getInitParameter("contextConfigLocation");
        inputStream = this.getClass().getClassLoader().getResourceAsStream(conf);
        try {
            //加载配置文件
            configContext.load(inputStream);
            //2: 解析配置文件 ： 读取包名
            String scanPackageName = configContext.getProperty("scanPackageName");
            doScanner(scanPackageName);
            //额外添加操作： 将mapping输出到文件中
//            synchronized (mapping) {
//                HdJavaEveUtils.getInstance().listMapWrite2File(mapping);
//            }
            //3： 初始化bean ： 根据包名初始化类 及 类实例
            //遍历map的所有key集合： map的所有value集合： mapping.values()
            //  3.1: 初始化所有相关的实例， 并且放入到IOC容器中
            //  3.2： 完成依赖注入
            //4： 初始化化 handlerMapping
            HdJavaEveUtils.getInstance().printMap(mapping);
            for (String className : mapping.keySet()) {
                //没有包含. 不是包路径 :
                if(!className.contains(".")) {
                    continue;
                }
                //根据包名加载类
                Class<?> clazz = Class.forName(className);
                //此时的 A.isAnnotationPresent(B.class)；意思就是：注释B是否在此A上。如果在则返回true；不在则返回false
                /**
                 *  @B("/hello")
                 *  public class A{
                 *    .........}
                 */
                //外层注解
                if(clazz.isAnnotationPresent(HdController.class)) {
                    //如果在， 就是注解了HdController: 那么实例化
                    mapping.put(className, clazz.newInstance());
                    //拼接访问url : 暂时要给HdRequestMapping
                    String baseUrl = "";
                    if(clazz.isAnnotationPresent(HdRequestMapping.class)) {
                        //获取注解 ： 类的类对象获取注解属性
                        HdRequestMapping requestMapping = clazz.getAnnotation(HdRequestMapping.class);
                        baseUrl = requestMapping.value();
                    }
                    //获取public方法 ： 以及获取public方法上面的HdRequestMapping注解
                    Method[] methods = clazz.getMethods();
                    for (Method method : methods) {
                        if(!method.isAnnotationPresent(HdRequestMapping.class)) {
                            continue;
                        }
                        HdRequestMapping requestMapping = method.getAnnotation(HdRequestMapping.class);
                        //去除多个//的影响
                        String url = ("/" +  baseUrl + "/" + requestMapping.value()).replaceAll("/+","/");
                        //url 跟方法对应
                        mapping.put(url, method);
                        System.out.println(" Mapped " + url + " = " + method);
                    }
                } else if(clazz.isAnnotationPresent(HdService.class)) {
                    HdService service = clazz.getAnnotation(HdService.class);
                    //获取service自定义值
                    String beanName = service.value();
                    //如果没有添加自定义值，默认使用类名首字母小写的beanName
                    if("".equals(beanName)) {
                        beanName = clazz.getName();
                    }
                    //实例化serviceImpl
                    Object instance = clazz.newInstance();
                    //字母小写：
                    mapping.put(beanName, instance);
                    for (Class<?> i : clazz.getInterfaces()) {
                        //将impl实现的所有类的实例都映射到： 该类实现的所有接口类上面
                        //TODO ： 测试下类实现的接口的名字是什么
                        mapping.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            }
            System.out.println("---------------------------------------实例化结束后的mapping-------------------------------");
            //额外添加操作： 将mapping输出到文件中
//            HdJavaEveUtils.getInstance().listMapWrite2File(mapping);

            for (Object o : mapping.values()) {
                if(null == o) {
                    continue;
                }
                Class clazz = o.getClass();
                if(clazz.isAnnotationPresent(HdController.class)) {
                    Field[] fields = clazz.getDeclaredFields();
                    for (Field field : fields) {
                        if(!field.isAnnotationPresent(HdAutowired.class)) {
                            continue;
                        }
                        HdAutowired autowired = field.getAnnotation(HdAutowired.class);
                        String beanName = autowired.value();
                        if("".equals(beanName)) {
                            beanName = field.getType().getName();
                            field.setAccessible(true);
                        }
                        try {
                            field.set(mapping.get(clazz.getName()), mapping.get(beanName));
                        } catch (Exception e) {
                            System.out.println("Field 设置值失败： 获取类名成比如User， 设置值 ：类名 + 实例 : 完成的是 ： 类名中的实例对象设置某个字段的值 ");
                        }
                    }
                }
            }
            System.out.println("---------------------------------------完成自动注入后的mapping-------------------------------");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Properties 从 inputStream加载配置文件出现异常！");
        } catch (IllegalAccessException e) {
            System.out.println("没有访问权限，初始化实例化失败！");
        } catch (InstantiationException e) {
            System.out.println("初始化失败！");
        } finally {
            if(inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {

                }
            }
        }
        System.out.print("HD MVC Framework is init");
        //运行阶段
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
                    mapping.put(clazzName, null);
                }
            }
        }
    }
}
