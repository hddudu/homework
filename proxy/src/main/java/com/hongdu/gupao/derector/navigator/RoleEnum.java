package com.hongdu.gupao.derector.navigator;

public enum RoleEnum {
    TOURISTS("游客", "TOURISTS"),
    NORMAL_USER("普通用户", "NORMAL_USER"),
    VIP_MEMBER("VIP用户", "VIP_MEMBER"),
    TEACHER("老师", "TEACHER"),
    ADMINISTRATOR("管理员", "ADMINISTRATOR"),
    SUPER_ADMINISTRATOR("超级管理员", "SUPER_ADMINISTRATOR")
    ;
//    游客（路人）： 相逢何必曾相识， 相逢既是缘分
//    老师：   系统分配角色
//    Vip会员 ： 注册登录并充值
//    普通用户： 注册登录没充值
//    管理员：   沽泡学院合伙人
//    超级管理员 ： 沽泡学院创始人

    private String name;
    private String value;

    RoleEnum(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
