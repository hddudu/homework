package com.hongdu.gupao.commission.boss;

public class EmployeeC implements IEmployee {

    @Override
    public void doing(String command) {
        System.out.println("我是员工C，擅长" + command + "工作");
    }
}
