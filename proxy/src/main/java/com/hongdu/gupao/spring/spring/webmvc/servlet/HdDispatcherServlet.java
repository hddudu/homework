package com.hongdu.gupao.spring.spring.webmvc.servlet;

import com.hongdu.gupao.spring.spring.annotation.HdController;
import com.hongdu.gupao.spring.spring.annotation.HdRequestMapping;
import com.hongdu.gupao.spring.spring.context.HdApplicationContext;
import com.hongdu.hdutil.HdJavaEveUtils;

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
 * @Description Servlet 只是作为一个 MVC 的启动入口
 * @Author dudu
 * @Date 2019/7/15 9:42
 * @Version 1.0
 */
public class HdDispatcherServlet extends HttpServlet {

    /**
     * 添加锁 ： 保证map安全
     */
    private final Object objectMonitor = new Object();

    /**
     * 配置文件路径读取
     */
    private final String CONTEXT_CONFIG_LOCATION = "contextConfigLocation";

    /**
     * 核心容器 ： IOC容器 ： 加载资源 ： 解析资源成为bean， 还能实例化 bean 并注入到包装的容器当中
     */
    private HdApplicationContext applicationContext;

    /**
     * 映射器 ：有多少个controller 就有多少个映射器
     */
    private List<HdHandlerMapping> handlerMappings = new ArrayList<>();

    /**
     * 映射器 和 处理映射器的 适配处理器 一一 对应
     */
    private Map<HdHandlerMapping, HdHandlerAdapter> handlerAdapters = new HashMap<>();

    /**
     * 视图解析器
     */
    private List<HdViewResolver> viewResolvers = new ArrayList<>();


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
        HdHandlerMapping handler = getHandler(req);

        if(null == handler) {
            processDispatchResult(req, resp, new HdModelAndView("404"));
            return;
        }
        //2: 准备调用前的参数 : 传入映射器 ： 返回对应的参数处理对应器
        HdHandlerAdapter ha = getHandlerAdapter(handler);

        //3、真正的调用方法,返回ModelAndView存储了要穿页面上值，和页面模板的名称
        HdModelAndView mv = ha.handle(req,resp,handler);

        //这一步才是真正的输出
        processDispatchResult(req, resp, mv);

    }

    /**
     * 参数 和 映射器 的映射处理器
     * @param handler
     * @return
     */
    private HdHandlerAdapter getHandlerAdapter(HdHandlerMapping handler) {
        if(this.handlerAdapters.isEmpty()) {
            return null;
        }
        HdHandlerAdapter ha = this.handlerAdapters.get(handler);
        if(ha.supports(handler)) {
            return ha;
        }
        return null;
    }

    /**
     * 处理映射器的结果
     * @param req
     * @param resp
     * @param modelAndView
     * @throws Exception
     */
    private void processDispatchResult(HttpServletRequest req, HttpServletResponse resp, HdModelAndView modelAndView) throws Exception {
        //把给我的ModleAndView变成一个HTML、OuputStream、json、freemark、veolcity
        //ContextType
        if (null == modelAndView) {
            return;
        }
        //如果ModelAndView不为null，怎么办？
        if(this.viewResolvers.isEmpty()) {
            return;
        }
        for (HdViewResolver viewResolver : this.viewResolvers) {
            HdView view = viewResolver.resolveViewName(modelAndView.getViewName(), null);
            view.render(modelAndView.getModel(), req, resp);
            return;
        }
    }

    private HdHandlerMapping getHandler(HttpServletRequest req) throws Exception {
        if(this.handlerMappings.isEmpty()) {return null;}

        String url = req.getRequestURI();
        String contextPath = req.getContextPath();
        url = url.replace(contextPath, "").replaceAll("/+", "");

        for (HdHandlerMapping handler : this.handlerMappings) {
            try {
                Matcher ma = handler.getPattern().matcher(url);
                //如果没有匹配 ： 继续下一个
                if(!ma.matches()) {
                    continue;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return null;
    }


    /**
     * *****************在 抽象类 HttpServletBean 中
     * 	// Let subclasses do whatever initialization they like. 在servlet这个层中 ： 这个方法留给了子类实现
     * 	public abstract class HttpServletBean extends HttpServlet implements EnvironmentCapable, EnvironmentAware {
     * 	    //这个方法是在 init() : 初始化配置信息的方法中
     * 	    initServletBean();
     *
     * 	    //在初始化这个 bean的方法里面 ：它又是一个空实现 ： 留给了子类 ：
     * 	    protected void initServletBean() throws ServletException {
     * 	    }
     * 	}
     *
     * 	**************** SpringMVC 提供了实现 ： FrameworkServlet
     *      1: 提供了监听器： ContextRefreshListener
     *      2：提供了请求拦截器 ： RequestBindingInterceptor
     *      protected final void initServletBean() throws ServletException {
     *
     *         WebApplicationContext this.webApplicationContext = initWebApplicationContext();
     *      }
     *
     *
     *
     *
     * @param config
     * @throws ServletException
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("待整合 。。。。。。。。。。。。。。。 暂时 ： 还未整合IOC容器!在这个里面调用了 IOC容器的初始化方法 fresh");
        //1、初始化ApplicationContext ： 这个初始化就完成了Spring的IOC 和 DI
        applicationContext = new HdApplicationContext(config.getInitParameter(CONTEXT_CONFIG_LOCATION));
        //2: 初始化Spring MVC 九大组件
        initStrategies(applicationContext);
    }

    //初始化策略
    protected void initStrategies(HdApplicationContext applicationContext) {
        //多文件上传组件
        initMultipartResolver(applicationContext);
        //c初始化本地语言环境
        initLocaleResolver(applicationContext);
        //初始化模板处理器
        initThemeResolver(applicationContext);

        //初始化 控制器和方法的映射 ： handlerMapping 必须实现
        initHandlerMapping(applicationContext);
        //初始化参数适配器 ： 必须实现
        initHandlerAdapter(applicationContext);


        //初始化异常拦截器
        initHandlerExceptionResolver(applicationContext);
        //初始化视图预处理器
        initRequestToViewNameTranslator(applicationContext);

        //初始化视图z转换器， 必须实现
        initViewResolver(applicationContext);

        //参数缓存器
        initFlashMapManager(applicationContext);

    }
    private void initFlashMapManager(HdApplicationContext applicationContext) {
    }

    /**
     *   初始化视图解析器
     * @param applicationContext
     */
    private void initViewResolver(HdApplicationContext applicationContext) {
        //拿到模板的存放目录
        String templateRoot = applicationContext.getConfig().getProperty("templateRoot");
        String templateRootPath = this.getClass().getClassLoader().getResource(templateRoot).getFile();
        File templateRootDir = new File(templateRootPath);
        //list() :获得文件名字
        //listFiles(); 获得多个文件
        String[] templates = templateRootDir.list();
        for (String s : templates) {
            //这里主要是为了兼容多模板，所有模仿Spring用List保存
            //在我写的代码中简化了，其实只有需要一个模板就可以搞定
            //只是为了仿真，所有还是搞了个List
            this.viewResolvers.add(new HdViewResolver(templateRoot));
        }

    }
    private void initRequestToViewNameTranslator(HdApplicationContext applicationContext) {
    }
    private void initHandlerExceptionResolver(HdApplicationContext applicationContext) {
    }

    /**
     * 初始化 ： 处理器 适配器 ：
     *      主要用来处理映射器的
     * @param applicationContext
     */
    private void initHandlerAdapter(HdApplicationContext applicationContext) {
        //把一个requet请求变成一个handler，参数都是字符串的，自动配到handler中的形参

        //可想而知，他要拿到HandlerMapping才能干活
        //就意味着，有几个HandlerMapping就有几个HandlerAdapter
        for (HdHandlerMapping handlerMapping : this.handlerMappings) {
            this.handlerAdapters.put(handlerMapping, new HdHandlerAdapter());
        }

    }

    /**
     * 初始化 url 和 控制器的映射对应
     *      所有的 beanNames ： 就是可以初始化的 处理映射 ：
     * @param applicationContext
     */
    private void initHandlerMapping(HdApplicationContext applicationContext) {
        //获取到所有的 benaNames ： keyset
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        try {
            for (String beanName : beanNames) {
                Object controller = applicationContext.getBean(beanName);

                Class<?> clazz = controller.getClass();

                if(!clazz.isAnnotationPresent(HdController.class)) {continue;}
                String baseUrl = "";
                //获取 Controller的 url 配置
                if(clazz.isAnnotationPresent(HdRequestMapping.class)) {
                    HdRequestMapping requestMapping = clazz.getAnnotation(HdRequestMapping.class);
                    baseUrl = requestMapping.value();
                }
                //获取方法上面的 位置
                //获取Method的url配置
                Method[] methods = clazz.getMethods();
                for (Method method : methods) {

                    //没有加RequestMapping注解的直接忽略
                    if(!method.isAnnotationPresent(HdRequestMapping.class)){ continue; }

                    //映射URL
                    HdRequestMapping requestMapping = method.getAnnotation(HdRequestMapping.class);
                    //  /demo/query

                    //  (//demo//query)

                    String regex = ("/" + baseUrl + "/" + requestMapping.value().replaceAll("\\*",".*")).replaceAll("/+", "/");
                    Pattern pattern = Pattern.compile(regex);

                    this.handlerMappings.add(new HdHandlerMapping(controller, method, pattern));
//                    log.info("Mapped " + regex + "," + method);
                }
            }
            /**
             * 加锁打印代码块
             */
            synchronized (objectMonitor) {
                System.out.println("===================================开始打印 映射器 ： handler Mapping==============");
                HdJavaEveUtils.getInstance().printList(this.handlerMappings);
                System.out.println("===================================结束打印 映射器 ： handler Mapping==============");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void initThemeResolver(HdApplicationContext applicationContext) {
    }
    private void initLocaleResolver(HdApplicationContext applicationContext) {
    }
    private void initMultipartResolver(HdApplicationContext applicationContext) {
    }
}
