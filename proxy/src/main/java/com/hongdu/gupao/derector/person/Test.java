package com.hongdu.gupao.derector.person;

/**
 * @ClassName Test
 * @Description : 测试
 *      装饰者A  多吃了一碗饭, 增强了本来没吃饱的肚子!
 *
 * @Author dudu
 * @Date 2019/6/28 19:10
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {

        /**
         * 被装饰的具体类
         */
        Man man = new Man();

        ManDecoratorA a = new ManDecoratorA();
        ManDecoratorB b = new ManDecoratorB();

        a.setPerson(man);
        b.setPerson(man);

        b.eat();
        System.out.println("eat分割线----------------------------------------");
        a.eat();

    }
}
