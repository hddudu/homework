package com.hongdu.gupao.spring.iocbeanprocess;

/**
 * @ClassName AopIntroduction
 * @Description aop spring的实现 以及使用 ：
 *
 * AOP 的植入与实现细节
 * 在 Bean 初始化过程中完成 AOP 的植入
 * 解决 AOP 的植入问题，首先要解决 在 IoC 容器的何处植入 AOP 的问题，其次要解决 为哪些对象提供 AOP 的植入 的问题。
 * tiny-spring 中 AspectJAwareAdvisorAutoProxyCreator 类（以下简称 AutoProxyCreator）是实现 AOP 植入的关键类，它实现了两个接口：
 *
 * BeanPostProcessor ：在 postProcessorAfterInitialization 方法中，使用动态代理的方式，返回一个对象的代理对象。解决了 在 IoC 容器的何处植入 AOP 的问题。
 * BeanFactoryAware ：这个接口提供了对 BeanFactory 的感知，这样，尽管它是容器中的一个 Bean，却可以获取容器的引用，进而获取容器中所有的切点对象，决定对哪些对象的哪些方法进行代理。解决了 为哪些对象提供 AOP 的植入 的问题。
 *
 *   PointcutAdvisor
 *   Pointcut ： ClassFilter ： 判断对某个对象进行拦截；
 *              MethodMatcher ： 用于判断是否对某个方法进行拦截
 *   Advisor : 通知对象 advice ： 用于实现具体的方法拦截；
 *
 *  AutoProxyCreator（实现了 BeanPostProcessor 接口）在实例化所有的 Bean 前，最先被实例化。
 * 其他普通 Bean 被实例化、初始化，在初始化的过程中，AutoProxyCreator 加载 BeanFactory 中所有的 PointcutAdvisor（这也保证了 PointcutAdvisor 的实例化顺序优于普通 Bean。），然后依次使用 PointcutAdvisor 内置的 ClassFilter，判断当前对象是不是要拦截的类。
 * 如果是，则生成一个 TargetSource（要拦截的对象和其类型），并取出 AutoProxyCreator 的 MethodMatcher（对哪些方法进行拦截）、Advice（拦截的具体操作），再，交给 AopProxy 去生成代理对象。
 * AopProxy 生成一个 InvocationHandler，在它的 invoke 函数中，首先使用 MethodMatcher 判断是不是要拦截的方法，如果是则交给 Advice 来执行（Advice 由用户来编写，其中也要手动/自动调用原始对象的方法），如果不是，则直接交给 TargetSource 的原始对象来执行。
 *
 *   1:  代理模式 支持 对象代理
 *   2： aop抽象接口 顶层接口
 *   3： 抽象实现类 ： AbstractAopProxy ： 持有AdvisedSupport ： 依赖 (代理相关的元数据)
 *
 * @Author dudu
 * @Date 2019/7/12 11:43
 * @Version 1.0
 */
public class AopIntroduction {

}
