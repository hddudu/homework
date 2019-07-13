package com.hongdu.gupao.spring.v3;

import com.hongdu.gupao.spring.annotation.*;
import com.hongdu.gupao.spring.v3.strategies.*;
import org.junit.Test;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName HdDispatcherServlet
 * @Description hd的 dispatcherservlet
 * @Author dudu
 * @Date 2019/7/3 21:07
 * @Version 1.0
 */
public class HdDispatcherServlet extends HttpServlet {

    private static final String LOCATION = "contextConfigLocation";
    //记载配置文件
    private Properties loadProperties = new Properties();
    //扫描容器
    private List<String> classNems = new ArrayList<>();
    //IOC容器
    private Map<String,Object> iocContainer = new HashMap<String,Object>();

    //url 映射 ： 写成了一个内部类
    private List<Handler> handlerMapping = new ArrayList<Handler>();

    public HdDispatcherServlet(){ super(); }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500" + e.getMessage());
            resp.getWriter().write("500 Exception,Details:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {
        try {
            Handler handler = getHandler(req);
            if(handler == null){
                //如果没有匹配上，返回404错误
                resp.getWriter().write("404 Not Found");
                return;
            }
            //获取方法的参数列表 ： 运行时
            Class<?>[] paramTypes = handler.method.getParameterTypes();

            //保存所有需要自动赋值的参数值
            Object[] paramValues = new Object[paramTypes.length];

            Map<String, String[]> params = req.getParameterMap();
            for (Map.Entry<String, String[]> param : params.entrySet()) {
                String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "").replaceAll(",\\s",",");
                //如果找到匹配的对象，则开始填充参数值
                if(!handler.paramIndexMapping.containsKey(param.getKey())) {
                    continue;
                }
                int index = handler.paramIndexMapping.get(param.getKey());
                //给实参赋值.....................
                paramValues[index] = convert(paramTypes[index], value);
            }
            //设置方法中的request和response对象
            int reqIndex = handler.paramIndexMapping.get(HttpServletRequest.class.getName());
            paramValues[reqIndex] = req;
            int respIndex = handler.paramIndexMapping.get(HttpServletResponse.class.getName());
            paramValues[respIndex] = resp;

            //controller 代表实例对象 : 调用该实例中的方法
            //就是 ： controller实例调用该类中的方法 ： 并将参数已经做好了封装
            handler.method.invoke(handler.controller, paramValues);

        } catch (Exception e) {
            System.out.println("未找到的请求url!");
        }
    }

    //url传过来的参数都是String类型的，HTTP是基于字符串协议
    //只需要把String转换为任意类型就好
    private Object convert(Class<?> type, String value) {
        ConvertorContext context = null;
        if(Integer.class == type) {
            context = new ConvertorContext(new IntegerConvertor());
            return context.convert(type, value);
        }
        if(Double.class == type) {
            context = new ConvertorContext(new DoubleConvertor());
            return context.convert(type, value);
        }
        if(Long.class == type) {
            context = new ConvertorContext(new LongConvertor());
            return context.convert(type, value);
        }
        if(Float.class == type) {
            context = new ConvertorContext(new FloatConvertor());
            return context.convert(type, value);
        }
        //如果还有double或者其他类型，继续加if
        //这时候，我们应该想到策略模式了
        //在这里暂时不实现，希望小伙伴自己来实现
        return value;
    }

    private Handler getHandler(HttpServletRequest req) throws Exception{
        if(handlerMapping.isEmpty()) {
            return null;
        }
        //项目名称之后的请求路径
        String url = req.getRequestURI();
        //获取部署项目名称
        String contextPath = req.getContextPath();
        //获取到 handlerMapping中 纯净的 Handler 对象 ：
        url = url.replace(contextPath, "").replaceAll("/+", "/");
        for (Handler handler : handlerMapping) {
            try {
                Matcher matcher = handler.pattern.matcher(url);
                //如果没有匹配上， 继续下一个匹配
                if(!matcher.matches()) {
                    continue;
                }
                return handler;
            } catch (Exception e) {
                throw  e;
            }
        }
        return null;
    }

    @Test
    public void patternTest() {
        String regex = "/query.*";
        String mat = "/queryXXX";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mat);
        System.out.println("是否匹配 ： " + matcher.matches());
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        doLoadConfig(config.getInitParameter(LOCATION));
        doSacnner(loadProperties.getProperty("scanPackage"));
        // : 扫描 包路径文件 错误： 现在只是先初始化所有包路径下的实例
        doInstance();
        //准备依赖注入 : 将扫描到的 实例化的 类 ： 进行依赖注入 （就是往类中的字段属性中设置值）
        doAutowired();
        //构造 HandlerMapping
        initHandlerMapping();
        //6、等待请求，匹配URL，定位方法， 反射调用执行
        //调用doGet或者doPost方法

        //提示信息
        System.out.println("hongdu mvcframework is init");
    }



    private void initHandlerMapping() {
        if (iocContainer.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : iocContainer.entrySet()) {
            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(HdController.class)) {
                continue;
            }
            String url = "";
            //获取controller的url配置
            if(clazz.isAnnotationPresent(HdRequestMapping.class)) {
                url = clazz.getAnnotation(HdRequestMapping.class).value();
            }

            //获取Method的url配置
            Method[] methods = clazz.getMethods();
            for (Method method : methods) {
                //没有加RequestMapping的z注解直接忽略
                if(!method.isAnnotationPresent(HdRequestMapping.class)) {
                    continue;
                }
                //映射URl
                HdRequestMapping requestMapping = method.getAnnotation(HdRequestMapping.class);
                //中间多加一个/ ： 是为了避免配置注解时忘记添加 / 的失误
                String regex = ("/" + url + "/" +requestMapping.value()).replaceAll("/+", "/");
                Pattern pattern = Pattern.compile(regex);
                handlerMapping.add(new Handler(entry.getValue(), method, pattern));
                System.out.println("mapping : " + regex + "," + method);
            }
        }

    }

    private void doAutowired() {
        if(classNems.isEmpty()) {
            return;
        }
        if(iocContainer.isEmpty()) {
            return;
        }
        for (Map.Entry<String, Object> entry : iocContainer.entrySet()) {
            //拿到实例对象中的所有属性 : 私有的 公有的
            Field[] fields = entry.getValue().getClass().getDeclaredFields();
            for (Field field : fields) {
                if(!field.isAnnotationPresent(HdAutowired.class)) {
                    continue;
                }
                HdAutowired autowired = field.getAnnotation(HdAutowired.class);
                String beanName = autowired.value();
                if("".equals(beanName)) {
                    beanName = (field.getType().getName());
                }
                field.setAccessible(true);
                try {
                    field.set(entry.getValue(), iocContainer.get(beanName));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    continue ;
                }
            }
        }
    }

    private void doInstance() {
        if(classNems.isEmpty()) {
            return;
        }
        try {
            for (String clazzName : classNems) {
                Class<?> clazz = Class.forName(clazzName);
                if(clazz.isAnnotationPresent(HdController.class)) {
                    //默认将首字母小写作为beanName ： 其实Controller这里也要添加是否自定义名字的
                    HdController controller = clazz.getAnnotation(HdController.class);
                    String beanName = controller.value();
                    //如果用户设置了名字，就用用户自己设置
                    if(!"".equals(beanName)) {
                        //demoAction : 实例对象
                        iocContainer.put(beanName, clazz.newInstance());
                        continue;
                    }
                    //如果没有设置
                    beanName = lowerFirst(clazz.getSimpleName());
                    iocContainer.put(beanName, clazz.newInstance());
                } else if(clazz.isAnnotationPresent(HdService.class)) {
                    //看用户是否自定义了名字
                    HdService service = clazz.getAnnotation(HdService.class);
                    String beanName= service.value();
                    Object instance = clazz.newInstance();
                    if(!"".equals(beanName)) {
                        iocContainer.put(beanName, instance);
                    } else {
                        beanName = lowerFirst(clazz.getSimpleName());
                        iocContainer.put(beanName, instance);
                    }
                    //将service实现的接口都注入进来
                    Class<?>[] interfaces = clazz.getInterfaces();
                    for (Class<?> i : interfaces) {
                        iocContainer.put(i.getName(), instance);
                    }
                } else {
                    continue;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testName() {
        HdDispatcherServlet hd3 = new HdDispatcherServlet();
        /**
         * getSimpleName : HdDispatcherServlet
         * getName : com.hongdu.gupao.spring.v3.HdDispatcherServlet
         * getTypeName : com.hongdu.gupao.spring.v3.HdDispatcherServlet
         * getCanonicalName : com.hongdu.gupao.spring.v3.HdDispatcherServlet
         */
        System.out.println(hd3.getClass().getSimpleName());
        System.out.println(hd3.getClass().getName());
        System.out.println(hd3.getClass().getTypeName());
        System.out.println(hd3.getClass().getCanonicalName());
    }

    private String lowerFirst(String simpleName) {
        char[] chars = simpleName.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }


    /**
     * Handler记录Controller中的RequestMapping和Method的对应关系
     * @author Tom
     * 内部类
     */
    //为什么不用Map
        //你用map， key只能是url
        //而这个handler本身已经具备url和method的对应关系， 已经具备了map的功能
        //g根据设计原则 ： 单一职责，
    private class Handler {
        //控制器对象 :保存方法的实例
        protected Object controller;
        //方法 ： 保存映射的方法
        protected Method method;
        //正则表达式匹配
        protected Pattern pattern;
        //参数顺序
        protected Map<String, Integer> paramIndexMapping;

        /**
         *  构造一个Handler基本的参数
         * @param controller
         * @param method
         * @param pattern
         */
        public Handler(Object controller, Method method, Pattern pattern) {
            this.controller = controller;
            this.method = method;
            this.pattern = pattern;
            paramIndexMapping = new HashMap<>();
            putParamIndexMapping(method);
        }

        private void putParamIndexMapping(Method method) {
            //提取方法中加入了注解的参数
            Annotation[][] pa = method.getParameterAnnotations();
            for (int i = 0; i < pa.length; i ++) {
                for (Annotation a : pa[i]) {
                    if(a instanceof HdRequestParam) {
                        String paramName = ((HdRequestParam) a).value();
                        if(!"".equals(paramName.trim())) {
                            //name 0
                            //age 1
                            paramIndexMapping.put(paramName, i);
                        }
                    }
                }
            }
            //提取方法中的request和response参数
            Class<?>[] paramsTypes = method.getParameterTypes();
            for (int i = 0; i < paramsTypes.length; i ++) {
                Class<?> type = paramsTypes[i];
                if(type == HttpServletRequest.class
                    || type == HttpServletResponse.class) {
                    //javax.servlet.http.HttpServletRequest 0
                    //javax.servlet.http.HttpServletResponse 1
                    paramIndexMapping.put(type.getName(), i);
                }
            }

        }
    }
    //加载配置文件
    private void doLoadConfig(String initParameter) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(initParameter);
        try {
            loadProperties.load(is);
        } catch (IOException e) {
            System.out.println("加载属性配置文件流失败！");
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    System.out.println("关闭is流失败！");
                }
            }
        }
    }
    //扫描包
    private void doSacnner(String scanPackageName) {
        URL packageFiles = this.getClass().getClassLoader().getResource(scanPackageName.replaceAll("\\.", "/"));
        File files = new File(packageFiles.getFile());
        //遍历文件夹
        for (File file : files.listFiles()) {
            //是否是文件夹
            if(file.isDirectory()) {
                doSacnner(scanPackageName + "." + file.getName());
            } else {
                if(!file.getName().endsWith(".class")) {
                    continue;
                } else {
                    //file.getName 只是文件名而已 : 需要的是包路径
                    String clazzName = scanPackageName + "." + file.getName().replaceAll(".class", "");
                    classNems.add(clazzName);
                }
            }
        }
    }
}