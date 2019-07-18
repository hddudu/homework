package com.hongdu.gupao.chain;


import com.hongdu.gupao.chain.price.PriceChain;

/**
 * @ClassName Demo
 * @Description TODO
 * @Author dudu
 * @Date 2019/7/18 18:03
 * @Version 1.0
 */
public class Demo {
    public static void main(String[] args) {
        BookPriceHandler bookPriceHandler = new BookPriceHandler();
        //满减优惠
        PriceChain pc = new PreferentialCar();
        //生日一律打折
        PriceChain b = new Birthday();
        //没有用到链式处理
        double price = bookPriceHandler.calcPrice(500, b);
        //500 * 0.9 = 450 在 100~500之间， 然后： 450 * 0.8 = 360.0
        System.out.println("实际价格： " + price);

        //添加上链式处理 : 在生日上面再来个满减100的优惠
        b.register(pc);
        double price2 = bookPriceHandler.calcPrice(500, b);
        System.out.println("(生日优惠和满减优惠一起)计算后实际价格： " + price2);
    }

}
