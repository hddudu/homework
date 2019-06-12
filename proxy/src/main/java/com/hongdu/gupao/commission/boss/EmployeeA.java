package com.hongdu.gupao.commission.boss;

public class EmployeeA implements IEmployee {

    @Override
    public void doing(String command) {
        System.out.println("我是员工A，擅长" + command + "工作");
    }
}
