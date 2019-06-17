package com.hongdu.gupao.commission.mvcdispatcher;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  类似于项目经理的角色：
 *
 *
 *  Servlet的生命周期包含了下面4个阶段：
 *
 * 1.加载和实例化
 *
 * 2.初始化
 *
 * 3.请求处理
 *
 * 4.服务终止
 * Web服务器在与客户端交互时Servlet的工作过程是:
 * 1.     在客户端对web服务器发出请求
 *
 * 2.     web服务器接收到请求后将其发送给Servlet
 *
 * 3.     Servlet容器为此产生一个实例对象并调用ServletAPI中相应的方法来对客户端HTTP请求进行处理,然后将处理的响应结果返回给WEB服务器.
 *
 * 4.     web服务器将从Servlet实例对象中收到的响应结构发送回客户端.
 *
 * https://www.cnblogs.com/lgk8023/p/6427977.html
 *
 *
 */
public class DispatcherServlet extends HttpServlet {
    //Servlet的线程安全问题主要是由于实例变量使用不当而引起的，这里以一个现实的例子来说明。
    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
//    Runnable
//Thread
    /**
     * public class ConcurrentTest extends HttpServlet implements SingleThreadModel  {
     *       ...  ...确保servlet每次只处理一项请求。接口不含方法。
     *
     *       注意：SingleThreadModel不会解决所有的线程安全隐患。 例如，会话属性和静态变量仍然可以被多线程的多请求同时访问，
     *       即便使用了SingleThreadModel servlet。建议开发人员应当采取其他手段来解决这些问题，而不是实现该接口，
     *       比如 避免实例变量的使用或者在访问资源时同步代码块。该接口在Servlet　API 2.4中将不推荐使用。
     *
     *  }
     */
    /**
     * 3、避免使用实例变量( 推荐 )
     *
     * 　　本实例中的线程安全问题是由实例变量造成的，只要在Servlet里面的任何方法里面都不使用实例变量，那么该Servlet就是线程安全的。
     *
     */
 /*************SPRING MVC 线程不安全************************/
    /**
     * Spring mvc 线程不安全的原因
     * 请求时多线程请求的，但是每次请求过来调用的Controller对象都是一个，而不是一个请求过来就创建一个controller对象
     * 原因就在于如果这个controller对象是单例的，那么如果不小心在类中定义了类变量，那么这个类变量是被所有请求共享的，
     * 那有没有办法让controller不以单例而以每次请求都重新创建的形式存在呢？
     *
     * 答案是当然可以，只需要在类上添加注解@Scope("prototype")即可，这样每次请求调用的类都是重新生成的（每次生成会影响效率）
     *
     * 使用ThreadLocal 来保存类变量，将类变量保存在线程的变量域中，让不同的请求隔离开来
     *
     * 有几种解决方法：
     * 1、在Controller中使用ThreadLocal变量
     * 2、在spring配置文件Controller中声明 scope="prototype"，每次都创建新的controller
     *  在Controller中使用ThreadLocal变量
     *
     * 所在在使用spring开发web 时要注意，默认Controller、Dao、Service都是单例的。
     *
     *  ThreadLocal<Long>startTime = newThreadLocal<Long>();  定义一个ThreadLocal 变量
     *
     * startTime.set(System.currentTimeMillis());   写入值
     *
     * startTime.get();  读取值
     *
     * spring对那些个有状态bean使用ThreadLocal维护变量[仅仅是变量,因为线程同步的问题就是成员变量的互斥访问出问题]时，
     * ThreadLocal为每个使用该变量的线程提供独立的变量副本，所以每一个线程都可以独立地改变自己的副本，而不会影响其它线程所对应的副本。
     *
     */

    /**
     * 当客户端第一次请求某个Servlet时，Servlet容器将会根据web.xml配置文件实例化这个Servlet类。当有新的客户端请求该Servlet时，
     * 一般不会再实例化该Servlet类，也就是有多个线程在使用这个实例
     * @throws ServletException
     */
    //servlet首先会初始化 ： servlet的生命周期 ：

    private Map<String,Handler> handlerMap = new HashMap<>();//也可以 ：
    //存储的形式 ： key + url ： 取值就是直接取值key

    private List<Handler> handlerList = new ArrayList<>();

    //注册url
    @Override
    public void init() throws ServletException {
        //线程不安全的初始化过程 : 这个是随着tomcat启动而启动初始化的，所以安全
        try {
            Class<?> memberCtlClass = MemberController.class;//这个后面改成了用spring的动态扫描注入实例
            handlerList.add(new Handler()
                    .setController(memberCtlClass.newInstance())
                    .setMethod(memberCtlClass.getMethod("getMemberById", String.class))
                    .setUrl("/web/" + memberCtlClass.getMethod("getMemberById", String.class).getName() + ".json")
            );
            handlerMap.put("/web/getMemberById.json",new Handler()
                    .setController(memberCtlClass.newInstance())
                    .setMethod(memberCtlClass.getMethod("getMemberById", String.class))
                    .setUrl("/web/" + memberCtlClass.getMethod("getMemberById", String.class).getName() + ".json")
            );//以类名 方法名做唯一区分
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

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
    //1、获取用户请求的url
    //   如果按照J2EE的标准、每个url对对应一个Serlvet，url由浏览器输入
   //2、Servlet拿到url以后，要做权衡（要做判断，要做选择）
    //   根据用户请求的URL，去找到这个url对应的某一个java类的方法
    //3、通过拿到的URL去handlerMapping（我们把它认为是策略常量）
    ////4、将具体的任务分发给Method（通过反射去调用其对应的方法）
    //5、获取到Method执行的结果，通过Response返回出去
//        response.getWriter().write();
    //测试路径： http://localhost:8282/proxy7/web/getMemberById.json?mid=来了getMemberById0000
    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        //req.getRequestURL() : http://localhost:8282/proxy7/
        System.out.println("req.getRequestURL() : " + req.getRequestURL());
        //req.getRequestURI() : /proxy7/
        System.out.println("req.getRequestURI() : " + req.getRequestURI());
        String uri = req.getRequestURI();
        String url = req.getRequestURL().toString();
        String mid = req.getParameter("mid");//uri :

        Handler h = null;
        for (Handler handler : handlerList) {
            if (uri.endsWith(handler.getUrl())) {
                h = handler;
                break;
            }
        }
        Object object = null;
        try {
            /**
             * MethodAccessor.invoke()真正完成反射调用。
             * sun.reflect.MethodAccessor
             * 创建MethodAccessor实例的是ReflectionFactory。
             *
             * sun.reflect.ReflectionFactory：
             */
            //h.getController =>    .setController(memberCtlClass.newInstance()) ==> 类的实例对象
            if(h != null) {
                //方法 类对象实例 参数： 类对象调用方法 传入参数 ： 这么演绎
                object = h.getMethod().invoke(h.getController(), req.getParameter("mid"));
                resp.getWriter().write("200 success!!!");
            } else {
                resp.getWriter().write("404 not fount!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

//    private void doDispatcher(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        String uri = req.getRequestURI();
//        System.out.println("uri : " + uri);
//        //访问http://localhost:8282/proxy7/getMemberById
//        String mid = req.getParameter("mid");//uri : /proxy7/getMemberById
//        if("getMemberById".equals(mid)) {
//            new MemberController().getMemberById(mid);
//        } else if("getOrderById".equals(mid)) {
//            new OrderController().getOrderById(mid);
//        } else if("logout".equals(mid)) {
//            new SystemController().logout();
//        } else {
//            //PrintWriter 打印流
//            resp.getWriter().write("404 Not Found!");
//        }
//    }

    //将控制器 访问url + 方法都封装起来
    class Handler{
        private Object controller;
        private Method method;
        private String url;

        public Object getController() {
            return controller;
        }

        public Handler setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public Method getMethod() {
            return method;
        }

        public Handler setMethod(Method method) {
            this.method = method;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Handler setUrl(String url) {
            this.url = url;
            return this;
        }
    }

    @Override
    public void destroy() {
        System.out.println("serlvet实例损毁！！！");
    }
}
