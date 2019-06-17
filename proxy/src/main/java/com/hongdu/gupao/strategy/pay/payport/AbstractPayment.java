package com.hongdu.gupao.strategy.pay.payport;

import com.hongdu.gupao.strategy.pay.PayStatus;

/**
 * 支付 抽象类 ： 用到了类似模板的过程 ： 只需要在支付的时候用策略模式
 */
public abstract class AbstractPayment {

    //支付类型： 待选择 : 默认是default ： 同一个包下访问
    public abstract String getPayTypeName();
    //查询余额: 用户账号 : 金额需要被保护 保密下
    protected abstract Double queryBalance(String uid);
    //支付订单

    /**
     * @Author dudu
     * @Description //TODO
     * @Date 15:24 2019/6/13
     * @Param [uid, amount]
     * @return com.hongdu.gupao.strategy.pay.PayStatus
     **/
    public PayStatus pay(String uid, double amount) {
        if(queryBalance(uid) < amount) {//余额小于支付金额
            return new PayStatus(500,"支付失败","余额不足");
        }
        return new PayStatus(200,"支付成功","支付金额： " + amount);
    }

}
