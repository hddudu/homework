package com.hongdu.gupao.spring.iocbeanprocess.aop.aspectj;

import com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface.ClassFilter;
import com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface.MethodMatcher;
import com.hongdu.gupao.spring.iocbeanprocess.aop.aopbaseinterface.Pointcut;
import org.aspectj.weaver.tools.PointcutExpression;
import org.aspectj.weaver.tools.PointcutParser;
import org.aspectj.weaver.tools.PointcutPrimitive;
import org.aspectj.weaver.tools.ShadowMatch;

import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName AspectJExpressionPointcut
 * @Description Aspectj ： 实现了 ： 切点 ： 切入对象接口 ： 切入方法匹配器
 *          从而将其组合： 形成一个表达式类 ：
 *              根据这个表达式类 ： 可以知道：  对哪个对象的哪个方法进行什么样的拦截 的具体内容
 *
 *       只是需要一个解析器 而已
 *
 * @Author dudu
 * @Date 2019/7/12 15:44
 * @Version 1.0
 */
public class AspectJExpressionPointcut implements Pointcut, ClassFilter, MethodMatcher {

    private PointcutParser pointcutParser;

    private String expression;

    private PointcutExpression pointcutExpression;

    private static final Set<PointcutPrimitive> DEFAULT_SUPPORTED_PRIMITIVES = new HashSet<PointcutPrimitive>();

    static {
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.EXECUTION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.REFERENCE);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.THIS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.TARGET);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ANNOTATION);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_WITHIN);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_ARGS);
        DEFAULT_SUPPORTED_PRIMITIVES.add(PointcutPrimitive.AT_TARGET);
    }

    public AspectJExpressionPointcut() {
        this(DEFAULT_SUPPORTED_PRIMITIVES);
    }

    public AspectJExpressionPointcut(Set<PointcutPrimitive> supportedPrimitives) {
        pointcutParser = PointcutParser
                .getPointcutParserSupportingSpecifiedPrimitivesAndUsingContextClassloaderForResolution(supportedPrimitives);
    }

    protected void checkReadyToMatch() {
        if(pointcutExpression == null) {
            pointcutExpression = buildPointcutExpression();
        }
    }

    public PointcutExpression buildPointcutExpression() {
        return pointcutParser.parsePointcutExpression(expression);
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }

    @Override
    public boolean matches(Class targetClass) {
        checkReadyToMatch();
        return pointcutExpression.couldMatchJoinPointsInType(targetClass);
    }

    @Override
    public boolean matches(Method method, Class targetClass) {
        checkReadyToMatch();
        ShadowMatch shadowMatch = pointcutExpression.matchesMethodExecution(method);
        if(shadowMatch.alwaysMatches()) {
            return true;
        } else if(shadowMatch.neverMatches()) {
            return false;
        }
        // TODO:其他情况不判断了！见org.springframework.aop.aspectj.RuntimeTestWalker
        return false;
    }

    @Override
    public MethodMatcher getMethodMatcher() {
        return this;
    }

    @Override
    public ClassFilter getClassFilter() {
        return this;
    }
}
