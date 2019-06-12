package com.hongdu.gupao.commission.boss;

public class EmployeeB implements IEmployee {

    @Override
    public void doing(String command) {
        System.out.println("我是员工B，擅长" + command + "工作");
    }
}
