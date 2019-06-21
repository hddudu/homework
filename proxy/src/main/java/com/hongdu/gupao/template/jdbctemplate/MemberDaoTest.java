package com.hongdu.gupao.template.jdbctemplate;

import com.hongdu.gupao.template.jdbctemplate.dao.MemberDao;

import java.util.List;

/**
 * @ClassName MemberDaoTest
 * @Description TODO
 * @Author dudu
 * @Date 2019/6/19 20:11
 * @Version 1.0
 */
public class MemberDaoTest {
    public static void main(String[] args) {
        MemberDao memberDao = new MemberDao(null);
        List<?> result = memberDao.listAll();
        System.out.println(result);
    }
}
