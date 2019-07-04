package com.hongdu.gupao.spring.v1;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

/**
 * @ClassName HdDispatcherServlet
 * @Description hd的 dispatcherservlet
 * @Author dudu
 * @Date 2019/7/3 21:07
 * @Version 1.0
 */
public class HdDispatcherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
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

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void init(ServletConfig config) throws ServletException {

        //1: 加载配置文件 从web.xml中加载初始化属性值 ： initParam
        //2: 解析配置文件 ： 读取包名
        //3： 初始化bean ： 根据包名初始化类 及 类实例
        //  3.1: 初始化所有相关的实例， 并且放入到IOC容器中
        //  3.2： 完成依赖注入
        //4： 初始化化 handlerMapping
        //运行阶段
    }
}
