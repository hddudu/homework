package com.hongdu.mybatis.ormchongxie;

import com.hongdu.mybatis.one.entity.User;
import com.hongdu.mybatis.ormchongxie.core.QueryFactory;
import com.hongdu.mybatis.ormchongxie.core.service.IQuery;

import java.util.List;

/**
 * @ClassName Test
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/30 23:13
 * @Version 1.0
 */
public class Test {

    public static void main(String[] args) {

        //TableContext.updatePoFile(); 初始化，调用一次生成文件

        IQuery query = QueryFactory.createQuery();
        List list = query.queryRows("select * from user", User.class, null);
        User user = new User();
        user.setAge(23);
        user.setName("往往");
        user.setId(5);
        query.save(user);
        System.out.println("111");

    }
}
