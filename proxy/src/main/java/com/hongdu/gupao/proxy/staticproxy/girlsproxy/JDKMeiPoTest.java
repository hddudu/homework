package com.hongdu.gupao.proxy.staticproxy.girlsproxy;

import com.hongdu.gupao.proxy.Person;

/**
 * 参考链接：
 * https://www.processon.com/diagraming/5e527166e4b0c037b5fad533
 */

public class JDKMeiPoTest {
    public static void main(String[] args) throws Exception{
        Girl girl = new Girl("林青霞",51);//目标对象
        //生成目标对象的代理对象
//        Object proxyObj = new JDKMeiPo().getInstance(girl);//代理对象 : 直接调用目标对象方法
        Person obj = (Person) new JDKMeiPo().getInstance(girl);//代理对象 : 直接调用目标对象方法
//        JDKMeiPo meipoObj = (JDKMeiPo)proxyObj;
//        Person obj = (Person)proxyObj;//强转 :
        System.out.println(obj.getClass());//obj是null, 但是它的class是$Proxy0
//        obj.findLover();
//        obj.findJob();

        Person obj2 = (Person) new JDKJobKiller().getInstance(new Girl("林青霞",51));
//        obj2.findJob();
        //动态生成字节码的原理: 在内存中会生成新的类;
        //$Proxy0 : 这个类:

//			byte[]  bytes =

//        byte [] bytes = ProxyGenerator.generateProxyClass("$Proxy1",new Class[]{Person.class});
        //生成到当前目录下 : 文件输出流 直接将字节流写成文件到目录下 ; 写 然后 刷新 关闭流
//        FileOutputStream os = new FileOutputStream("G:\\my_maven_workspace_git\\javaSpaceForGupao\\homework\\proxy\\src\\main\\java\\com\\hongdu\\gupao\\proxy\\staticproxy\\girlsproxy\\$Proxy0.class");
////        os.write(bytes);
////        os.close();
    }
}

/**
 *
 * 导入包:
 import com.hongdu.gupao.proxy.Person;
 import java.lang.reflect.InvocationHandler;
 import java.lang.reflect.Method;
 import java.lang.reflect.Proxy;
 import java.lang.reflect.UndeclaredThrowableException;
//继承了 反射的 Proxy 类 然后实现了接口 Person ==>生成了final类 $Proxy0
 public final class $Proxy0 extends Proxy implements Person {
 //扫描了方法 Method :
 //Proxy 自然有 equals hashcode 等方法 toString
 //实现Person 实现 findLover 方法 m3
 private static Method m1;
 private static Method m3;
 private static Method m2;
 private static Method m0;

 //构造器 : InvocationHandler 反射处理器
 public $Proxy0(InvocationHandler var1) throws  {
 super(var1);
 }

 public final boolean equals(Object var1) throws  {
 try {
 return (Boolean)super.h.invoke(this, m1, new Object[]{var1});
 } catch (RuntimeException | Error var3) {
 throw var3;
 } catch (Throwable var4) {
 throw new UndeclaredThrowableException(var4);
 }
 }

 public final void findLover() throws  {
 try {
 super.h.invoke(this, m3, (Object[])null);
 } catch (RuntimeException | Error var2) {
 throw var2;
 } catch (Throwable var3) {
 throw new UndeclaredThrowableException(var3);
 }
 }

 public final String toString() throws  {
 try {
 return (String)super.h.invoke(this, m2, (Object[])null);
 } catch (RuntimeException | Error var2) {
 throw var2;
 } catch (Throwable var3) {
 throw new UndeclaredThrowableException(var3);
 }
 }

 public final int hashCode() throws  {
 try {
 return (Integer)super.h.invoke(this, m0, (Object[])null);
 } catch (RuntimeException | Error var2) {
 throw var2;
 } catch (Throwable var3) {
 throw new UndeclaredThrowableException(var3);
 }
 }
//静态块 加载了方法 : 类对象属性 方法
 static {
 try {
 m1 = Class.forName("java.lang.Object").getMethod("equals", Class.forName("java.lang.Object"));
 m3 = Class.forName("com.hongdu.gupao.proxy.Person").getMethod("findLover");
 m2 = Class.forName("java.lang.Object").getMethod("toString");
 m0 = Class.forName("java.lang.Object").getMethod("hashCode");
 } catch (NoSuchMethodException var2) {
 throw new NoSuchMethodError(var2.getMessage());
 } catch (ClassNotFoundException var3) {
 throw new NoClassDefFoundError(var3.getMessage());
 }
 }
 }
 */
