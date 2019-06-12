package com.hongdu.gupao.commission.boss;

import java.util.HashMap;
import java.util.Map;

//类似于静态代理 ： 实现相同的接口的类： 也是真实类， 持有多个引用
//根据具体的工作需求去分配 调度不同的人完成
public class Manager implements IEmployee {

    private static Map<String, IEmployee> targets = new HashMap<>();
    //管理三个下属 分配工作 了解情况

    /**
     * 类似这个种结构 --》 可以换成常量形式以免修改出错；
     */
    public Manager() {
        targets.put("加密", new EmployeeA());
        targets.put("架构", new EmployeeB());
        targets.put("画图", new EmployeeC());
    }

    @Override
    public void doing(String command) {
        IEmployee obj = targets.get(command);
        if(obj != null) {
            obj.doing(command);
        } else {
            System.out.println("目前任务比较紧,您的这个" + command + "任务可能需要延迟!");
        }
    }
}
