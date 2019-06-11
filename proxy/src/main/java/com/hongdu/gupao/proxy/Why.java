package com.hongdu.gupao.proxy;

/**
 * 为什么的思考：
 * ①为什么要有代理
 * Ⅱ实现方式有哪些
 * 🌂用到过的业务场景
 * ④画图详解
 * 🌫实现原理
 *  扩展： 代理涉及 相关知识点
 *      动态拼接字符串,动态生成代理类的源代码,将字符串输出到文件中成为 $Proxy0.java 类文件;
 *      ①类加载及类加载器
 *      Ⅱ磁盘文件 《--》 读到内存中（文件输入流： IO流） 《--》字节数组输出流：（缓冲输出）： 输出成为字节流对象 ： 字节码文件
 *      🌂 java的编译器对象 ： 编译字节码文件 JavaCompiler.CompilationTask
 *      ④编译生成的 .class文件 加载到JVM中来
 *      🌫返回字节码重组以后的新的代理对象, 有了代理对象后,自动调用invoke方法就可以了;
 */
public class Why {

    public static void main(String[] args) {

    }
}
