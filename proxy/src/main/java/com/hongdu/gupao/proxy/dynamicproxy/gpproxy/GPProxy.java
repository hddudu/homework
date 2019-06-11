package com.hongdu.gupao.proxy.dynamicproxy.gpproxy;


import com.hongdu.gupao.proxy.Person;
import com.hongdu.gupao.proxy.staticproxy.girlsproxy.Girl;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * getInterfaces()方法和Java的反射机制有关。它能够获得这个对象所实现的所有接口
 * Class<?> string01 = person.getClass().getInterfaces()[0];// 这个接口表示 类 ： 不是表示方法
 *      而每个接口中又都定义了若干个方法
 * public class Person implements eagle,whale{}
 * //获得person对象所实现的第一个接口
 */
public class GPProxy {

    private static final String ln = "\r\n";

    /**
     *
     * @param loader 类加载器
     * @param interfaces 类对象数组
     * @param h 反射自动调用方法处理器
     * @return
     */
    public static Object newProxyInstance(GPClassLoader loader,
                                          Class<?>[] interfaces,
                                          GPInvocationHandler h) {
        try {
        //1. 动态生成源文件    .java文件
//            String src = generateSrc(interfaces);
            String src = generateSrcCommon(interfaces);
//            System.out.println(src);//注释打印控制台生成的代理类 ：final
        //2. java文件输出到磁盘
            String filePath = GPProxy.class.getResource("").getPath();//获取到了陆军
            File f = new File(filePath + "$Proxy0.java");
            FileWriter fw = new FileWriter(f);
            fw.write(src);
            fw.flush();
            fw.close();
        //3. 把生成的java文件编译成.class文件
            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            StandardJavaFileManager manager = compiler.getStandardFileManager(null,null,null);

            Iterable iterable = manager.getJavaFileObjects(f);
            JavaCompiler.CompilationTask compilationTask = compiler.getTask(null,manager,null,null,null,iterable);
            compilationTask.call();
            manager.close();

        //4. 编译生成的.class文件加载JVM中来
            Class proxyClass = loader.findClass("$Proxy0");
            Constructor c = proxyClass.getConstructor(GPInvocationHandler.class);
            f.delete();

        //5. 返回字节码重组以后的新的代理对象
            return c.newInstance(h);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    /**
     * 动态代理原理：
     * ①代理角色（代理类）必须要实现一个接口 InvocationHandler
     * Ⅱ真实类必须实现一个接口
     *
     * jdk Proxy对象生成对象的步骤如下：
     * ①拿到被代理对象的引用，并且获取到它的所有的接口，反射获取
     * (⊙﹏⊙)JDK Proxy重新生成了一个新的类，同时新的类要实现被代理类所有实现的所有的接口
     * 🌂动态生成java 代码，把新加的业务逻辑方法由一定的逻辑代码去调用，（在代码体中体现）
     * ④编译新生成的Java代码成为 .class
     * 🌫再重新加载到JVM中运行。
     * 这个过程就叫做字节码重组。
     *
     * 静态代理原理：
     * ①真实类和代理类必须实现相同的接口，实现具体的业务逻辑在真实类当中，代理类需持有真实类的引用，从而在代理类的方法中可以通过真实类的引用调具体的业务逻辑
     * Ⅱ
     * @return
     */

    /**
     * 类对象数组 ： 接口中的方法： 表示一个类吗？ 返回值 + 返回类型 + 方法名 + 参数
     * @param clsssObjects
     * @return
     */
    private static String generateSrcCommon(Class<?>[] clsssObjects) {
        StringBuffer sb = new StringBuffer();
        sb.append("package com.hongdu.gupao.proxy.dynamicproxy.gpproxy;" + ln);//copy reference
        sb.append("import com.hongdu.gupao.proxy.Person;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public class $Proxy0 implements " + clsssObjects[0].getName() + " { " + ln);

        //初始化 ： 构造函数
        sb.append("GPInvocationHandler h;" + ln);
        sb.append("public $Proxy0(GPInvocationHandler h) {" + ln);
        sb.append("this.h = h;" + ln);
        sb.append("}" + ln);

        //反射机制生成
        for (Method m : clsssObjects[0].getMethods()) {

            Class<?>[] params = m.getParameterTypes();//获取参数类型 ： 类对象
            System.out.println("params: " + params.length);//params: [Ljava.lang.Class;@677327b6

            StringBuffer paramNames = new StringBuffer();
            StringBuffer paramValues = new StringBuffer();
            StringBuffer paramClasses = new StringBuffer();

            for (int i = 0; i < params.length; i++) {
                Class clazz = params[i];//参数类型对象
                System.out.println("clazz: " + clazz);

                String type = clazz.getName();//参数类型名称
                System.out.println("type: " + type);

                String paramName = toLowerFirstCase(clazz.getSimpleName());
                System.out.println("paramName: " + paramName);

                paramNames.append(type + " " + paramName);
                paramValues.append(paramName);
                paramClasses.append(clazz.getName() + ".class");

                if (i > 0 && i < params.length - 1) {
                    paramNames.append(",");
                    paramClasses.append(",");
                    paramValues.append(",");
                }
            }
            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "(" + paramNames.toString() + ") {" + ln);
            sb.append("try{" + ln);
            sb.append("Method m = " + clsssObjects[0].getName() + ".class.getMethod(\"" + m.getName() + "\",new Class[]{" + paramClasses.toString() + "});" + ln);
            sb.append((hasReturnValue(m.getReturnType()) ? "return " : "") + getCaseCode("this.h.invoke(this,m,new Object[]{" + paramValues + "})",m.getReturnType()) + ";" + ln);
            sb.append("}catch(Error _ex) { }");
            sb.append("catch(Throwable e){" + ln);
            sb.append("throw new UndeclaredThrowableException(e);" + ln);
            sb.append("}");
            sb.append(getReturnEmptyCode(m.getReturnType()));
            sb.append("}");
        }
        sb.append("}" + ln);
        return sb.toString();

    }

    private static Map<Class,Class> mappings = new HashMap<Class, Class>();
    static {
        mappings.put(int.class,Integer.class);
    }

    /**
     * 返回空值代码
     * @param returnClass
     * @return
     */
    private static String getReturnEmptyCode(Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            return "return 0;";
        }else if(returnClass == void.class){
            return "";
        }else {
            return "return null;";
        }
    }

    /**
     * 获取case代码
     * @param code
     * @param returnClass
     * @return
     */
    private static String getCaseCode(String code,Class<?> returnClass){
        if(mappings.containsKey(returnClass)){
            System.out.println("getCaseCode : "+"((" + mappings.get(returnClass).getName() +  ")" + code + ")." + returnClass.getSimpleName() + "Value()");
            return "((" + mappings.get(returnClass).getName() +  ")" + code + ")." + returnClass.getSimpleName() + "Value()";
        }
        return code;
    }

    /**
     * 不等于void 就有返回值
     * @param clazz
     * @return
     */
    private static boolean hasReturnValue(Class<?> clazz) {
        return clazz != void.class;
    }

    /**
     * 大写转小写： Abc ==》 abc
     * @param src
     * @return
     */
    private static String toLowerFirstCase(String src) {
        char[] chars = src.toCharArray();
        chars[0] += 32;
        return String.valueOf(chars);
    }

    /**
     * 通过反射获取接口方法数组
     * 根据接口数组
     * @param interfaces
     * @return
     */
    private static java.lang.String generateSrc(Class<?>[] interfaces) {//interfaces[0] 是一个类
        StringBuffer sb = new StringBuffer();
        sb.append("package com.hongdu.gupao.proxy.dynamicproxy.gpproxy;" + ln);//copy reference
        sb.append("import com.hongdu.gupao.proxy.Person;" + ln);
        sb.append("import java.lang.reflect.*;" + ln);
        sb.append("public class $Proxy0 implements " + interfaces[0].getName() + " { " + ln);

        //初始化 ： 构造函数
        sb.append("GPInvocationHandler h;" + ln);
        sb.append("public $Proxy0(GPInvocationHandler h) {" + ln);
            sb.append("this.h = h;" + ln);
        sb.append("}" + ln);

        //反射机制生成
        for (Method m : interfaces[0].getMethods()) {
            sb.append("public " + m.getReturnType().getName() + " " + m.getName() + "() {" + ln);
            sb.append("try {" + ln);
                sb.append("Method m = " + interfaces[0].getName() + ".class.getMethod(\"" + m.getName() + "\", new Class[]{});" + ln);
                sb.append("this.h.invoke(this,m,null);" + ln);
            sb.append("}catch(Throwable e){" + ln);
            sb.append("e.printStackTrace();" + ln);
            sb.append("}" + ln);
        }
        sb.append("}" + ln);
        sb.append("}" + ln);
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(toLowerFirstCase("Abc"));//abc

        Person s = new Girl("张霞",28);
        System.out.println(s.getClass().getInterfaces());//[Ljava.lang.Class;@74a14482
        System.out.println(s.getClass().getInterfaces().length);//1
        System.out.println(s.getClass().getInterfaces()[0].getAnnotations());//[Ljava.lang.annotation.Annotation;@677327b6
        System.out.println(s.getClass().getInterfaces()[0].getSimpleName());//Person
        System.out.println(s.getClass().getInterfaces()[0].getResource(""));//Person
        //file:/G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/classes/com/hongdu/gupao/proxy/
        System.out.println(s.getClass().getInterfaces()[0].getResource("").getPath());// /G:/my_maven_workspace_git/javaSpaceForGupao/homework/proxy/target/classes/com/hongdu/gupao/proxy/
        System.out.println(s.getClass().getInterfaces()[0].getClassLoader());// sun.misc.Launcher$AppClassLoader@18b4aac2
        System.out.println(s.getClass().getInterfaces()[0].getTypeParameters());// [Ljava.lang.reflect.TypeVariable;@14ae5a5
        System.out.println(s.getClass().getInterfaces()[0].getPackage());// package com.hongdu.gupao.proxy
        System.out.println(s.getClass().getInterfaces()[0].getModifiers());// 1537
        System.out.println(s.getClass().getInterfaces()[0].getDeclaredMethods());// [Ljava.lang.reflect.Method;@6d6f6e28
        System.out.println(s.getClass().getInterfaces()[0].getDeclaredMethods().length);// [Ljava.lang.reflect.Method;@6d6f6e28
        for (Method m : s.getClass().getMethods()) {
            System.out.println(m.getReturnType() + " public " + m.getReturnType().getName() + " " + m.getName());
        }
        /**
         * public void sleep
         * public void findLover
         * public void findJob
         * public void examing
         * public void eat
         * public void playGame
         * public void cooking
         * public void wait
         * public void wait
         * public void wait
         * public boolean equals
         * public java.lang.String toString
         * public int hashCode
         * public java.lang.Class getClass
         * public void notify
         * public void notifyAll
         *
         * void public void sleep
         * void public void findLover
         * void public void cooking
         * void public void playGame
         * void public void examing
         * void public void findJob
         * void public void eat
         * void public void wait
         * void public void wait
         * void public void wait
         * boolean public boolean equals
         * class java.lang.String public java.lang.String toString
         * int public int hashCode
         * class java.lang.Class public java.lang.Class getClass
         * void public void notify
         * void public void notifyAll
         */
    }
}
