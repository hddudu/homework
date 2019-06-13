package com.hongdu.gupao.commission.mvcdispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  类似于项目经理的角色
 */
public class DispatcherServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //接收前端url请求
        //进行uri匹配转发
        //响应
        try {
            doDispatcher(req,resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String uri = req.getRequestURI();
        System.out.println("uri : " + uri);
        //访问http://localhost:8282/proxy7/getMemberById
        String mid = req.getParameter("mid");//uri : /proxy7/getMemberById
        if("getMemberById".equals(mid)) {
            new MemberController().getMemberById(mid);
        } else if("getOrderById".equals(mid)) {
            new OrderController().getOrderById(mid);
        } else if("logout".equals(mid)) {
            new SystemController().logout();
        } else {
            //PrintWriter 打印流
            resp.getWriter().write("404 Not Found!");
        }
    }
}
