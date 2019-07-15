package com.hongdu.gupao.spring.spring.webmvc;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @ClassName HdDispatcherServlet
 * @Description Servlet 只是作为一个 MVC 的启动入口
 * @Author dudu
 * @Date 2019/7/15 9:42
 * @Version 1.0
 */
public class HdDispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            resp.getWriter().write("500 Exception!" + Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDispatch(HttpServletRequest req, HttpServletResponse resp) {

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
    }
}
