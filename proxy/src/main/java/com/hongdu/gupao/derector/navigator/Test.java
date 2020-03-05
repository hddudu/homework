package com.hongdu.gupao.derector.navigator;

/**
 * @ClassName Test
 * @Description TODO
 * @Author dudu
 * @Date 2020/3/5 13:37
 * @Version 1.0
 */
public class Test {
    public static void main(String[] args) {
        TuristsNavigator turistsNavigator = new TuristsNavigator();
        System.out.print("游客导航栏： ");
        turistsNavigator.printlnNavigators();
        System.out.println();

        System.out.print("普通用户导航栏： ");
        NormalUserNavigator normalUserNavigator = new NormalUserNavigator();
        normalUserNavigator.printlnNavigators();
        System.out.println();

        System.out.print("VIP用户导航栏： ");
        VipUserNavigator vipUserNavigator = new VipUserNavigator();
        vipUserNavigator.printlnNavigators();
        System.out.println();

        System.out.print("老师用户导航栏： ");
        TeacherNavigator teacherNavigator = new TeacherNavigator();
        teacherNavigator.printlnNavigators();
        System.out.println();

        System.out.print("管理员导航栏： ");
        AdministratorNavigator ad = new AdministratorNavigator();
        ad.printlnNavigators();
        System.out.println();

        System.out.print("超级管理员导航栏： ");
        SuperAdministratorNavigator superAdministratorNavigator = new SuperAdministratorNavigator();
//        superAdministratorNavigator.addNavigatorItem();
        superAdministratorNavigator.printlnNavigators();
        System.out.println();
    }
}
