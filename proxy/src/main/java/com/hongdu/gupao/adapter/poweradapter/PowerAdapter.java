package com.hongdu.gupao.adapter.poweradapter;

/**
 * @ClassName PowerAdapter
 * @Description 特别像静态代理 ： 实现了一个接口； 持有另一个的引用， 那么就具有另一个的全部方法
 * @Author dudu
 * @Date 2019/6/20 19:57
 * @Version 1.0
 */
public class PowerAdapter implements DC5 {
    //持有引用
    private AC220 ac220;

    //目前灭有spring： 采用构造器注入实例 （后期ioc将这个问题解决）
    public PowerAdapter(AC220 ac220) {
        this.ac220 = ac220;
    }

    @Override
    public int outputDC5V() {
        /**
         * 输入220 AC
         *  通过中间转换
         * 输出5 DC
         */
        int adapterInput = ac220.outputAC220();
        int adapterOutput = adapterInput / 44;
        System.out.println("使用PowerAdapters输入AC： " + adapterInput + "V,输出DC： " + adapterOutput + "V");
        return adapterOutput;
    }
}
