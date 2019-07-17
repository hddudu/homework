package com.hongdu.spring.mvc;

import com.hongdu.spring.annotation.HdController;
import com.hongdu.spring.annotation.HdRequestMapping;
import com.hongdu.spring.context.PropertiesHdApplicationContext;
import com.hongdu.spring.mvc.servlet.HdModelAndViewV1;
import com.hongdu.spring.mvc.servlet.HdViewResolverV1;
import com.hongdu.spring.mvc.servlet.HdViewV1;
import com.hongdu.spring.mvc.servlet.v1.HdHandlerAdapter;
import com.hongdu.spring.mvc.servlet.v1.HdHandlerMapping;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName HdDispatcherServlet
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/17 9:29
 * @Version 1.0
 */
@Slf4j
public class HdDispatcherServlet extends HttpServlet {

    /**
     * 一个全局的上下文
     */
    private PropertiesHdApplicationContext applicationContext;

    private final String CONFIG_CONTEXT_LOCATION = "contextConfigLocation";

    //把这个去重 用set
//    private List<HdHandlerMapping> handlerMappings = new ArrayList<>();

    private Set<HdHandlerMapping> handlerMappings = new HashSet<>();

    private Map<HdHandlerMapping, HdHandlerAdapter> handlerAdapters = new HashMap<>();

    private List<HdViewResolverV1>  viewResolverV1s = new ArrayList<>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception,Details:\r\n" + Arrays.toString(e.getStackTrace()).replaceAll("\\[|\\]", "").replaceAll(",\\s", "\r\n"));
            e.printStackTrace();
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //1、通过从request中拿到URL，去匹配一个HandlerMapping
        HdHandlerMapping handlerMapping = getHandler(req);

        if(handlerMapping == null) {
            processDispatchResult(req,resp, new HdModelAndViewV1("404"));
            return;
        }

        //准备调用前的参数u
        HdHandlerAdapter ha = getHandlerAdapter(handlerMapping);

        //真正的调用方法 ， 返回 ModelAndView : 存储了要传到页面上值 和 页面模板的名称
        //这个参数有点蒙蔽了
        HdModelAndViewV1 mv = ha.handle(req,resp,handlerMapping);

        //： 渲染视图 : 真正的输出
        processDispatchResult(req, resp, mv);

    }

    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, HdModelAndViewV1 mv) throws Exception {
        //把给我的modelAndView 变成一个 HTML 、 OutputStream  、 json freemark velocity
        //context
        if(null == mv) {
            return;
        }
        if(this.viewResolverV1s.isEmpty()) {
            return;
        }

        //viewResolverV1s : 就一个
        for (HdViewResolverV1 v1 : viewResolverV1s) {
                HdViewV1 viewV1 = v1.resolveViewName(mv.getViewName(), null);
                //渲染调用
            if(viewV1 != null) {
                viewV1.render(mv.getModel(), req, resp);
                return;
            }
        }
    }

    /**
     * 处理 映射器的处理参数
     * @param hanlder
     * @return
     */
    private HdHandlerAdapter getHandlerAdapter(HdHandlerMapping hanlder) {
        if(this.handlerAdapters.isEmpty()) {
            return null;
        }
        HdHandlerAdapter ha = this.handlerAdapters.get(hanlder);
        if(ha.supports(hanlder)) {
            return ha;
        }
        return null;
    }

    private HdHandlerMapping getHandler(HttpServletRequest req) throws Exception {
        if(this.handlerMappings.isEmpty()) {return null;}

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "/");

        for (HdHandlerMapping handler : this.handlerMappings) {
            try {
                Matcher ma = handler.getPattern().matcher(url);
                //如果没有匹配 ： 继续下一个
                if(!ma.matches()) {
                    continue;
                }
                return handler;
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("------------------------------------spring ioc di 开始");
        System.out.println("===================================================================================");
        applicationContext = new PropertiesHdApplicationContext(config.getInitParameter(CONFIG_CONTEXT_LOCATION));
        System.out.println("===================================================================================");
        System.out.println("------------------------------------spring ioc di 结束");

        //初始化
        System.out.println("------------------------------------初始化spring mvc 组件开始");
        System.out.println("===================================================================================");
        onfresh();
        System.out.println("===================================================================================");
        System.out.println("------------------------------------初始化spring mvc 组件结束");
    }

    /**
     * 受保护的方法 : spring的上层框架中提供了默认空实现
     */
    protected void onfresh() {
        initStrategies(applicationContext);
    }

    protected void initStrategies(PropertiesHdApplicationContext context) {
        //多文件上传的组件
        initMultipartResolver(context);
        //初始化本地语言环境
        initLocaleResolver(context);
        //初始化模板处理器
        initThemeResolver(context);

        //handlerMapping
        initHandlerMappings(context);
        //初始化参数适配器
        initHandlerAdapters(context);
        //初始化异常拦截器
        initHandlerExceptionResolvers(context);
        //初始化视图预处理器
        initRequestToViewNameTranslator(context);
        //初始化视图转换器 : 初始化 视图处理器
        initViewResolvers(context);
        //参数缓存器
        initFlashMapManager(context);
    }

    private void initFlashMapManager(PropertiesHdApplicationContext context) {

    }

    /**
     *
     * @param context
     */
    private void initViewResolvers(PropertiesHdApplicationContext context) {
        //拿到m模板的存放目录
        String templateRootPath = context.getConfig().getProperty("templateRoot");
        String filePath = this.getClass().getClassLoader().getResource(templateRootPath).getFile();

        //写错了
//        File file = new File(filePath);
//        for (File tempalte : file.listFiles()) {
//            this.viewResolverV1s.add(new HdViewResolverV1(tempalte.getName()));
//        }
        File templateRootDir = new File(filePath);
        String[] templates = templateRootDir.list();
        for (int i = 0; i < templates.length; i ++) {
            //这里主要是为了兼容多模板，所有模仿Spring用List保存
            //在我写的代码中简化了，其实只有需要一个模板就可以搞定
            //只是为了仿真，所有还是搞了个List
            this.viewResolverV1s.add(new HdViewResolverV1(templateRootPath));
        }

    }

    private void initRequestToViewNameTranslator(PropertiesHdApplicationContext context) {

    }

    private void initHandlerExceptionResolvers(PropertiesHdApplicationContext context) {

    }

    //把一个 request 请求变成一个 handler : 参数都是字符串 ： 自动匹配到 handler中的形参
    //需要拿到handerlmapping才能干活
    //意味着： 有几个 handlerMapping 就有几个 handlerAdapter
    private void initHandlerAdapters(PropertiesHdApplicationContext context) {
        //迭代 handlermappings
        //把一个requet请求变成一个handler，参数都是字符串的，自动配到handler中的形参

        //可想而知，他要拿到HandlerMapping才能干活
        //就意味着，有几个HandlerMapping就有几个HandlerAdapter
        for (HdHandlerMapping handlerMapping : this.handlerMappings) {
            this.handlerAdapters.put(handlerMapping,new HdHandlerAdapter());
        }

    }
    //在初始化映射器的时候 ： 完成了整个的注入过程：
    //其实就是在调用getBean的时候进行了注入属性值
    //
    private void initHandlerMappings(PropertiesHdApplicationContext context) {
        String[] beanNames = context.getBeanDefinitionNames();

        try {
            for (String beanName : beanNames) {
                Object controller = context.getBean(beanName);
                Class<?> clazz = controller.getClass();
                if(!clazz.isAnnotationPresent(HdController.class)) {continue;}

                String baseUrl = "";
                if(clazz.isAnnotationPresent(HdRequestMapping.class)) {
                    HdRequestMapping requestMapping = clazz.getAnnotation(HdRequestMapping.class);
                    baseUrl = requestMapping.value();
                }
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {
                    if(!method.isAnnotationPresent(HdRequestMapping.class)){continue;}
                    HdRequestMapping requestMapping = method.getAnnotation(HdRequestMapping.class);
                    // 正则语法中的 . 表示任意
                    String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*", ".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);
                    handlerMappings.add(new HdHandlerMapping(controller, method, pattern));
                    log.info("Mapped " + regex + "," + method);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initThemeResolver(PropertiesHdApplicationContext context) {

    }

    private void initLocaleResolver(PropertiesHdApplicationContext context) {

    }

    private void initMultipartResolver(PropertiesHdApplicationContext context) {

    }
}
